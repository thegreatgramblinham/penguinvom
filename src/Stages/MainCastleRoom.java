package Stages;

import GameObjectBase.enums.Side;
import GameObjects.Environmental.Backdrop;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.InvalidObjectException;

public class MainCastleRoom extends RoomBase
{
    //Private Constants
    private static final int _sectorRequiredWidth = 3500;
    private static final int _sectorRequiredHeight = 900;

    private static final int _stageWidth = 3200;
    private static final int _stageHeight = 620;

    private final static Image SKY_TEXTURE = new Image(
            new File("src/ImageAssets/backgrounds/skybg20000.png").toURI().toString());
    private final static Image WALL_TEXTURE = new Image(
            new File("src/ImageAssets/backgrounds/largeWallSector0000.png").toURI().toString());
    private final static Image FLOOR_TEXTURE = new Image(
            new File("src/ImageAssets/backgrounds/largeWoodSector0000.png").toURI().toString());


    //Private Variables

    //Constructor
    public MainCastleRoom(Sector sector) throws Exception
    {
        super(sector, SKY_TEXTURE, WALL_TEXTURE, FLOOR_TEXTURE);

        if(sector.width < _sectorRequiredWidth || sector.height < _sectorRequiredHeight)
            throw new InvalidObjectException("Provided sector not large enough.");
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public Point GetPlayerStartingLocation(Side s)
    {
        switch(s)
        {
            case Top:
            case Bottom:
            case Left:
            case Right:
                return new Point(372, 372);
        }

        return null;
    }

    //Private Methods

    @Override
    protected void InitStageBounds()
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

    @Override
    protected void InitExits()
    {

    }


}
