package GameObjects.BattleCharacters;

import Animation.enums.AnimationOrientation;
import GameObjects.Characters.Enemies.EnemyBase;
import javafx.scene.canvas.GraphicsContext;

public class EnemyBattleCharacter extends BattleCharacterBase
{
    //Variables
    EnemyBase _enemy;

    //Constructor
    public EnemyBattleCharacter(EnemyBase enemy)
    {
        super(enemy.GetBounds(), enemy.GetHitBox());

        _enemy = enemy;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void DrawRestingAnimation(GraphicsContext gc)
    {
        _enemy.DrawRestingAnimation(gc, this.GetGameDrawPoint(), AnimationOrientation.MirrorXAxis);
    }

    //Private Methods

}
