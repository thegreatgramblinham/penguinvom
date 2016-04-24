package Stages.Battle;

import GameObjects.BattleCharacters.BattleCharacterBase;
import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import MainGame.GameConstants;
import SectorBase.Sector;
import Stages.StageObject;


public abstract class BattleStage extends StageObject
{

    //Variables

    //Constructor
    public BattleStage(Sector sector, PlayerBattleCharacter player,
                       BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        super(sector);
        sector.AddObject(player, GameConstants.PLAYER_RENDER_GROUP,
                GameConstants.PLAYER_COLLISION_GROUP);
    }

    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods


}
