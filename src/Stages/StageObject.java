package Stages;

import GameObjects.Environmental.Backdrop;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.InvalidObjectException;
import java.util.UUID;

public abstract class StageObject
{
    //Private Constants
    private static final int _sectorRequiredWidth = 3500;
    private static final int _sectorRequiredHeight = 900;

    //Protected Variables
    protected Sector _sector;
    //protected Backdrop _backdrop;

    //Private Variables
    private UUID _id;
    private String _roomId;

    //Constructor
    public StageObject(String roomId, Sector sector) throws Exception
    {
        if(sector.width < _sectorRequiredWidth || sector.height < _sectorRequiredHeight)
            throw new InvalidObjectException("Provided sector not large enough.");

        _sector = sector;
        _roomId = roomId;

        _id = UUID.randomUUID();

        //Init();
    }

    //Get Methods
    public Sector GetSector()
    {
        return _sector;
    }

    //public Backdrop GetBackdrop() { return _backdrop; }

    public UUID GetId()
    {
        return _id;
    }

    public String GetRoomId() { return _roomId; }

    //Set Methods

    //Abstract Methods
    protected abstract void InitObjects();
    protected abstract void InitProps();
    protected abstract void InitBackdrop();
    protected abstract void InitWall();
    protected abstract void InitFloor();
    protected abstract Image GetBackgroundTexture();
    protected abstract Image GetWallTexture();
    protected abstract Image GetFloorTexture();
    protected abstract int GetStageHeight();
    protected abstract int GetStageWidth();
    public abstract Point GetViewLocation();

    //Public Methods
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StageObject that = (StageObject) o;

        return _id.equals(that._id);

    }

    @Override
    public int hashCode()
    {
        return _id.hashCode();
    }

    //Private Methods
//    private void Init()
//    {
//        InitBackdrop();
//        InitProps();
//    }

//    private void InitBackdrop()
//    {
//        //Sky Texture
//        Image skyImage = GetBackgroundTexture();
//        if(skyImage != null)
//        {
//            _backdrop = new Backdrop(new Rectangle(
//                    ViewPort.SecLocX(0),
//                    ViewPort.SecLocY(-18),
//                    (int) GetBackgroundTexture().getWidth(),
//                    (int) GetBackgroundTexture().getHeight()),
//                    false, false, "BackSky");
//            _backdrop.SetSprite(skyImage);
//            _sector.AddObject(_backdrop, GameConstants.SKY_RENDER_GROUP,
//                    GameConstants.BACKGROUND_COLLISION_GROUP);
//        }
//
//        //Rendered Walls
//        Image wallImage = GetWallTexture();
//        if(wallImage != null)
//        {
//            Backdrop wall = new Backdrop(new Rectangle(
//                    ViewPort.SecLocX(418),
//                    ViewPort.SecLocY(0),
//                    (int) GetWallTexture().getWidth(),
//                    (int) GetWallTexture().getHeight()),
//                    true, true, "BackWall");
//            wall.SetSprite(wallImage);
//            _sector.AddObject(wall, GameConstants.ROOM_RENDER_GROUP,
//                    GameConstants.BACKGROUND_COLLISION_GROUP);
//        }
//
//        Image floorImage = GetFloorTexture();
//        if(floorImage != null)
//        {
//            Backdrop floor = new Backdrop(new Rectangle(
//                    ViewPort.SecLocX(0),
//                    ViewPort.SecLocY(280),
//                    (int) GetFloorTexture().getWidth(),
//                    (int) GetFloorTexture().getHeight()),
//                    true, false, "Floor");
//            floor.SetSprite(floorImage);
//            _sector.AddObject(floor, GameConstants.ROOM_RENDER_GROUP,
//                    GameConstants.BACKGROUND_COLLISION_GROUP);
//        }
//    }


}
