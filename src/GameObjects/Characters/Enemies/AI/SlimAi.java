package GameObjects.Characters.Enemies.AI;

import GameObjects.Characters.Enemies.AI.enums.AIAction;
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
    public AIAction QueryAction()
    {
        return AIAction.Advance;
    }
}
