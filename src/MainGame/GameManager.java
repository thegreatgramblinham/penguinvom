package MainGame;

import Engine.GameEngine;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;
import GameObjects.Characters.Enemies.Slime;
import GameObjects.Environmental.Backdrop;
import GameObjects.Characters.Player.PlayerObject;
import GameObjects.Projectiles.Bullet;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.HashSet;
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
        //Rendered Backdrops
        Backdrop bg = new Backdrop(new Rectangle(0,0,800,280), true);
        bg.SetSprite(new Image(new File("src/ImageAssets/backgrounds/penguinbg10000.png")
                .toURI().toString()));
        _currentSector.AddObject(bg,1);

        Backdrop floor = new Backdrop(new Rectangle(0,280,800,320), false);
        floor.SetSprite(new Image(new File("src/ImageAssets/backgrounds/woodfloor0000.png")
                .toURI().toString()));
        _currentSector.AddObject(floor,1);

        //Non-rendered Game Bounds
        Backdrop topBound = new Backdrop(new Rectangle(1,1,799,1), true);
        _currentSector.AddObject(topBound, 1);

        Backdrop botBound = new Backdrop(new Rectangle(1,599,799,1), true);
        _currentSector.AddObject(botBound, 1);

        Backdrop leftBound = new Backdrop(new Rectangle(1,1,1,599), true);
        _currentSector.AddObject(leftBound, 1);

        Backdrop rightBound = new Backdrop(new Rectangle(799,1,1,599), true);
        _currentSector.AddObject(rightBound, 1);

        //Temp Enemy Renderer
        Slime slim = new Slime(new Rectangle(600,400,64,64), 0.2F, 10);
        _currentSector.AddObject(slim, 2);
    }

    private void InitPlayerHandlers()
    {
        _player = new PlayerObject(new Rectangle(100, 400, 64, 64), 0.1F, 20);

        _currentSector.AddObject(_player,3);
        _primaryStage.getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                //Flags
                boolean isMovementKey = false;
                boolean isAttackKey = false;

                switch (event.getCode())
                {
                    //Movement
                    case UP:
                    case W:
                        _lastPlayerDirection = (3*Math.PI)/2;
                        isMovementKey = true;
                        break;
                    case DOWN:
                    case S:
                        _lastPlayerDirection = Math.PI/2;
                        isMovementKey = true;
                        break;
                    case LEFT:
                    case A:
                        _lastPlayerDirection = Math.PI;
                        isMovementKey = true;
                        break;
                    case RIGHT:
                    case D:
                        _lastPlayerDirection = 0;
                        isMovementKey = true;
                        break;

                    //Attacks
                    case SPACE:
                        Bullet b = new Bullet(new Point(_player.GetRight() + 1,
                                _player.GetCenterPoint().y), _player);
                        b.SetVelocity(new VelocityVector(0, 7));
                        _currentSector.AddObject(b, 3);
                        isAttackKey = true;
                        break;
                }

                if(isMovementKey)
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

                        for (int i = 0;
                             i < _currentSector.GetRenderGroupCount();
                             i++)
                        {
                            HashSet<GameWorldObject> renderGroup
                                    = _currentSector.GetRenderGroup(i);

                            if(renderGroup == null) continue;

                            for( GameWorldObject gObj : renderGroup)
                            {
                                CharacterBase charObj = null;

                                if(gObj instanceof CharacterBase)
                                    charObj = (CharacterBase)gObj;

                                gc.strokeRect(gObj.x,
                                        gObj.y,
                                        gObj.width,
                                        gObj.height);

                                if(charObj != null)
                                {
                                    charObj.DrawWalkAnimation(gc);
                                }
                                else if(gObj.GetSprite() != null)
                                {
                                    gc.drawImage(gObj.GetSprite(), gObj.getX(), gObj.getY(),
                                            gObj.GetSprite().getWidth(), gObj.GetSprite().getHeight());
                                }

                                if(_player.GetIsAccelerating())
                                    _player.AccelerateBy(_player.GetAcceleration());

                                _engine.CycleCollision();
                            }
                        }
                    }
                });
    }

}
