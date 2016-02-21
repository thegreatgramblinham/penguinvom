package GameObjects.Characters;

import Animation.SpriteAnimation;
import GameObjectBase.GameWorldObject;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class CharacterBase extends GameWorldObject
{
    //todo Projectile hitbox?

    //Properties
    private int _health;
    protected SpriteAnimation _walkCycle;

    //Constructor
    public CharacterBase(Rectangle size, boolean isImmobile, float mass, int health,
                         SpriteAnimation walkCycle)
    {
        super(size, isImmobile, mass);
        this.SetHealth(health);
        _walkCycle = walkCycle;
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
            _walkCycle.DrawSpriteFrame(gc, this.getLocation());
        else
            _walkCycle.DrawFrameAtIndex(gc, this.getLocation(), 0);
    }
}
