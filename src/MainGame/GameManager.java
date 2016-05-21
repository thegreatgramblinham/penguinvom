package MainGame;

import Engine.GameEngine;
import GameObjectBase.GameWorldObject;
import GameObjectBase.enums.Side;
import GameObjects.Base.GameObject;
import GameObjects.BattleCharacters.BattleCharacterBase;
import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import GameObjects.Characters.Enemies.AI.interfaces.IAiController;
import GameObjects.Characters.Enemies.Dagron;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Characters.Enemies.Skilleatin;
import GameObjects.Characters.Enemies.Slim;
import GameObjects.Characters.Player.PlayerObject;
import GameObjects.Environmental.Backdrop;
import GameObjects.Environmental.Props.PropBase;
import GameObjects.Projectiles.Bullet;
import Global.Tuple;
import MainGame.Battle.BattleManager;
import MainGame.Debugging.DebugHelper;
import MainGame.Mapping.StageMap;
import PhysicsBase.CollisionRules.CollisionGroupNamePair;
import PhysicsBase.CollisionRules.enums.CollisionRule;
import PhysicsBase.Vectors.VelocityVector;
import SectorBase.Sector;
import SectorBase.enums.GravityApplication;
import Stages.CastleGardenStage;
import Stages.OverworldStage;
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
    /*
       Main game class.
       **NOTE: This class has static dependencies, only one instance is allowed.**
    */

    //Public Static Fields
    public static PlayerObject PLAYER_OBJECT;

    //Private Static Fields
    private static HashMap<GameWorldObject, Integer> _objectAdditionRenderGroupQueue = new HashMap<>();
    private static HashMap<GameWorldObject, String> _objectAdditionCollisionGroupQueue = new HashMap<>();
    private static Tuple<OverworldStage, Side> _sectorTransitionQueue = null;
    private static BattleCharacterGroup<EnemyBattleCharacter> _battleStageTransitionQueue = null;
    private static GameEngine _engineInstance;
    private static boolean _isFullscreen = false;
    private static boolean _showPropertyDebugMode = false;

    //Private Variables - UI
    private Stage _primaryStage;
    private GraphicsContext _gc;
    private Timeline _gameLoop;
    private ViewPort _viewPort;

    //Private Variables - Engine
    private EnemySpawner _enemySpawner;
    private StageMap _stageMap;
    private OverworldStage _currentRoom;
    private BattleManager _currentBattleManager;
    private double _lastPlayerDirection = 0;
    private boolean _isBattleMode = false;

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
    //region Initialization Methods
    private void InitCollisionRules()
    {
        //Player projectiles can't collide with other player projectiles.
        _engineInstance.AddCollisionRule(
                new CollisionGroupNamePair(
                        GameConstants.PLAYER_PROJECTILE_COLLISION_GROUP,
                        GameConstants.PLAYER_PROJECTILE_COLLISION_GROUP),
                CollisionRule.CannotCollideWith);

        //Enemy projectiles can't hit other enemies.
        _engineInstance.AddCollisionRule(
                new CollisionGroupNamePair(
                        GameConstants.ENEMY_COLLISION_GROUP,
                        GameConstants.ENEMY_PROJECTILE_COLLISION_GROUP),
                CollisionRule.CannotCollideWith);

        //Enemy projectiles can't hit other enemy projectiles.
        _engineInstance.AddCollisionRule(
                new CollisionGroupNamePair(
                        GameConstants.ENEMY_PROJECTILE_COLLISION_GROUP,
                        GameConstants.ENEMY_PROJECTILE_COLLISION_GROUP),
                CollisionRule.CannotCollideWith);

        //Enemies are not restricted by the same bounds as the player
        _engineInstance.AddCollisionRule(
                new CollisionGroupNamePair(
                        GameConstants.ENEMY_COLLISION_GROUP,
                        GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP),
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

        _viewPort = new ViewPort(
                ViewPort.X_RES,
                ViewPort.Y_RES,
                GameConstants.GAME_STARTING_POINT.x,
                GameConstants.GAME_STARTING_POINT.y,
                GameConstants.DEFAULT_SECTOR_WIDTH,
                GameConstants.DEFAULT_SECTOR_HEIGHT
                );

        Canvas canvas = new Canvas( ViewPort.X_RES, ViewPort.Y_RES );
        root.getChildren().add( canvas );
        _gc = canvas.getGraphicsContext2D();

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
        _stageMap = new StageMap();

        _currentRoom = StageMap.Query(CastleGardenStage.class);

        _engineInstance.SetActiveSector(_currentRoom.GetSector());
    }

    private void InitEnemySpawner()
    {
        //Temp Enemy Render
        Slim slim = new Slim(new Point(
                ViewPort.SecLocX(600),
                ViewPort.SecLocY(400)), 0.2F, 10);

        Dagron dagron = new Dagron(new Point(
                ViewPort.SecLocX(600),
                ViewPort.SecLocY(300)), 0.5F, 10);

        Skilleatin skilleatin = new Skilleatin(new Point(
                ViewPort.SecLocX(600),
                ViewPort.SecLocY(300)), 0.5F, 10);

        EnemyBase[] enemies = {slim, dagron, skilleatin};

        _enemySpawner = new EnemySpawner(_engineInstance.GetActiveSector(),
                enemies, new Rectangle(ViewPort.SecLocX(0),
                ViewPort.SecLocY(280),800,320), 180);
    }

    private void InitPlayer()
    {
        PLAYER_OBJECT = new PlayerObject(
                _currentRoom.GetPlayerStartingLocation(Side.Left),
                0.1F, 20);

        _engineInstance.GetActiveSector().AddObject(PLAYER_OBJECT,
                GameConstants.PLAYER_RENDER_GROUP,
                GameConstants.PLAYER_COLLISION_GROUP);
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
                        else if(event.getCode() == KeyCode.ESCAPE && _isBattleMode)
                        {
                            _isBattleMode = false;
                            _viewPort.Unlock();
                            QueueStageTransition(_currentRoom, Side.Bottom);
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
    //endregion

    //region Shared Rendering Methods
    public KeyFrame Render(final GraphicsContext gc)
    {
        return new KeyFrame(
                Duration.seconds(0.017),
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        gc.clearRect(0, 0, _viewPort.GetWidth(), _viewPort.GetHeight());

                        AddQueuedObjects();

                        try
                        {
                            HandleSectorChange();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        _engineInstance.CycleEngine();

                        if(_isBattleMode)
                        {
                            BattleRenderLoop(gc);
                        }
                        else
                        {
                            OverworldRenderLoop(gc);
                        }
                    }
                });
    }

    private void ShowDebugInfo(GameObject gObj, GraphicsContext gc)
    {
        gc.strokeRect(gObj.GetGameHitBoxDrawPoint().x,
                gObj.GetGameHitBoxDrawPoint().y,
                gObj.GetHitBox().width,
                gObj.GetHitBox().height);

        if(!gObj.GetIsImmobile())
        {
            gc.setFill(javafx.scene.paint.Color.CHARTREUSE);
            gc.fillText(
                    DebugHelper.BuildFormattedPropertyString(gObj),
                    ViewPort.DrawLocX(gObj.GetRight()),
                    ViewPort.DrawLocY(gObj.GetBottom()));
        }
    }

    //endregion

    //region Overworld Rendering
    private void OverworldRenderLoop(GraphicsContext gc)
    {
        HandlePlayerAttack();
        HandlePlayerMovement();

//      if(_enemySpawner.ShouldSpawn())
//        _enemySpawner.SpawnRandom();

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
                    ShowDebugInfo(gObj, gc);

                if(HandlePlayerAction(gObj, gc))
                {
                    //Player action handled.
                    _viewPort.ScrollIntoView(PLAYER_OBJECT.GetCenterPoint());

                    Backdrop sky = _currentRoom.GetSkyBox();
                    sky.NSetLocation(new Point(
                            _viewPort.GetLocation().x,
                            sky.y));
                }
                else if(HandleEnemyAction(gObj, gc))
                {
                    //Ai controlled action handled.
                }
                else if(HandlePropAction(gObj, gc))
                {

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
            PLAYER_OBJECT.SetVelocity(new VelocityVector(_lastPlayerDirection, 2));
        }
    }

    private void HandlePlayerAttack()
    {
        if(!GameConstants.IsKeyPressed(KeyCode.SPACE)) return;

        Bullet b = null;
        switch(PLAYER_OBJECT.GetProjectileDirection())
        {
            case Left:
                b = new Bullet(new Point(PLAYER_OBJECT.GetLeft() - Bullet.WIDTH - 1 ,
                        PLAYER_OBJECT.GetCenterPoint().y), PLAYER_OBJECT);
                b.SetVelocity(new VelocityVector(Math.PI, 7));
                break;
            case Right:
                b = new Bullet(new Point(PLAYER_OBJECT.GetRight() + 1,
                        PLAYER_OBJECT.GetCenterPoint().y), PLAYER_OBJECT);
                b.SetVelocity(new VelocityVector(0, 7));
                break;
        }

        _engineInstance.GetActiveSector().AddObject(b,
                GameConstants.PLAYER_PROJECTILE_RENDER_GROUP,
                GameConstants.PLAYER_PROJECTILE_COLLISION_GROUP);
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

    private boolean HandlePropAction(GameWorldObject gObj, GraphicsContext gc)
    {
        PropBase propObj;

        if(gObj instanceof PropBase)
            propObj = (PropBase)gObj;
        else
            return false;

        propObj.DrawAnimation(gc);

        return true;
    }
    //endregion

    //region Battle Rendering
    public void BattleRenderLoop(GraphicsContext gc)
    {
        _currentBattleManager.HandleKeyPress();

        //Draw all the engine managed objects
        for (int i = 0;
             i < _engineInstance.GetActiveSector().GetRenderGroupCount();
             i++)
        {
            HashSet<GameWorldObject> renderGroup
                    = _engineInstance.GetActiveSector().GetRenderGroup(i);

            if (renderGroup == null) continue;

            for (GameWorldObject gameEngObj : renderGroup)
            {
                GameObject gObj = (GameObject) gameEngObj;

                if(_showPropertyDebugMode)
                    ShowDebugInfo(gObj, gc);

                if(HandleCharacterAction(gObj, gc))
                {
                }
                else if(gObj.GetSprite() != null)
                {
                    gc.drawImage(gObj.GetSprite(),
                            gObj.GetGameDrawPoint().x,
                            gObj.GetGameDrawPoint().y,
                            gObj.GetSprite().getWidth(),
                            gObj.GetSprite().getHeight());
                }
            }
        }

        //Draw all the windows/menus
        _currentBattleManager.DrawMenus(gc);
    }

    private boolean HandleCharacterAction(GameWorldObject gObj, GraphicsContext gc)
    {
        BattleCharacterBase charObj;

        if(gObj instanceof BattleCharacterBase)
            charObj = (BattleCharacterBase)gObj;
        else
            return false;

        charObj.DrawRestingAnimation(gc);

        return true;
    }
    //endregion

    //region Queued Event Handling
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

    private void HandleSectorChange() throws Exception
    {
        if(_battleStageTransitionQueue != null)
        {
            //Create the a battle stage from the current room
            _currentBattleManager = new BattleManager(_currentRoom.CreateBattleStage(
                    new PlayerBattleCharacter(PLAYER_OBJECT),
                    _battleStageTransitionQueue));

            //Set it to active
            _engineInstance.SetActiveSector(_currentBattleManager.GetStage().GetSector());

            //Position camera
            _viewPort.SetLocation(_currentBattleManager.GetStage().GetViewLocation());
            _viewPort.Lock();

            //Signal we are in battle
            _isBattleMode = true;
            _battleStageTransitionQueue = null;
        }
        else if(_sectorTransitionQueue != null)
        {

            if(_sectorTransitionQueue.GetItem1() == _currentRoom)
            {
                //Go back to overworld
                _engineInstance.SetActiveSector(_currentRoom.GetSector());

                //Delete battle stage
                _engineInstance.DeleteSector(_currentBattleManager.GetStage().GetSector());
                _currentBattleManager = null;

                //Position camera
                _viewPort.JumpToPoint(PLAYER_OBJECT.GetCenterPoint());
                _sectorTransitionQueue = null;
            }
            else
            {
                //Remove player from current sector.
                _engineInstance.GetActiveSector().RemoveObject(PLAYER_OBJECT);

                //Change sector.
                _currentRoom = _sectorTransitionQueue.GetItem1();
                _engineInstance.SetActiveSector(_currentRoom.GetSector());

                //Add player to new sector.
                Point p = _currentRoom.GetPlayerStartingLocation(
                        _sectorTransitionQueue.GetItem2());

                PLAYER_OBJECT.NSetLocation(p);
                _engineInstance.GetActiveSector().AddObject(PLAYER_OBJECT,
                        GameConstants.PLAYER_RENDER_GROUP, GameConstants.PLAYER_COLLISION_GROUP);

                _viewPort.JumpToPoint(PLAYER_OBJECT.GetCenterPoint());

                _sectorTransitionQueue = null;
            }
        }
    }
    //endregion

    //Static Methods
    public static void QueueObjectForAddition(GameWorldObject obj, int renderGroup, String groupName)
    {
        _objectAdditionRenderGroupQueue.put(obj, renderGroup);
        _objectAdditionCollisionGroupQueue.put(obj, groupName);
    }

    public static void QueueStageTransition(OverworldStage changeTo, Side enteringFrom)
    {
        if(_sectorTransitionQueue == null)
            _sectorTransitionQueue = new Tuple<>(changeTo, enteringFrom);
    }

    public static void QueueBattleTransition(BattleCharacterGroup<EnemyBattleCharacter> enemies)
    {
        //todo take the player and enemy group that is battling and build
        //the battle stage from the current overworld stage theme.
        _battleStageTransitionQueue = enemies;
    }

    public static Sector CreateNewEngineSector()
    {
        return _engineInstance.CreateSector(
                GameConstants.DEFAULT_SECTOR_WIDTH,
                GameConstants.DEFAULT_SECTOR_HEIGHT,
                GameConstants.DEFAULT_SECTOR_GRID_UNIT_SIZE,
                GameConstants.GRAVITY,
                GravityApplication.Area);
    }

}
