package MainGame;

import Engine.GameEngine;
import GameObjectBase.GameWorldObject;
import GameObjects.Environmental.Backdrop;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
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

    //Constructor
    public GameManager(Stage displayStage)
    {
        _primaryStage = displayStage;
        _engine = new GameEngine();

        InitStage();
        InitRenderLoop();
        InitEnvironment();
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
                = _engine.CreateSector(800, 600, 30, 0.2F, GravityApplication.Area);
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
                        Iterator<GameWorldObject> iter
                                = _currentSector.GetObjectsInSector();

                        while(iter.hasNext())
                        {
                            GameWorldObject gObj = iter.next();

                            gc.strokeRect(gObj.x,
                                          gObj.y,
                                          gObj.width,
                                          gObj.height);
                        }
                    }
                });
    }

}
