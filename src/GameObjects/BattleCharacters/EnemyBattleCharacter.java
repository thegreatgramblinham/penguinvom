package GameObjects.BattleCharacters;

import GameObjects.Characters.Enemies.EnemyBase;
import javafx.scene.canvas.GraphicsContext;

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
    @Override
    public void DrawRestingAnimation(GraphicsContext gc)
    {

    }

    //Private Methods

}
