package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;
import GameObjects.Projectiles.ProjectileBase;

import java.awt.*;

public class EnemyBase extends CharacterBase
{

    public EnemyBase(Rectangle size, boolean isImmobile,
                     float mass, int health,
                     SpriteAnimation walkCycle, SpriteAnimation restCycle)
    {
        super(size, isImmobile, mass, health, walkCycle, restCycle);
    }

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
}
