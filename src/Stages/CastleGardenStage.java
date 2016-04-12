package Stages;

import GameObjectBase.enums.Side;
import SectorBase.Sector;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class CastleGardenStage extends StageObject
{
    //Variables

    //Constructor
    public CastleGardenStage(Sector sector) throws Exception
    {
        super(sector);
    }

    //Get Methods
    @Override
    protected Image GetSkyTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/skybg20000.png").toURI().toString());
    }

    @Override
    protected Image GetWallTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/largeWallSector0000.png").toURI().toString());
    }

    @Override
    protected Image GetFloorTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/gardenGrassSector0000.png").toURI().toString());
    }

    @Override
    protected int GetStageHeight()
    {
        return 1400;
    }

    @Override
    protected int GetStageWidth()
    {
        return 620;
    }

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
    protected void InitProps()
    {

    }

    @Override
    protected void InitExits()
    {

    }



}
