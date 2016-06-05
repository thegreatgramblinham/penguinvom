package Stages;

import GameObjectBase.enums.Side;
import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import GameObjects.Triggers.RoomChangeTrigger;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;

import SectorBase.enums.Direction;
import Stages.Battle.BattleStage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class MainCastleStage extends OverworldStage
{
    //Private Variables

    //Constructor
    public MainCastleStage(Sector sector) throws Exception
    {
        super(sector);
    }

    @Override
    protected void InitObjects()
    {

    }

    //Get Methods
    @Override
    protected Image GetBackgroundTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/brightSky1.png").toURI().toString());
    }

    @Override
    protected Image GetWallTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/castleWallSector1.png").toURI().toString());
    }

    @Override
    protected Image GetFloorTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/castleFloorSector1.png").toURI().toString());
    }

    @Override
    protected int GetStageWidth()
    {
        return 3200;
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
                return new Point(
                        ViewPort.SecLocX(280),
                        ViewPort.SecLocY(372));
        }

        return null;
    }

    @Override
    public BattleStage CreateBattleStage(
            PlayerBattleCharacter player,
            BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        return null;
    }

    //Private Methods
    @Override
    protected void InitProps()
    {

    }

    @Override
    protected void InitBackdrop()
    {

    }

    @Override
    protected void InitWall()
    {

    }

    @Override
    protected void InitFloor()
    {

    }

    @Override
    protected void InitExits()
    {
        RoomChangeTrigger leftExit = new RoomChangeTrigger
                (
                        new Rectangle(
                                ViewPort.SecLocX(200),
                                ViewPort.SecLocY(345),
                                60,
                                120),
                        MainCastleStage.class,
                        Direction.Left,
                        Side.Right
                );
        _sector.AddObject(leftExit, GameConstants.PROP_RENDER_GROUP_MID,
                GameConstants.TRIGGER_COLLISION_GROUP);
    }




}
