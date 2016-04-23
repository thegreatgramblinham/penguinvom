package Stages.Battle;

import GameObjectBase.enums.Side;
import SectorBase.Sector;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class GardenBattleStage extends BattleStage
{
    //Variables

    //Constructor
    public GardenBattleStage(Sector sector) throws Exception
    {
        super(sector);
    }

    //Get Methods
    @Override
    protected int GetStageHeight()
    {
        return 620;
    }

    @Override
    protected int GetStageWidth()
    {
        return 1100;
    }

    //Set Methods

    //Public Methods
    @Override
    public Point GetPlayerStartingLocation(Side s)
    {
        return new Point(500,500);
    }

    //Private Methods
    @Override
    protected Image GetSkyTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/brightSky0000.png").toURI().toString());
    }

    @Override
    protected Image GetWallTexture()
    {
        return null;
    }

    @Override
    protected Image GetFloorTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/battleStage/stageFloorPlaceHolder.png").toURI().toString());
    }

    @Override
    protected void InitProps()
    {

    }


}
