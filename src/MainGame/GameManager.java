package MainGame;

import Engine.GameEngine;
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

@SuppressWarnings("Convert2Lambda")
public class GameManager
{
    //Private Variables - UI
    private Stage _primaryStage;
    private GraphicsContext _gc;
    private Timeline _gameLoop;

    //Private Variables - Engine
    private GameEngine _engine;

    //Constructor
    public GameManager(Stage displayStage)
    {
        _primaryStage = displayStage;

        InitStage();
        InitRenderLoop();
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

        //todo sector init
    }

    private void InitRenderLoop()
    {
        _gameLoop = new Timeline();
        _gameLoop.setCycleCount( Timeline.INDEFINITE );
        KeyFrame kf = Render(_gc);
        _gameLoop.getKeyFrames().add( kf );
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

                    }
                });
    }

}
