package GameObjects.Base;

import GameObjectBase.GameWorldObject;

import java.awt.*;

public class GameObject extends GameWorldObject
{
    public boolean needsDeletion;

    public GameObject(Rectangle size, boolean isImmobile, float mass)
    {
        super(size, isImmobile, mass);
    }
}
