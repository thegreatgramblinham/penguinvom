package GameObjects.Characters.Enemies.AI;

import GameObjects.Characters.Enemies.AI.enums.AIAction;

public abstract class EnemyAIBase
{
    //Todo create simple enemy ai that advances toward the player.

    //Abstract Methods
    public abstract void Advance();

    public abstract void Attack();

    public abstract void Retreat();

    public abstract void Stand();

    public abstract AIAction QueryAction();
}
