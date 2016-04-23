package GameObjects.BattleCharacters;

import GameObjects.Characters.Player.PlayerObject;

import java.awt.*;

public class PlayerBattleCharacter extends BattleCharacterBase
{
    //Variables

    //Constructor
    public PlayerBattleCharacter(PlayerObject player)
    {
        super(player, player.GetHitBox());
    }

    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods

}
