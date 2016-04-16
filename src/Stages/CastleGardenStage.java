package Stages;

import GameObjectBase.enums.Side;
import GameObjects.Environmental.Props.Bush;
import GameObjects.Environmental.Props.Fountain;
import GameObjects.Triggers.RoomChangeTrigger;
import MainGame.GameConstants;
import MainGame.Mapping.StageMap;
import SectorBase.Sector;
import SectorBase.enums.Direction;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Type;

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
                return new Point(500, 500);
        }

        return null;
    }

    //Private Methods
    @Override
    protected void InitProps()
    {
        Fountain f = new Fountain(new Point(570, 450));
        _sector.AddObject(f, GameConstants.PROP_RENDER_GROUP_BACK,
                GameConstants.PROP_COLLISION_GROUP);

        for(int i = 0; i < 7; i++)
        {
            Bush b = new Bush(new Point(318+(i*Bush.WIDTH), 320));
            _sector.AddObject(b, GameConstants.PROP_RENDER_GROUP_BACK,
                    GameConstants.PROP_COLLISION_GROUP);
        }


        for(int i = 0; i < 9; i++)
        {
            Bush b = new Bush(new Point(190+(i*Bush.WIDTH), 620));
            b.SetCanCollide(false);
            _sector.AddObject(b, GameConstants.PROP_RENDER_GROUP_FORWARD,
                    GameConstants.PROP_COLLISION_GROUP);
        }
    }

    @Override
    protected void InitExits()
    {
        RoomChangeTrigger rightExit = new RoomChangeTrigger
                (
                    new Rectangle(1000, 500, 60, 60),
                    CastleGardenStage.class,
                    Direction.Right,
                    Side.Left
                );

        _sector.AddObject(rightExit, GameConstants.PROP_RENDER_GROUP_FORWARD,
                GameConstants.TRIGGER_COLLISION_GROUP);
    }



}
