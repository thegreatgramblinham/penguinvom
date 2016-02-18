package GameObjects.Characters;

import GameObjectBase.GameWorldObject;

import java.awt.*;

public class CharacterBase extends GameWorldObject
{
    //todo Projectile hitbox?

    //Properties
    private int _health;

    //Constructor
    public CharacterBase(Rectangle size, boolean isImmobile, float mass, int health)
    {
        super(size, isImmobile, mass);
        this.SetHealth(health);
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

}
