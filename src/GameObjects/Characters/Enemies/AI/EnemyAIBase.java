package GameObjects.Characters.Enemies.AI;

import GameObjects.Characters.Enemies.AI.enums.AiAction;
import GameObjects.Characters.Enemies.EnemyBase;
import PhysicsBase.Vectors.VelocityVector;

public abstract class EnemyAiBase
{
    //Properties
    private EnemyBase _body;

    //Constructor
    protected EnemyAiBase(EnemyBase body)
    {
        //todo potentially hold on to player obj for location data
        this.SetBody(body);
    }

    //Get Methods
    public EnemyBase GetBody()
    {
        return _body;
    }

    //Set Methods
    public void SetBody(EnemyBase body)
    {
        this._body = body;
    }

    //Abstract Methods
    public abstract void Advance();

    public abstract void Attack();

    public abstract void Retreat();

    public abstract void Stand();

    public abstract AiAction QueryAction();

    //Protected Methods
    protected void AdvanceTowardPlayer()
    {

    }

    protected void AdvanceForward()
    {
        _body.SetVelocity(new VelocityVector(0,1));
    }

}
