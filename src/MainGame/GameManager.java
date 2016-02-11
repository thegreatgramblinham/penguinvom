package MainGame;

import Engine.GameEngine;
import GameObjectBase.GameWorldObject;
import GameObjects.Environmental.Backdrop;
import GameObjects.Player.PlayerObject;
import PhysicsBase.Vectors.VelocityVector;
import SectorBase.Sector;
import SectorBase.enums.GravityApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.internal.util.xml.impl.Input;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Iterator;

@SuppressWarnings("Convert2Lambda")
public class GameManager
{
    //Private Variables - UI
    private Stage _primaryStage;
    private GraphicsContext _gc;
    private Timeline _gameLoop;
    private Sector _currentSector;

    //Private Variables - Engine
    private GameEngine _engine;
    private PlayerObject _player;

    //Constructor
    public GameManager(Stage displayStage)
    {
        _primaryStage = displayStage;
        _engine = new GameEngine();

        InitStage();
        InitRenderLoop();
        InitEnvironment();
        InitPlayerHandlers();
        InitSprites();
    }

    //Public Methods
    public void Start() throws Exception
    {
        _engine.Start();
        _gameLoop.play();
    }

    //Private Methods
    private void InitStage()
    {
        Group root = new Group();
        Scene scene = new Scene( root );
        _primaryStage.setScene( scene );

        Canvas canvas = new Canvas( 800, 600 );
        root.getChildren().add( canvas );
        _gc = canvas.getGraphicsContext2D();

        _currentSector
                = _engine.CreateSector(800, 600, 30, 0.5F, GravityApplication.Area);
    }

    private void InitRenderLoop()
    {
        _gameLoop = new Timeline();
        _gameLoop.setCycleCount( Timeline.INDEFINITE );
        KeyFrame kf = Render(_gc);
        _gameLoop.getKeyFrames().add( kf );
    }

    private void InitEnvironment()
    {
        _currentSector.AddObject(new Backdrop(new Rectangle(0,0,800,275)));
    }

    private void InitPlayerHandlers()
    {
        _player = new PlayerObject(new Rectangle(100, 400, 20, 20), 0.1F);

        _currentSector.AddObject(_player);

        _primaryStage.getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case UP:
                        _player.AccelerateBy(new VelocityVector((3*Math.PI)/2, .1));
                        break;
                    case DOWN:
                        _player.AccelerateBy(new VelocityVector(Math.PI/2, .1));
                        break;
                    case LEFT:
                        _player.AccelerateBy(new VelocityVector(Math.PI, .1));
                        break;
                    case RIGHT:
                        _player.AccelerateBy(new VelocityVector(0, .1));
                        break;
                }
            }
        });

        _primaryStage.getScene().setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent event)
                    {
                        switch (event.getCode())
                        {
                            case UP:
                            case DOWN:
                            case LEFT:
                            case RIGHT:
                                _player.StopAcceleration();
                                break;
                        }
                    }
                });
    }

    private void InitSprites()
    {
        //todo sprite import
    }

    public KeyFrame Render(final GraphicsContext gc)
    {
        return new KeyFrame(
                Duration.seconds(0.017),
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        gc.clearRect(0, 0, 800, 600);
                        _engine.CycleEngine();

                        Iterator<GameWorldObject> iter
                                = _currentSector.GetObjectsInSector();

                        while(iter.hasNext())
                        {
                            GameWorldObject gObj = iter.next();

                            gc.strokeRect(gObj.x,
                                          gObj.y,
                                          gObj.width,
                                          gObj.height);

                            if(_player.GetIsAccelerating())
                                _player.AccelerateBy(_player.GetAcceleration());
                        }
                    }
                });
    }

}
