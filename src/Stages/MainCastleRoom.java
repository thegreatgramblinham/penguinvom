package Stages;

import GameObjects.Environmental.Backdrop;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.InvalidObjectException;

public class MainCastleRoom
{
    //Private Constants
    private static final int _sectorRequiredWidth = 3500;
    private static final int _sectorRequiredHeight = 900;

    private static final int _stageWidth = 3200;
    private static final int _stageHeight = 620;

    //Private Variables
    private Sector _sector;

    //Constructor
    public MainCastleRoom(Sector sector) throws Exception {

        if(sector.width < _sectorRequiredWidth || sector.height < _sectorRequiredHeight)
            throw new InvalidObjectException("Provided sector not large enough.");

        _sector = sector;

        Init();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    public Sector GetSector()
    {
        return _sector;
    }

    //Private Methods
    private void Init()
    {
        InitBackdrop();
        InitStageBounds();
    }

    private void InitBackdrop()
    {
        //Sky Texture
        Backdrop skybg = new Backdrop(new Rectangle(
                ViewPort.SecLocX(0),
                ViewPort.SecLocY(110),800,280), false, false, "BackSky");
        skybg.SetSprite(new Image(new File("src/ImageAssets/backgrounds/skybg20000.png")
                .toURI().toString()));
        _sector.AddObject(skybg, GameConstants.SKY_RENDER_GROUP,
                GameConstants.BACKGROUND_GROUP);

        //Rendered Walls
        Backdrop bg = new Backdrop(new Rectangle(
                ViewPort.SecLocX(418),
                ViewPort.SecLocY(0),2364,280), true, true, "BackWall");
        bg.SetSprite(new Image(new File("src/ImageAssets/backgrounds/largeWallSector0000.png")
                .toURI().toString()));
        _sector.AddObject(bg, GameConstants.ROOM_RENDER_GROUP,
                GameConstants.BACKGROUND_GROUP);

        Backdrop floor = new Backdrop(new Rectangle(
                ViewPort.SecLocX(0),
                ViewPort.SecLocY(280),3200,320), true, false, "Floor");
        floor.SetSprite(new Image(new File("src/ImageAssets/backgrounds/largeWoodSector0000.png")
                .toURI().toString()));
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

}
