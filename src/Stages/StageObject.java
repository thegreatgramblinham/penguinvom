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

    //Private Variables
    private Image _skyTexture;
    private Image _wallTexture;
    private Image _floorTexture;

    private int _stageWidth;
    private int _stageHeight;

    //Constructor
    public StageObject(Sector sector, Image skyTexture, Image wallTexture,
                       Image floorTexture, int stageWidth, int stageHeight) throws Exception
    {
        if(sector.width < _sectorRequiredWidth || sector.height < _sectorRequiredHeight)
            throw new InvalidObjectException("Provided sector not large enough.");

        _sector = sector;
        _skyTexture = skyTexture;
        _wallTexture = wallTexture;
        _floorTexture = floorTexture;
        _stageWidth = stageWidth;
        _stageHeight = stageHeight;

        Init();
    }

    //Get Methods
    public Sector GetSector()
    {
        return _sector;
    }

    //Set Methods

    //Public Methods
    public abstract Point GetPlayerStartingLocation(Side s);

    //Private Methods
    protected void Init()
    {
        InitBackdrop();
        InitStageBounds();
        InitExits();
    }

    private void InitBackdrop()
    {
        //Sky Texture
        Backdrop skybg = new Backdrop(new Rectangle(
                ViewPort.SecLocX(0),
                ViewPort.SecLocY(110),
                (int)_skyTexture.getWidth(),
                (int)_skyTexture.getHeight()),
                false, false, "BackSky");
        skybg.SetSprite(_skyTexture);
        _sector.AddObject(skybg, GameConstants.SKY_RENDER_GROUP,
                GameConstants.BACKGROUND_GROUP);

        //Rendered Walls
        Backdrop wall = new Backdrop(new Rectangle(
                ViewPort.SecLocX(418),
                ViewPort.SecLocY(0),
                (int)_wallTexture.getWidth(),
                (int)_wallTexture.getHeight()),
                true, true, "BackWall");
        wall.SetSprite(_wallTexture);
        _sector.AddObject(wall, GameConstants.ROOM_RENDER_GROUP,
                GameConstants.BACKGROUND_GROUP);

        Backdrop floor = new Backdrop(new Rectangle(
                ViewPort.SecLocX(0),
                ViewPort.SecLocY(280),
                (int)_floorTexture.getWidth(),
                (int)_floorTexture.getHeight()),
                true, false, "Floor");
        floor.SetSprite(_floorTexture);
        _sector.AddObject(floor, GameConstants.ROOM_RENDER_GROUP,
                GameConstants.BACKGROUND_GROUP);
    }

    private void InitStageBounds()
    {
        //Non-rendered Game Bounds
        Backdrop topBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(1), _stageWidth,1), true, true,
                "TopBounds");
        _sector.AddObject(topBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_GROUP);

        Backdrop botBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(_stageHeight), _stageWidth,1),
                true, true, "BottomBounds");
        _sector.AddObject(botBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_GROUP);

        Backdrop leftBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(1),1, _stageHeight), true, true,
                "LeftBounds");
        _sector.AddObject(leftBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_GROUP);

        Backdrop rightBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(_stageWidth),
                ViewPort.SecLocY(1), 1, _stageHeight),
                true, true, "RightBounds");
        _sector.AddObject(rightBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_GROUP);
    }

    protected abstract void InitExits();

}
