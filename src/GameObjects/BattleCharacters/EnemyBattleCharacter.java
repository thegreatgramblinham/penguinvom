package GameObjects.BattleCharacters;

import GameObjects.Characters.Enemies.EnemyBase;

import java.awt.*;

public class EnemyBattleCharacter extends BattleCharacterBase
{
    //Variables

    //Constructor
    public EnemyBattleCharacter(EnemyBase enemy)
    {
        super(enemy, enemy.GetHitBox());
    }

    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods

}
