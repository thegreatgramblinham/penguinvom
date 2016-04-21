package Stages;

import GameObjectBase.enums.Side;
import GameObjects.Environmental.Backdrop;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.InvalidObjectException;

public abstract class StageObject
{
    //Private Constants
    private static final int _sectorRequiredWidth = 3500;
    private static final int _sectorRequiredHeight = 900;

    //Protected Variables
    protected Sector _sector;
    protected Backdrop _skyBox;

    //Private Variables

    //Constructor
    public StageObject(Sector sector) throws Exception
    {
        if(sector.width < _sectorRequiredWidth || sector.height < _sectorRequiredHeight)
            throw new InvalidObjectException("Provided sector not large enough.");

        _sector = sector;

        Init();
    }

    //Get Methods
    public Sector GetSector()
    {
        return _sector;
    }

    public Backdrop GetSkyBox() { return _skyBox; }

    //Set Methods

    //Public Methods
    public abstract Point GetPlayerStartingLocation(Side s);

    //Abstract Methods
    protected abstract void InitExits();
    protected abstract void InitProps();
    protected abstract Image GetSkyTexture();
    protected abstract Image GetWallTexture();
    protected abstract Image GetFloorTexture();
    protected abstract int GetStageHeight();
    protected abstract int GetStageWidth();

    //Private Methods
    protected void Init()
    {
        InitBackdrop();
        InitStageBounds();
        InitProps();
        InitExits();
    }

    private void InitBackdrop()
    {
        //Sky Texture
        Image skyImage = GetSkyTexture();
        if(skyImage != null)
        {
            _skyBox = new Backdrop(new Rectangle(
                    ViewPort.SecLocX(0),
                    ViewPort.SecLocY(-18),
                    (int) GetSkyTexture().getWidth(),
                    (int) GetSkyTexture().getHeight()),
                    false, false, "BackSky");
            _skyBox.SetSprite(skyImage);
            _sector.AddObject(_skyBox, GameConstants.SKY_RENDER_GROUP,
                    GameConstants.BACKGROUND_COLLISION_GROUP);
        }

        //Rendered Walls
        Image wallImage = GetWallTexture();
        if(wallImage != null)
        {
            Backdrop wall = new Backdrop(new Rectangle(
                    ViewPort.SecLocX(418),
                    ViewPort.SecLocY(0),
                    (int) GetWallTexture().getWidth(),
                    (int) GetWallTexture().getHeight()),
                    true, true, "BackWall");
            wall.SetSprite(wallImage);
            _sector.AddObject(wall, GameConstants.ROOM_RENDER_GROUP,
                    GameConstants.BACKGROUND_COLLISION_GROUP);
        }

        Image floorImage = GetFloorTexture();
        if(floorImage != null)
        {
            Backdrop floor = new Backdrop(new Rectangle(
                    ViewPort.SecLocX(0),
                    ViewPort.SecLocY(280),
                    (int) GetFloorTexture().getWidth(),
                    (int) GetFloorTexture().getHeight()),
                    true, false, "Floor");
            floor.SetSprite(floorImage);
            _sector.AddObject(floor, GameConstants.ROOM_RENDER_GROUP,
                    GameConstants.BACKGROUND_COLLISION_GROUP);
        }
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
