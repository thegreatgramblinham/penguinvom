package Stages;

import GameObjectBase.enums.Side;
import SectorBase.Sector;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class MainCastleStage extends StageObject
{
    //Private Variables

    //Constructor
    public MainCastleStage(Sector sector) throws Exception
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
                "src/ImageAssets/backgrounds/largeWoodSector0000.png").toURI().toString());
    }

    @Override
    protected int GetStageHeight()
    {
        return 3200;
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
