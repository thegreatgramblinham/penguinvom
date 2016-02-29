package GameObjects.Characters;

import Animation.SpriteAnimation;
import Animation.enums.AnimationOrientation;
import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;
import GameObjects.Projectiles.ProjectileBase;
import GeneralHelpers.ConversionHelper;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class CharacterBase extends GameObject
{
    //todo Projectile hitbox?
    //Private Variables
    private AnimationOrientation _lastRenderedDirection;

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
            this.SetNeedsDeletion(true);
    }

    //Get Methods
    public int GetHealth()
    {
        return _health;
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
                    _walkCycle.DrawSpriteFrame(gc, this.getLocation(),
                            AnimationOrientation.MirrorXAxis);
                    _lastRenderedDirection = AnimationOrientation.MirrorXAxis;
                    break;
                case Right:
                    _walkCycle.DrawSpriteFrame(gc, this.getLocation(),
                            AnimationOrientation.Default);
                    _lastRenderedDirection = AnimationOrientation.Default;
                    break;
                case Up:
                case Down:
                    _walkCycle.DrawSpriteFrame(gc, this.getLocation(),
                            _lastRenderedDirection);
                    break;
            }
        else if(_restCycle != null)
        {
            _restCycle.DrawSpriteFrame(gc, this.getLocation(), _lastRenderedDirection);
        }
        else
            _walkCycle.DrawFrameAtIndex(gc, this.getLocation(), 0, _lastRenderedDirection);
    }
}
