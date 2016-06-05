package Stages.Battle;

import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import SectorBase.Sector;
import javafx.scene.image.Image;

import java.io.File;

public class GardenBattleStage extends BattleStage
{
    //Variables

    //Constructor
    public GardenBattleStage(Sector sector, PlayerBattleCharacter player,
                             BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        super(sector, player, enemies);
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

    //Private Methods
    @Override
    protected Image GetBackgroundTexture()
    {
        return new Image(new File(
                "src/ImageAssets/backgrounds/brightSky1.png").toURI().toString());
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
                "src/ImageAssets/backgrounds/stageFloorPlaceholder.png").toURI().toString());
    }



}
