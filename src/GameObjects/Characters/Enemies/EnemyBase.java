package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;
import GameObjects.Characters.Enemies.AI.EnemyAiBase;
import GameObjects.Characters.Enemies.AI.enums.AiAction;
import GameObjects.Characters.Enemies.AI.interfaces.IAiController;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class EnemyBase extends CharacterBase implements IAiController, Cloneable
{
    //Properties
    private int _touchDamage;
    protected EnemyAiBase _ai;

    //Constructor
    public EnemyBase(Rectangle size, Rectangle hitBox, boolean isImmobile,
                     float mass, int health, float movementSpeed,
                     SpriteAnimation walkCycle,
                     SpriteAnimation restCycle)
    {
        super(size, hitBox, isImmobile, mass, health,
                movementSpeed, walkCycle, restCycle);
        Init();
    }

    //Get Methods
    public int GetTouchDamage()
    {
        return _touchDamage;
    }

    //Set Methods
    public void SetTouchDamage(int _touchDamage)
    {
        this._touchDamage = _touchDamage;
    }

    public void SetAi(EnemyAiBase _ai)
    {
        this._ai = _ai;
    }

    //Public Methods
    @Override
    public void OnCollide(GameWorldObject other)
    {
        super.OnCollide(other);
    }

    @Override
    public boolean PerformAndDrawAction(GraphicsContext gc)
    {
        if(_ai == null) return false;

        AiAction act = _ai.QueryAction();

        switch(act)
        {
            case Advance:
                this.Advance(gc);
                break;
            case Attack:
                this.Attack(gc);
                break;
            case Retreat:
                this.Retreat(gc);
                break;
            case Stand:
                this.Stand(gc);
                break;
        }

        return true;
    }

    //Private Methods
    private void Init()
    {
        _touchDamage = 5;
    }


}
