package GameObjects.Projectiles;

import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;

import java.awt.*;

public class ProjectileBase extends GameObject
{
    //Properties
    private int _damage;
    private GameObject _owner;

    //Constructor
    public ProjectileBase(Rectangle size, Rectangle hitBox, float mass, GameObject owner,
                          int damage)
    {
        super(size, hitBox, false, mass);
        _damage = damage;
        _owner = owner;
    }


    //Get Methods
    public int GetDamage()
    {
        return _damage;
    }

    public GameObject GetOwner()
    {
        return _owner;
    }

    //Set Methods
    public void SetDamage(int _damage)
    {
        this._damage = _damage;
    }

    public void SetOwner(GameObject _owner)
    {
        this._owner = _owner;
    }
}
