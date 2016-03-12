package GameObjects.Characters;

import Animation.SpriteAnimation;
import Animation.enums.AnimationOrientation;
import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;
import GameObjects.Projectiles.ProjectileBase;
import GameObjects.Projectiles.enums.ProjectileDirection;
import GeneralHelpers.ConversionHelper;
import MainGame.GameManager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class CharacterBase extends GameObject
{
    //todo Projectile hitbox?
    //Private Variables
    private AnimationOrientation _lastRenderedDirection;
    private ProjectileDirection _projectileDirection = ProjectileDirection.Right;

    //Properties
    private int _health;
    protected SpriteAnimation _walkCycle;
    protected SpriteAnimation _restCycle;

    //Constructor
    public CharacterBase(Rectangle size, boolean isImmobile, float mass, int health,
                         SpriteAnimation walkCycle, SpriteAnimation restCycle)
    {
        super(size, isImmobile, mass);
        this.SetHealth(health);
        _walkCycle = walkCycle;
        _restCycle = restCycle;
        _lastRenderedDirection = AnimationOrientation.Default;
    }

    //Set Methods
    public void SetHealth(int health)
    {
        _health = health;

        if(_health <= 0)
            OnDeath();

    }

    //Get Methods
    public int GetHealth()
    {
        return _health;
    }

    public ProjectileDirection GetProjectileDirection()
    {
        return _projectileDirection;
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

    public void DrawWalkAnimation(GraphicsContext gc)
    {
        if(this.IsMoving())
            switch(ConversionHelper.RadianToDirection(this.GetVelocity().GetRadianRotation()))
            {
                case Left:
                    _walkCycle.DrawSpriteFrame(gc, this.GetGameDrawPoint(),
                            AnimationOrientation.MirrorXAxis);
                    _lastRenderedDirection = AnimationOrientation.MirrorXAxis;
                    _projectileDirection = ProjectileDirection.Left;
                    break;
                case Right:
                    _walkCycle.DrawSpriteFrame(gc, this.GetGameDrawPoint(),
                            AnimationOrientation.Default);
                    _lastRenderedDirection = AnimationOrientation.Default;
                    _projectileDirection = ProjectileDirection.Right;
                    break;
                case Up:
                case Down:
                    _walkCycle.DrawSpriteFrame(gc, this.GetGameDrawPoint(),
                            _lastRenderedDirection);
                    break;
            }
        else if(_restCycle != null)
        {
            _restCycle.DrawSpriteFrame(gc, this.GetGameDrawPoint(), _lastRenderedDirection);
        }
        else
            _walkCycle.DrawFrameAtIndex(gc, this.GetGameDrawPoint(), 0, _lastRenderedDirection);
    }

    public void OnDeath()
    {
        this.SetNeedsDeletion(true);
    }

}
