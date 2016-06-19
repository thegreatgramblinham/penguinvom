package Stages;

import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import GameObjects.Environmental.Props.PropBase;
import MainGame.GameConstants;
import MainGame.GameManager;
import Stages.Objects.Scenery.Backdrop;
import Stages.Objects.Scenery.Floor;
import Stages.Objects.Scenery.Wall;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

public class XmlBuiltBattleStage extends BattleStage
{
    //Private Variables
    private int _stageWidth;
    private int _stageHeight;
    private Point _viewPortStart;

    private ArrayList<Backdrop> _backdrops;
    private ArrayList<Floor> _floors;
    private ArrayList<Wall> _walls;
    private ArrayList<PropBase> _props;

    //Constructor
    public XmlBuiltBattleStage(String roomId, int stageWidth, int stageHeight, Point viewPortStart,
            ArrayList<Backdrop> backdrops, ArrayList<Floor> floors, ArrayList<Wall> walls,
            ArrayList<PropBase> props, PlayerBattleCharacter player,
            BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        super(roomId, GameManager.CreateNewEngineSector(stageWidth, stageHeight), player, enemies);

        _stageWidth = stageWidth;
        _stageHeight = stageHeight;

        _viewPortStart = viewPortStart;

        _backdrops = backdrops;
        _floors = floors;
        _walls = walls;
        _props = props;

        InitObjects();
    }

    //Get Methods
    @Override
    protected int GetStageWidth()
    {
        return _stageWidth;
    }

    @Override
    protected int GetStageHeight()
    {
        return _stageHeight;
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

    @Override
    public Point GetViewLocation()
    {
        return _viewPortStart;
    }

    @Override
    public Point GetPlayerCharacterLocation()
    {
        return new Point(0,0);
    }

    @Override
    public Point GetPartnerCharacterLocation()
    {
        return new Point(0,0);
    }

    @Override
    public Point[] GetEnemyCharacterLocation()
    {
        Point[] pts = new Point[1];
        pts[0] = new Point(0,0);

        return pts;
    }

    //Private Methods
    @Override
    protected void InitObjects()
    {
        InitBackdrop();
        InitFloor();
        InitWall();
        InitProps();
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
