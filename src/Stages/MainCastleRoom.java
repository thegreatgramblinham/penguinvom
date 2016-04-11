package Stages;

import GameObjectBase.enums.Side;
import SectorBase.Sector;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class MainCastleRoom extends RoomBase
{
    //Private Constants
    private static final int STAGE_WIDTH = 3200;
    private static final int STAGE_HEIGHT = 620;

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
        super(sector, SKY_TEXTURE, WALL_TEXTURE,
                FLOOR_TEXTURE, STAGE_WIDTH, STAGE_HEIGHT);
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
    protected void InitExits()
    {

    }


}
