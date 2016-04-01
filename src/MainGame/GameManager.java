package MainGame;

import Engine.GameEngine;
import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;
import GameObjects.Characters.Enemies.AI.interfaces.IAiController;
import GameObjects.Characters.Enemies.Dagron;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Characters.Enemies.Skilleatin;
import GameObjects.Characters.Enemies.Slim;
import GameObjects.Environmental.Backdrop;
import GameObjects.Characters.Player.PlayerObject;
import GameObjects.Projectiles.Bullet;
import PhysicsBase.CollisionRules.CollisionGroupNamePair;
import PhysicsBase.CollisionRules.CollisionGroupPair;
import PhysicsBase.CollisionRules.enums.CollisionRule;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("Convert2Lambda")
public class GameManager
{
    //Private Constants
    private static final Point _originPoint = new Point(128,128);
    private static final Point _drawPoint = new Point(-0,-0); //set to 0,0 to see full sector
    private static final int X_RES = 1056;
    private static final int Y_RES = 856;

    //Private Static Fields
    private static HashMap<GameWorldObject, Integer> _objectAdditionRenderGroupQueue = new HashMap<>();
    private static HashMap<GameWorldObject, String> _objectAdditionCollisionGroupQueue = new HashMap<>();
    private static GameEngine _engineInstance;

    //Private Variables - UI
    private Stage _primaryStage;
    private GraphicsContext _gc;
    private Timeline _gameLoop;
    private Sector _currentSector;

    //Private Variables - Engine
    private PlayerObject _player;
    private Backdrop _skybg;
    private EnemySpawner _enemySpawner;
    private double _lastPlayerDirection = 0;

    //Constructor
    public GameManager(Stage displayStage)
    {
        _primaryStage = displayStage;
        _engineInstance = new GameEngine();

        InitCollisionRules();
        InitStage();
        InitRenderLoop();
        InitEnvironment();
        InitEnemySpawner();
        InitPlayerHandlers();
    }

    //Public Methods
    public void Start() throws Exception
    {
        _engineInstance.Start();
        _gameLoop.play();
    }

    //Private Methods
    private void InitCollisionRules()
    {
        //Player projectiles can't collide with other player projectiles.
        _engineInstance.AddCollisionRule(
                new CollisionGroupNamePair(
                        GameConstants.PLAYER_PROJECTILE_GROUP,
                        GameConstants.PLAYER_PROJECTILE_GROUP),
                CollisionRule.CannotCollideWith);

        //Enemy projectiles can't hit other enemies.
        _engineInstance.AddCollisionRule(
                new CollisionGroupNamePair(
                        GameConstants.ENEMY_GROUP,
                        GameConstants.ENEMY_PROJECTILE_GROUP),
                CollisionRule.CannotCollideWith);

        //Enemy projectiles can't hit other enemy projectiles.
        _engineInstance.AddCollisionRule(
                new CollisionGroupNamePair(
                        GameConstants.ENEMY_PROJECTILE_GROUP,
                        GameConstants.ENEMY_PROJECTILE_GROUP),
                CollisionRule.CannotCollideWith);
    }

    private void InitStage()
    {
        Group root = new Group();
        Scene scene = new Scene( root );
        _primaryStage.setScene( scene );

        Canvas canvas = new Canvas( X_RES, Y_RES );
        root.getChildren().add( canvas );
        _gc = canvas.getGraphicsContext2D();

        _currentSector
                = _engineInstance.CreateSector(
                    X_RES,
                    Y_RES,
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
                SecLocX(0), SecLocY(0),800,280), true, true, "BackWall");
        bg.SetSprite(new Image(new File("src/ImageAssets/backgrounds/penguinbg10000.png")
                .toURI().toString()));
        _currentSector.AddObject(bg,1, GameConstants.BACKGROUND_GROUP);

        _skybg = new Backdrop(new Rectangle(
                SecLocX(0), SecLocY(110),800,280), false, false, "BackSky");
        _skybg.SetSprite(new Image(new File("src/ImageAssets/backgrounds/skybg20000.png")
                .toURI().toString()));
        _currentSector.AddObject(_skybg,0, GameConstants.BACKGROUND_GROUP);

        Backdrop floor = new Backdrop(new Rectangle(
                SecLocX(0), SecLocY(280),800,320), true, false, "Floor");
        floor.SetSprite(new Image(new File("src/ImageAssets/backgrounds/woodfloor0000.png")
                .toURI().toString()));
        _currentSector.AddObject(floor, 1, GameConstants.BACKGROUND_GROUP);

        //Non-rendered Game Bounds
        Backdrop topBound = new Backdrop(new Rectangle(
                1, 1, X_RES - 1,1), true, true, "TopBounds");
        _currentSector.AddObject(topBound, 1, GameConstants.PLAYER_GAMEBOUNDS_GROUP);

        Backdrop botBound = new Backdrop(new Rectangle(
                1, Y_RES - 1,X_RES - 1,1), true, true, "BottomBounds");
        _currentSector.AddObject(botBound, 1, GameConstants.PLAYER_GAMEBOUNDS_GROUP);

        Backdrop leftBound = new Backdrop(new Rectangle(
                1,1,1, Y_RES - 1), true, true, "LeftBounds");
        _currentSector.AddObject(leftBound, 1, GameConstants.PLAYER_GAMEBOUNDS_GROUP);

        Backdrop rightBound = new Backdrop(new Rectangle(
                X_RES - 1 , 1 ,1, Y_RES - 1), true, true, "RightBounds");
        _currentSector.AddObject(rightBound, 1, GameConstants.PLAYER_GAMEBOUNDS_GROUP);
    }

    private void InitEnemySpawner()
    {
        //Temp Enemy Render
        Slim slim = new Slim(new Rectangle(
                SecLocX(600), SecLocY(400),64,64), 0.2F, 10);

        Dagron dagron = new Dagron(new Rectangle(
                SecLocX(600), SecLocY(300),64,64), 0.5F, 10);

        Skilleatin skilleatin = new Skilleatin(new Rectangle(
                SecLocX(600), SecLocY(300),64,64), 0.5F, 10);

        EnemyBase[] enemies = {slim, dagron, skilleatin};

        _enemySpawner = new EnemySpawner(_currentSector, enemies,
                new Rectangle(SecLocX(0),SecLocY(280),800,320), 180);
    }

    private void InitPlayerHandlers()
    {
        _player = new PlayerObject(
                new Rectangle(SecLocX(100), SecLocX(400), 64, 64), 0.1F, 20);

        _currentSector.AddObject(_player, 3, GameConstants.PLAYER_GROUP);
        _primaryStage.getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                GameConstants.SetKeyPressed(event.getCode());
            }
        });

        _primaryStage.getScene().setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent event)
                    {
                        GameConstants.SetKeyReleased(event.getCode());
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
                        gc.clearRect(0, 0, X_RES, Y_RES);

                        AddQueuedObjects();

                        gc.strokeRect(X_RES - 64,0,1,Y_RES);

                        _engineInstance.CycleEngine();

                        HandlePlayerAttack();
                        HandlePlayerMovement();

                        if(_enemySpawner.ShouldSpawn())
                            _enemySpawner.SpawnRandom();

                        for (int i = 0;
                             i < _currentSector.GetRenderGroupCount();
                             i++)
                        {
                            HashSet<GameWorldObject> renderGroup
                                    = _currentSector.GetRenderGroup(i);

                            if(renderGroup == null) continue;

                            for( GameWorldObject gameEngObj : renderGroup)
                            {
                                GameObject gObj = (GameObject)gameEngObj;

                                gc.strokeRect(gObj.GetGameDrawPoint().x,
                                        gObj.GetGameDrawPoint().y,
                                        gObj.width,
                                        gObj.height);

                                if(HandlePlayerAction(gObj, gc))
                                {
                                    //Player action handled.
                                    _skybg.NSetLocation(new Point(
                                            SecLocX((_player.x - _skybg.width)/20+30),
                                            SecLocY((_player.y - _skybg.height)/20)+30));
                                }
                                else if(HandleEnemyAction(gObj, gc))
                                {
                                    //Ai controlled action handled.
                                }
                                else if(gObj.GetSprite() != null)
                                {
                                    gc.drawImage(gObj.GetSprite(),
                                            gObj.GetGameDrawPoint().x,
                                            gObj.GetGameDrawPoint().y,
                                            gObj.GetSprite().getWidth(),
                                            gObj.GetSprite().getHeight());
                                }

                                _engineInstance.CycleCollision();
                            }
                        }
                    }
                });
    }

    private void HandlePlayerMovement()
    {
        boolean isKeyPressed = false;

        if(GameConstants.IsKeyPressed(KeyCode.W)
                || GameConstants.IsKeyPressed(KeyCode.UP))
        {
            _lastPlayerDirection = (3*Math.PI)/2;
            isKeyPressed = true;
        }
        else if (GameConstants.IsKeyPressed(KeyCode.S)
                || GameConstants.IsKeyPressed(KeyCode.DOWN))
        {
            _lastPlayerDirection = Math.PI/2;
            isKeyPressed = true;
        }
        else if (GameConstants.IsKeyPressed(KeyCode.A)
                || GameConstants.IsKeyPressed(KeyCode.LEFT))
        {
            _lastPlayerDirection = Math.PI;
            isKeyPressed = true;
        }
        else if (GameConstants.IsKeyPressed(KeyCode.D)
                || GameConstants.IsKeyPressed(KeyCode.RIGHT))
        {
            _lastPlayerDirection = 0;
            isKeyPressed = true;
        }

        if(isKeyPressed)
            _player.SetVelocity(new VelocityVector(_lastPlayerDirection, 2));
    }

    private void HandlePlayerAttack()
    {
        if(!GameConstants.IsKeyPressed(KeyCode.SPACE)) return;

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

        _currentSector.AddObject(b, 3, GameConstants.PLAYER_PROJECTILE_GROUP);
    }

    private boolean HandlePlayerAction(GameWorldObject gObj, GraphicsContext gc)
    {
        PlayerObject playObj;

        if(gObj instanceof PlayerObject)
            playObj = (PlayerObject)gObj;
        else
            return false;

        playObj.DrawWalkAnimation(gc);

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

    private void AddQueuedObjects()
    {
        for( GameWorldObject gObj : _objectAdditionRenderGroupQueue.keySet())
        {
            _currentSector.AddObject(gObj,
                    _objectAdditionRenderGroupQueue.get(gObj),
                    _objectAdditionCollisionGroupQueue.get(gObj));
        }

        _objectAdditionRenderGroupQueue.clear();
        _objectAdditionCollisionGroupQueue.clear();
    }


    //Static Methods
    private static int SecLocX(int offset)
    {
        return _originPoint.x + offset;
    }

    private static int SecLocY(int offset)
    {
        return _originPoint.y + offset;
    }

    public static int DrawLocX(int offset)
    {
        return  _drawPoint.x + offset;
    }

    public static int DrawLocY(int offset)
    {
        return  _drawPoint.y + offset;
    }

    public static void QueueObjectForAddition(GameWorldObject obj, int renderGroup, String groupName)
    {
        _objectAdditionRenderGroupQueue.put(obj, renderGroup);
        _objectAdditionCollisionGroupQueue.put(obj, groupName);
    }

}
