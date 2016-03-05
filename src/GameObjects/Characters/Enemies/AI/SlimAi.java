package GameObjects.Characters.Enemies.AI;

import GameObjects.Characters.Enemies.AI.enums.AiAction;
import GameObjects.Characters.Enemies.EnemyBase;

public class SlimAi extends EnemyAiBase
{

    //Constructor
    public SlimAi(EnemyBase body)
    {
        super(body);
    }

    //Public Methods
    @Override
    public void Advance()
    {
        super.AdvanceForward();
    }

    @Override
    public void Attack()
    {

    }

    @Override
    public void Retreat()
    {

    }

    @Override
    public void Stand()
    {

    }

    @Override
    public AiAction QueryAction()
    {
        return AiAction.Advance;
    }
}
