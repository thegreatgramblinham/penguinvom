package Stages;

import GameObjectBase.enums.Side;
import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Environmental.Props.PropBase;
import GameObjects.Triggers.RoomChangeTrigger;
import MainGame.GameConstants;
import MainGame.GameManager;
import SectorBase.enums.Direction;
import Stages.Battle.BattleStage;
import Stages.Objects.Scenery.Backdrop;
import Stages.Objects.Scenery.Floor;
import Stages.Objects.Scenery.Wall;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class XmlBuiltStage extends OverworldStage
{
    //Private Variables
    private int _stageWidth;
    private int _stageHeight;
    private HashMap<Side, Rectangle> _exits;
    private HashMap<Side, Rectangle> _entrances;
    private ArrayList<Backdrop> _backdrops;
    private ArrayList<Floor> _floors;
    private ArrayList<Wall> _walls;
    private ArrayList<PropBase> _props;
    private ArrayList<EnemyBase> _enemies;

    //Constructor
    public XmlBuiltStage(int stageWidth, int stageHeight, HashMap<Side, Rectangle> exits,
            HashMap<Side, Rectangle> entrances, ArrayList<Backdrop> backdrops,
            ArrayList<Floor> floors, ArrayList<Wall> walls, ArrayList<PropBase> props,
            ArrayList<EnemyBase> enemies) throws Exception
    {
        super(GameManager.CreateNewEngineSector(stageWidth, stageHeight));

        _stageWidth = stageWidth;
        _stageHeight = stageHeight;

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
    public Point GetPlayerStartingLocation(Side s)
    {
        return new Point(900, 750);
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
        return null;
    }

    //Private Methods
    @Override
    protected void InitObjects()
    {
        InitExits();

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

            RoomChangeTrigger rightExit = new RoomChangeTrigger
                    (
                            rect,
                            StageConstants.CastleGarden,
                            Direction.Right, //TODO need to make this dynamic, this is just for testing
                            s
                    );

            this.GetSector().AddObject(
                    rightExit,
                    GameConstants.TRIGGER_RENDER_GROUP,
                    GameConstants.TRIGGER_COLLISION_GROUP);
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
