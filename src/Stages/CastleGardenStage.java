package Stages;

import GameObjectBase.enums.Side;
import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import GameObjects.Characters.Enemies.Skilleatin;
import GameObjects.Environmental.Props.Bush;
import GameObjects.Environmental.Props.Fountain;
import GameObjects.Triggers.RoomChangeTrigger;
import MainGame.GameConstants;
import MainGame.GameManager;
import MainGame.ViewPort;
import SectorBase.Sector;
import SectorBase.enums.Direction;
import Stages.Battle.BattleStage;
import Stages.Battle.GardenBattleStage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class CastleGardenStage extends OverworldStage
{
    //Variables

    //Constructor
    public CastleGardenStage(Sector sector) throws Exception
    {
        super(sector);
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
//        return new Image(new File(
//                "src/ImageAssets/backgrounds/castleWallSector1.png").toURI().toString());
        return null;
    }

    @Override
    protected Image GetFloorTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/gardenGrassSector1.png").toURI().toString());
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
                return new Point(
                        ViewPort.SecLocX(280),
                        ViewPort.SecLocY(372));
            case Right:
                return new Point(
                        ViewPort.SecLocX(1172),
                        ViewPort.SecLocY(372));

        }

        return null;
    }

    @Override
    public BattleStage CreateBattleStage(
            PlayerBattleCharacter player,
            BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        return new GardenBattleStage(GameManager.CreateNewEngineSector(), player, enemies);
    }

    //Private Methods
    @Override
    protected void InitProps()
    {
        Fountain f = new Fountain(
                new Point(
                        ViewPort.SecLocX(442),
                        ViewPort.SecLocY(322)));
        _sector.AddObject(f, GameConstants.PROP_RENDER_GROUP_MID,
                GameConstants.PROP_COLLISION_GROUP);

        for(int i = 0; i < 7; i++)
        {
            Bush b = new Bush(new Point(
                    ViewPort.SecLocX(190+(i*Bush.WIDTH)),
                    ViewPort.SecLocY(192)));
            _sector.AddObject(b, GameConstants.PROP_RENDER_GROUP_BACK,
                    GameConstants.PROP_COLLISION_GROUP);
        }


        for(int i = 0; i < 9; i++)
        {
            Bush b = new Bush(new Point(
                    ViewPort.SecLocX(62+(i*Bush.WIDTH)),
                    ViewPort.SecLocY(490)));
            b.SetCanCollide(false);
            _sector.AddObject(b, GameConstants.PROP_RENDER_GROUP_MID,
                    GameConstants.PROP_COLLISION_GROUP);
        }

        Skilleatin s = new Skilleatin(new Point(
                        ViewPort.SecLocX(900),
                        ViewPort.SecLocY(400)), 1.0F, 10);
        _sector.AddObject(s, GameConstants.ENEMY_RENDER_GROUP,
                GameConstants.ENEMY_COLLISION_GROUP);
    }

    @Override
    protected void InitExits()
    {
        RoomChangeTrigger rightExit = new RoomChangeTrigger
                (
                    new Rectangle(
                            ViewPort.SecLocX(1250),
                            ViewPort.SecLocY(345),
                            60,
                            120),
                    CastleGardenStage.class,
                    Direction.Right,
                    Side.Left
                );

        _sector.AddObject(rightExit, GameConstants.PROP_RENDER_GROUP_MID,
                GameConstants.TRIGGER_COLLISION_GROUP);
    }
}
