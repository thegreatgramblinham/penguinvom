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

    //Abstract Methods
    protected abstract void InitProps();
    protected abstract Image GetSkyTexture();
    protected abstract Image GetWallTexture();
    protected abstract Image GetFloorTexture();
    protected abstract int GetStageHeight();
    protected abstract int GetStageWidth();

    //Private Methods
    private void Init()
    {
        InitBackdrop();
        InitProps();
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


}
