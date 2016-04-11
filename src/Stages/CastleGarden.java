package Stages;

import GameObjectBase.enums.Side;
import SectorBase.Sector;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class CastleGarden extends RoomBase
{
    //Private Constants
    private static final int STAGE_WIDTH = 1400;
    private static final int STAGE_HEIGHT = 620;

    private final static Image SKY_TEXTURE = new Image(
            new File("src/ImageAssets/backgrounds/skybg20000.png").toURI().toString());
    private final static Image WALL_TEXTURE = new Image(
            new File("src/ImageAssets/backgrounds/largeWallSector0000.png").toURI().toString());
    private final static Image FLOOR_TEXTURE = new Image(
            new File("src/ImageAssets/backgrounds/gardenGrassSector0000.png").toURI().toString());

    //Variables

    //Constructor
    public CastleGarden(Sector sector) throws Exception
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
