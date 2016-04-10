package MainGame;

import Engine.GameEngine;
import GameObjectBase.GameWorldObject;
import GameObjectBase.enums.Side;
import GameObjects.Base.GameObject;
import GameObjects.Characters.Enemies.AI.interfaces.IAiController;
import GameObjects.Characters.Enemies.Dagron;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Characters.Enemies.Skilleatin;
import GameObjects.Characters.Enemies.Slim;
import GameObjects.Characters.Player.PlayerObject;
import GameObjects.Projectiles.Bullet;
import MainGame.Debugging.DebugHelper;
import PhysicsBase.CollisionRules.CollisionGroupNamePair;
import PhysicsBase.CollisionRules.enums.CollisionRule;
import PhysicsBase.Vectors.VelocityVector;
import SectorBase.enums.GravityApplication;
import Stages.MainCastleRoom;
import Stages.RoomBase;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("Convert2Lambda")
public class GameManager
{
    //Private Constants
    private static final Point _gameStartingPoint = new Point(128,128);
    private static final int _stageWidth = 3500;
    private static final int _stageHeight = 900;

    //Private Static Fields
    private static HashMap<GameWorldObject, Integer> _objectAdditionRenderGroupQueue = new HashMap<>();
    private static HashMap<GameWorldObject, String> _objectAdditionCollisionGroupQueue = new HashMap<>();
    private static GameEngine _engineInstance;
    private static boolean _isFullscreen = true;
    private static boolean _showPropertyDebugMode = false;

    //Private Variables - UI
    private Stage _primaryStage;
    private GraphicsContext _gc;
    private Timeline _gameLoop;
    private ViewPort _viewPort;

    //Private Variables - Engine
    private PlayerObject _player;
    private EnemySpawner _enemySpawner;
    private RoomBase _currentRoom;
    private double _lastPlayerDirection = 0;

    //Constructor
    public GameManager(Stage displayStage) throws Exception {

        _primaryStage = displayStage;
        _engineInstance = new GameEngine();

        InitCollisionRules();
        InitStage();
        InitRenderLoop();
        InitEnvironment();
        InitEnemySpawner();
        InitPlayer();
        InitKeyHandlers();
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

        //Enemies are not restricted by the same bounds as the player
        _engineInstance.AddCollisionRule(
                new CollisionGroupNamePair(
                        GameConstants.ENEMY_GROUP,
                        GameConstants.PLAYER_GAMEBOUNDS_GROUP),
                CollisionRule.CannotCollideWith);
    }

    private void InitStage()
    {
        Group root = new Group();
        Scene scene = new Scene( root );

        if(_isFullscreen)
        {
            _primaryStage.setFullScreen(true);
            Scale scale = new Scale(1.8, 1.8);
            scale.setPivotX(0);
            scale.setPivotY(0);
            scene.getRoot().getTransforms().setAll(scale);
        }

        _primaryStage.setScene( scene );

        _viewPort = new ViewPort( ViewPort.X_RES,
                ViewPort.Y_RES, _gameStartingPoint,
                0,
                _gameStartingPoint.y,
                _stageWidth,
                _stageHeight
                );

        Canvas canvas = new Canvas( ViewPort.X_RES, ViewPort.Y_RES );
        root.getChildren().add( canvas );
        _gc = canvas.getGraphicsContext2D();

        _engineInstance.CreateSector(
                    _stageWidth,
                   _stageHeight,
                   30, 0.5F, GravityApplication.Area);
    }

    private void InitRenderLoop()
    {
        _gameLoop = new Timeline();
        _gameLoop.setCycleCount( Timeline.INDEFINITE );
        KeyFrame kf = Render(_gc);
        _gameLoop.getKeyFrames().add( kf );
    }

    private void InitEnvironment() throws Exception
    {
        _currentRoom = new MainCastleRoom(_engineInstance.GetActiveSector());
    }

    private void InitEnemySpawner()
    {
        //Temp Enemy Render
        Slim slim = new Slim(new Rectangle(
                ViewPort.SecLocX(600),
                ViewPort.SecLocY(400),64,64), 0.2F, 10);

        Dagron dagron = new Dagron(new Rectangle(
                ViewPort.SecLocX(600),
                ViewPort.SecLocY(300),64,64), 0.5F, 10);

        Skilleatin skilleatin = new Skilleatin(new Rectangle(
                ViewPort.SecLocX(600),
                ViewPort.SecLocY(300),64,64), 0.5F, 10);

        EnemyBase[] enemies = {slim, dagron, skilleatin};

        _enemySpawner = new EnemySpawner(_engineInstance.GetActiveSector(),
                enemies, new Rectangle(ViewPort.SecLocX(0),
                ViewPort.SecLocY(280),800,320), 180);
    }

    private void InitPlayer()
    {
        _player = new PlayerObject(
                new Rectangle(
                        ViewPort.SecLocX(_currentRoom.GetPlayerStartingLocation(Side.Left).x),
                        ViewPort.SecLocY(_currentRoom.GetPlayerStartingLocation(Side.Left).y),
                        64, 64), 0.1F, 20);

        _engineInstance.GetActiveSector().AddObject(_player,
                GameConstants.PLAYER_RENDER_GROUP,
                GameConstants.PLAYER_GROUP);
    }

    private void InitKeyHandlers()
    {
        _primaryStage.getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent event)
                    {
                        GameConstants.SetKeyPressed(event.getCode());

                        //Shift + Ctrl + X enters debug mode.
                        if(event.getCode() == KeyCode.X
                                && event.isShiftDown()
                                && event.isControlDown())
                        {
                            _showPropertyDebugMode = !_showPropertyDebugMode;
                        }
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
                        gc.clearRect(0, 0, _viewPort.GetWidth(), _viewPort.GetHeight());

                        //todo - allow for change of sector if necessary

                        AddQueuedObjects();

                        _engineInstance.CycleEngine();

                        HandlePlayerAttack();
                        HandlePlayerMovement();

//                        if(_enemySpawner.ShouldSpawn())
//                            _enemySpawner.SpawnRandom();

                        for (int i = 0;
                             i < _engineInstance.GetActiveSector().GetRenderGroupCount();
                             i++)
                        {
                            HashSet<GameWorldObject> renderGroup
                                    = _engineInstance.GetActiveSector().GetRenderGroup(i);

                            if(renderGroup == null) continue;

                            for( GameWorldObject gameEngObj : renderGroup)
                            {
                                GameObject gObj = (GameObject)gameEngObj;

                                if(_showPropertyDebugMode)
                                {
                                    gc.strokeRect(gObj.GetGameDrawPoint().x,
                                            gObj.GetGameDrawPoint().y,
                                            gObj.width,
                                            gObj.height);

                                    if(!gObj.GetIsImmobile())
                                    {
                                        gc.setFill(javafx.scene.paint.Color.CHARTREUSE);
                                        gc.fillText(
                                                DebugHelper.BuildFormattedPropertyString(gObj),
                                                ViewPort.DrawLocX(gObj.GetRight()),
                                                ViewPort.DrawLocY(gObj.GetBottom()));
                                    }
                                }

                                if(HandlePlayerAction(gObj, gc))
                                {
                                    //Player action handled.
//                                    _skybg.NSetLocation(new Point(
//                                            ViewPort.SecLocX((_player.x - _skybg.width)/20+30),
//                                            ViewPort.SecLocY((_player.y - _skybg.height)/20)+30));

                                    _viewPort.ScrollIntoView(_player.GetCenterPoint());
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
        {
            _player.SetVelocity(new VelocityVector(_lastPlayerDirection, 2));
        }
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

        _engineInstance.GetActiveSector().AddObject(b,
                GameConstants.PLAYER_PROJECTILE_RENDER_GROUP,
                GameConstants.PLAYER_PROJECTILE_GROUP);
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
            _engineInstance.GetActiveSector().AddObject(gObj,
                    _objectAdditionRenderGroupQueue.get(gObj),
                    _objectAdditionCollisionGroupQueue.get(gObj));
        }

        _objectAdditionRenderGroupQueue.clear();
        _objectAdditionCollisionGroupQueue.clear();
    }

    //Static Methods
    public static void QueueObjectForAddition(GameWorldObject obj, int renderGroup, String groupName)
    {
        _objectAdditionRenderGroupQueue.put(obj, renderGroup);
        _objectAdditionCollisionGroupQueue.put(obj, groupName);
    }



}
