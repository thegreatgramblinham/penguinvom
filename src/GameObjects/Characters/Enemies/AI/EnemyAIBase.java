package GameObjects.Characters.Enemies.AI;

import GameObjects.Characters.Enemies.AI.enums.AiAction;
import GameObjects.Characters.Enemies.EnemyBase;
import PhysicsBase.Vectors.VelocityVector;

public abstract class EnemyAiBase
{
    //Protected Variables
    protected int _queryCounter;

    //Private Variables
    private int _counterReset;

    //Properties
    private EnemyBase _body;

    //Constructor
    protected EnemyAiBase(EnemyBase body, int counterReset)
    {
        this.SetBody(body);
        _counterReset = counterReset;
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

    protected abstract AiAction DetermineAction();

    //Protected Methods
    public AiAction QueryAction()
    {
        if(_queryCounter < _counterReset)
            _queryCounter++;
        else
            _queryCounter = 0;

        return DetermineAction();
    }

    protected void AdvanceTowardPlayer()
    {

    }

    protected void AdvanceForward()
    {
        _body.SetVelocity(new VelocityVector(Math.PI, 0.5));
    }

}
