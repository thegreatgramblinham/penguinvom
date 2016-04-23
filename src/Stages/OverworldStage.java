package Stages;

import GameObjectBase.enums.Side;
import GameObjects.Environmental.Backdrop;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;

import java.awt.*;

public abstract class OverworldStage extends StageObject
{
    //Constructor
    public OverworldStage(Sector sector) throws Exception
    {
        super(sector);

        Init();
    }

    //Public Methods
    public abstract Point GetPlayerStartingLocation(Side s);

    //Abstract Methods
    protected abstract void InitExits();

    //Private Methods
    private void Init()
    {
        InitStageBounds();
        InitExits();
    }

    private void InitStageBounds()
    {
        //Non-rendered Game Bounds
        Backdrop topBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(1), GetStageWidth(),1), true, true,
                "TopBounds");
        _sector.AddObject(topBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP);

        Backdrop botBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(GetStageHeight()), GetStageWidth(),1),
                true, true, "BottomBounds");
        _sector.AddObject(botBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP);

        Backdrop leftBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(1),1, GetStageHeight()), true, true,
                "LeftBounds");
        _sector.AddObject(leftBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP);

        Backdrop rightBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(GetStageWidth()),
                ViewPort.SecLocY(1), 1, GetStageHeight()),
                true, true, "RightBounds");
        _sector.AddObject(rightBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP);
    }

}
