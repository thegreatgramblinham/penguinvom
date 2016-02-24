package GameObjects.Projectiles;

import GameObjectBase.GameWorldObject;

import java.awt.*;

public class ProjectileBase extends GameWorldObject
{
    //Properties
    private int _damage;

    //Constructor
    public ProjectileBase(Rectangle size, float mass, int damage)
    {
        super(size, false, mass);
        _damage = damage;
    }


    //Get Methods
    public int GetDamage()
    {
        return _damage;
    }

    //Set Methods
    public void SetDamage(int _damage)
    {
        this._damage = _damage;
    }
}
