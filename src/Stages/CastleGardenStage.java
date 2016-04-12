package Stages;

import GameObjectBase.enums.Side;
import GameObjects.Environmental.Props.Fountain;
import MainGame.GameConstants;
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
    protected int GetStageWidth()
    {
        return 1400;
    }

    @Override
    protected int GetStageHeight()
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
        Fountain f = new Fountain(new Point(580, 450));
        _sector.AddObject(f, GameConstants.PROP_RENDER_GROUP,
                GameConstants.PROP_GROUP);
    }

    @Override
    protected void InitExits()
    {

    }



}
