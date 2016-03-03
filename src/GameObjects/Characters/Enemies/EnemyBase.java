package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;
import GameObjects.Characters.Enemies.AI.EnemyAIBase;
import GameObjects.Projectiles.ProjectileBase;

import java.awt.*;

public class EnemyBase extends CharacterBase
{
    //Properties
    private int _touchDamage;
    protected EnemyAIBase _ai;

    //Constructor
    public EnemyBase(Rectangle size, boolean isImmobile,
                     float mass, int health,
                     SpriteAnimation walkCycle,
                     SpriteAnimation restCycle)
    {
        super(size, isImmobile, mass, health, walkCycle, restCycle);
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

    //Public Methods
    @Override
    public void OnCollide(GameWorldObject other)
    {
        super.OnCollide(other);
    }

    //Private Methods
    private void Init()
    {
        _touchDamage = 5;
    }
}
