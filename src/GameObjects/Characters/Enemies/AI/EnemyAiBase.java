package GameObjects.Characters.Enemies.AI;

import GameObjects.Characters.Enemies.AI.enums.AiAction;
import GameObjects.Characters.Enemies.EnemyBase;
import MainGame.GameManager;
import PhysicsBase.Vectors.VelocityVector;

import java.awt.*;

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

    public int GetCounterReset()
    {
        return _counterReset;
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
        Point playerLocation = GameManager.PLAYER_OBJECT.GetCenterPoint();

        if(_body.GetCenterPoint().y > playerLocation.y)
        _body.SetVelocity(new VelocityVector((3*Math.PI)/2, _body.GetMovementSpeed()));
        else if(_body.GetCenterPoint().y < playerLocation.y)
            _body.SetVelocity(new VelocityVector(Math.PI/2, _body.GetMovementSpeed()));
        else if(_body.GetCenterPoint().x > playerLocation.x)
            _body.SetVelocity(new VelocityVector(Math.PI, _body.GetMovementSpeed()));
        else if(_body.GetCenterPoint().x < playerLocation.x)
            _body.SetVelocity(new VelocityVector(0, _body.GetMovementSpeed()));
    }

    protected void AdvanceForward()
    {
        _body.SetVelocity(new VelocityVector(Math.PI, 0.5));
    }

}
