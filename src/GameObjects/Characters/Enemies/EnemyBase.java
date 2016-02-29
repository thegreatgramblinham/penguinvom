package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;
import GameObjects.Projectiles.ProjectileBase;

import java.awt.*;

public class EnemyBase extends CharacterBase
{
    //Properties
    private int _touchDamage;

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
        if(other instanceof ProjectileBase)
        {
            ProjectileBase projectile = (ProjectileBase) other;

            if(projectile.GetOwner() != this)
                this.SetHealth(
                        this.GetHealth() - projectile.GetDamage());

        }
    }

    //Private Methods
    private void Init()
    {
        _touchDamage = 5;
    }
}
