package Stages;

import GameObjectBase.enums.Side;
import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Environmental.Props.PropBase;
import GameObjects.Triggers.RoomChangeTrigger;
import GeneralHelpers.ConversionHelper;
import Global.Tuple;
import MainGame.GameConstants;
import MainGame.GameManager;
import Stages.Objects.Scenery.Backdrop;
import Stages.Objects.Scenery.Floor;
import Stages.Objects.Scenery.Wall;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class XmlBuiltOverworldStage extends OverworldStage
{
    //Private Variables
    private int _stageWidth;
    private int _stageHeight;
    private Point _viewPortStart;
    private HashMap<Side, Rectangle> _exits;
    private HashMap<Side, Tuple<Integer, Rectangle>> _entrances;
    private ArrayList<Backdrop> _backdrops;
    private ArrayList<Floor> _floors;
    private ArrayList<Wall> _walls;
    private ArrayList<PropBase> _props;
    private ArrayList<EnemyBase> _enemies;

    //Constructor
    public XmlBuiltOverworldStage(String roomId, int stageWidth, int stageHeight, Point viewPortStart,
            HashMap<Side, Rectangle> exits, HashMap<Side, Tuple<Integer, Rectangle>> entrances,
            ArrayList<Backdrop> backdrops, ArrayList<Floor> floors, ArrayList<Wall> walls,
            ArrayList<PropBase> props, ArrayList<EnemyBase> enemies) throws Exception
    {
        super(roomId, GameManager.CreateNewEngineSector(stageWidth, stageHeight));

        _stageWidth = stageWidth;
        _stageHeight = stageHeight;
        _viewPortStart = viewPortStart;

        _exits = exits;
        _entrances = entrances;

        _backdrops = backdrops;
        _floors = floors;
        _walls = walls;
        _props = props;
        _enemies = enemies;

        InitObjects();
    }

    //Get Methods
    @Override
    protected int GetStageHeight()
    {
        return _stageHeight;
    }

    @Override
    protected int GetStageWidth()
    {
        return _stageWidth;
    }

    @Override
    public Point GetViewLocation()
    {
        return _viewPortStart;
    }

    @Override
    public Tuple<Integer, Point> GetPlayerStartingLocation(Side s)
    {
        return _entrances.containsKey(s)
                ? new Tuple<>( _entrances.get(s).GetItem1(),
                    _entrances.get(s).GetItem2().getLocation())
                : null;
    }

    @Override
    protected Image GetBackgroundTexture()
    {
        return _backdrops == null || _backdrops.size() == 0
                ? null
                : _backdrops.get(0).GetSprite();
    }

    @Override
    protected Image GetWallTexture()
    {
        return _walls == null || _walls.size() == 0
                ? null
                : _walls.get(0).GetSprite();
    }

    @Override
    protected Image GetFloorTexture()
    {
        return _floors == null || _floors.size() == 0
                ? null
                : _floors.get(0).GetSprite();
    }

    //Public Methods
    @Override
    public BattleStage CreateBattleStage(PlayerBattleCharacter player,
            BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        String id = StageConstants.GetBattleStageId(this.GetRoomId());

        if(id == null) return null;

        return StageBuilder.BuildBattleStage(id, player, enemies);
    }

    //Private Methods
    @Override
    protected void InitObjects()
    {
        InitExits();
        InitEnemies();

        InitBackdrop();
        InitFloor();
        InitWall();
        InitProps();
    }

    @Override
    protected void InitExits()
    {
        for(Side s : _exits.keySet())
        {
            Rectangle rect = _exits.get(s);

            RoomChangeTrigger exit = new RoomChangeTrigger
                    (
                            rect,
                            GetRoomId(),
                            ConversionHelper.GetDirectionFromSide(s),
                            ConversionHelper.GetOppositeSide(s)
                    );

            this.GetSector().AddObject(
                    exit,
                    GameConstants.TRIGGER_RENDER_GROUP,
                    GameConstants.TRIGGER_COLLISION_GROUP);
        }
    }

    @Override
    protected void InitEnemies()
    {
        if(_enemies == null) return;

        for(EnemyBase enemy : _enemies)
        {
            this.GetSector().AddObject(
                    enemy,
                    GameConstants.ENEMY_RENDER_GROUP,
                    GameConstants.ENEMY_COLLISION_GROUP
            );
        }
    }

    @Override
    protected void InitBackdrop()
    {
        if(_backdrops == null) return;

        for (Backdrop backdrop : _backdrops)
        {
            this.GetSector().AddObject(
                    backdrop,
                    GameConstants.SKY_RENDER_GROUP,
                    GameConstants.NO_COLLISION_GROUP
            );
        }
    }

    @Override
    protected void InitFloor()
    {
        if(_floors == null) return;

        for (Floor floor : _floors)
        {
            this.GetSector().AddObject(
                    floor,
                    GameConstants.ROOM_RENDER_GROUP,
                    GameConstants.NO_COLLISION_GROUP
            );
        }
    }


    @Override
    protected void InitWall()
    {
        if(_walls == null) return;

        for (Wall wall : _walls)
        {
            this.GetSector().AddObject(
                    wall,
                    GameConstants.ROOM_RENDER_GROUP,
                    GameConstants.BACKGROUND_COLLISION_GROUP
            );
        }
    }

    @Override
    protected void InitProps()
    {
        if(_props == null) return;

        for(PropBase prop : _props)
        {
            this.GetSector().AddObject(
                    prop,
                    GameConstants.PROP_RENDER_GROUP_BACK,
                    GameConstants.PROP_COLLISION_GROUP);
        }
    }
}
