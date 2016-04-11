package Stages;

import GameObjectBase.enums.Side;
import GameObjects.Environmental.Backdrop;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;
import javafx.scene.image.Image;

import java.awt.*;

public abstract class RoomBase
{
    //Variables
    protected Sector _sector;

    private Image _skyTexture;
    private Image _wallTexture;
    private Image _floorTexture;

    //Constructor
    public RoomBase(Sector sector, Image skyTexture, Image wallTexture,
                    Image floorTexture)
    {
        _sector = sector;
        _skyTexture = skyTexture;
        _wallTexture = wallTexture;
        _floorTexture = floorTexture;

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

    protected abstract void InitStageBounds();

    protected abstract void InitExits();

}
