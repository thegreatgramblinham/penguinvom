package MainGame;

import Engine.GameEngine;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;
import GameObjects.Characters.Enemies.AI.interfaces.IAiController;
import GameObjects.Characters.Enemies.Dagron;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Characters.Enemies.Slim;
import GameObjects.Environmental.Backdrop;
import GameObjects.Characters.Player.PlayerObject;
import GameObjects.Projectiles.Bullet;
import GameObjects.Projectiles.enums.ProjectileDirection;
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

@SuppressWarnings("Convert2Lambda")
public class GameManager
{
    //Private Constants
    private static final Point _originPoint = new Point(64,64);

    //Public Static Fields
    public static GameEngine engineInstance;

    //Private Variables - UI
    private Stage _primaryStage;
    private GraphicsContext _gc;
    private Timeline _gameLoop;
    private Sector _currentSector;

    //Private Variables - Engine
    private PlayerObject _player;
    private EnemySpawner _enemySpawner;
    private double _lastPlayerDirection = 0;

    //Constructor
    public GameManager(Stage displayStage)
    {
        _primaryStage = displayStage;
        engineInstance = new GameEngine();

        InitStage();
        InitRenderLoop();
        InitEnvironment();
        InitEnemySpawner();
        InitPlayerHandlers();
    }

    //Public Methods
    public void Start() throws Exception
    {
        engineInstance.Start();
        _gameLoop.play();
    }

    //Private Methods
    private void InitStage()
    {
        Group root = new Group();
        Scene scene = new Scene( root );
        _primaryStage.setScene( scene );

        Canvas canvas = new Canvas( 928, 728 );
        root.getChildren().add( canvas );
        _gc = canvas.getGraphicsContext2D();

        _currentSector
                = engineInstance.CreateSector(
                    928,
                    728,
                    30, 0.5F, GravityApplication.Area);
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
        Backdrop bg = new Backdrop(new Rectangle(
                DrawLocX(0),DrawLocY(0),800,280), true, "BackWall");
        bg.SetSprite(new Image(new File("src/ImageAssets/backgrounds/penguinbg10000.png")
                .toURI().toString()));
        _currentSector.AddObject(bg,1);

        Backdrop floor = new Backdrop(new Rectangle(
                DrawLocX(0),DrawLocY(280),800,320), false, "Floor");
        floor.SetSprite(new Image(new File("src/ImageAssets/backgrounds/woodfloor0000.png")
                .toURI().toString()));
        _currentSector.AddObject(floor,1);

        //Non-rendered Game Bounds
        Backdrop topBound = new Backdrop(new Rectangle(
                DrawLocX(1),DrawLocY(1),799,1), true, "TopBounds");
        _currentSector.AddObject(topBound, 1);

        Backdrop botBound = new Backdrop(new Rectangle(
                DrawLocX(1),DrawLocY(599),799,1), true, "BottomBounds");
        _currentSector.AddObject(botBound, 1);

        Backdrop leftBound = new Backdrop(new Rectangle(
                DrawLocX(1),DrawLocY(1),1,599), true, "LeftBounds");
        _currentSector.AddObject(leftBound, 1);

        Backdrop rightBound = new Backdrop(new Rectangle(
                DrawLocX(799),DrawLocY(1),1,599), true, "RightBounds");
        _currentSector.AddObject(rightBound, 1);
    }

    private void InitEnemySpawner()
    {
        //Temp Enemy Render
        Slim slim = new Slim(new Rectangle(
                DrawLocX(600),DrawLocY(400),64,64), 0.2F, 10);
        _currentSector.AddObject(slim, 2);

        Dagron dagron = new Dagron(new Rectangle(
                DrawLocX(600),DrawLocY(300),64,64), 0.5F, 10);
        _currentSector.AddObject(dagron, 2);

//        EnemyBase[] enemies = {slim, dagron};
//
//        _enemySpawner = new EnemySpawner(_currentSector, enemies,
//                new Rectangle(0,280,800,320), 180);
    }

    private void InitPlayerHandlers()
    {
        _player = new PlayerObject(
                new Rectangle(DrawLocX(100), DrawLocX(400), 64, 64), 0.1F, 20);

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
                        Bullet b = null;
                        switch(_player.GetProjectileDirection())
                        {
                            case Left:
                                b = new Bullet(new Point(_player.GetLeft() - Bullet.WIDTH - 1 ,
                                        _player.GetCenterPoint().y), _player);
                                b.SetVelocity(new VelocityVector(Math.PI, 7));
                                break;
                            case Right:
                                b = new Bullet(new Point(_player.GetRight() + 1,
                                        _player.GetCenterPoint().y), _player);
                                b.SetVelocity(new VelocityVector(0, 7));
                                break;
                        }

                        if(b != null)
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

    public KeyFrame Render(final GraphicsContext gc)
    {
        return new KeyFrame(
                Duration.seconds(0.017),
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        gc.clearRect(0, 0, 800, 600);
                        engineInstance.CycleEngine();

//                        if(_enemySpawner.ShouldSpawn())
//                            _enemySpawner.SpawnRandom();

                        for (int i = 0;
                             i < _currentSector.GetRenderGroupCount();
                             i++)
                        {
                            HashSet<GameWorldObject> renderGroup
                                    = _currentSector.GetRenderGroup(i);

                            if(renderGroup == null) continue;

                            for( GameWorldObject gObj : renderGroup)
                            {
                                gc.strokeRect(gObj.x,
                                        gObj.y,
                                        gObj.width,
                                        gObj.height);

                                if(HandlePlayerAction(gObj, gc))
                                {
                                    //Player action handled.
                                }
                                else if(HandleEnemyAction(gObj, gc))
                                {
                                    //Ai controlled action handled.
                                }
                                else if(gObj.GetSprite() != null)
                                {
                                    gc.drawImage(gObj.GetSprite(), gObj.getX(), gObj.getY(),
                                            gObj.GetSprite().getWidth(), gObj.GetSprite().getHeight());
                                }

                                engineInstance.CycleCollision();
                            }
                        }
                    }
                });
    }

    private boolean HandlePlayerAction(GameWorldObject gObj, GraphicsContext gc)
    {
        PlayerObject playObj;

        if(gObj instanceof PlayerObject)
            playObj = (PlayerObject)gObj;
        else
            return false;

        playObj.DrawWalkAnimation(gc);

//        if(_player.GetIsAccelerating())
//            _player.AccelerateBy(_player.GetAcceleration());

        return true;
    }

    private boolean HandleEnemyAction(GameWorldObject gObj, GraphicsContext gc)
    {
        IAiController aiObj;

        if(gObj instanceof IAiController)
            aiObj = (IAiController)gObj;
        else
            return false;

        aiObj.PerformAndDrawAction(gc);

        return true;
    }

    private static int DrawLocX(int offset)
    {
        return _originPoint.x + offset;
    }

    private static int DrawLocY(int offset)
    {
        return _originPoint.y + offset;
    }

}
