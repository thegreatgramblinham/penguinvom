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
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
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
    private double _lastPlayerDirection;

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
        Backdrop bg = new Backdrop(new Rectangle(0,0,800,280));
        bg.SetSprite(new Image(new File("src/ImageAssets/penguinbg10000.png")
                .toURI().toString()));
        _currentSector.AddObject(bg);
    }

    private void InitPlayerHandlers()
    {
        _player = new PlayerObject(new Rectangle(100, 400, 64, 64), 0.1F);

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
                        _lastPlayerDirection = (3*Math.PI)/2;
                        break;
                    case DOWN:
                        _lastPlayerDirection = Math.PI/2;
                        break;
                    case LEFT:
                        _lastPlayerDirection = Math.PI;
                        break;
                    case RIGHT:
                        _lastPlayerDirection = 0;
                        break;
                }
                _player.SetVelocity(new VelocityVector(_lastPlayerDirection, 2));
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
                                VelocityVector accel = _player.GetAcceleration();
                                if(accel != null && accel.GetRadianRotation() == _lastPlayerDirection)
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

                            if(gObj.GetSprite() != null)
                            {
                                gc.drawImage(gObj.GetSprite(), gObj.getX(), gObj.getY(),
                                        gObj.GetSprite().getWidth(), gObj.GetSprite().getHeight());
                            }

                            if(_player.GetIsAccelerating())
                                _player.AccelerateBy(_player.GetAcceleration());
                        }
                    }
                });
    }

}
