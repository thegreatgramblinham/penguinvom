package GameObjects.Characters;

import Animation.SpriteAnimation;
import Animation.enums.AnimationOrientation;
import GameObjectBase.GameWorldObject;
import GeneralHelpers.ConversionHelper;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class CharacterBase extends GameWorldObject
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
    }

    //Get Methods
    public int GetHealth()
    {
        return _health;
    }

    //Public Methods
    @Override
    public void OnCollide(GameWorldObject e)
    {
        System.out.println("On Collision Event Here");
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
