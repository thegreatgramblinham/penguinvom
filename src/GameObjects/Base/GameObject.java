package GameObjects.Base;

import GameObjectBase.GameWorldObject;
import MainGame.GameManager;

import java.awt.*;

public class GameObject extends GameWorldObject
{
    public GameObject(Rectangle size, boolean isImmobile, float mass)
    {
        super(size, isImmobile, mass);
    }

    public Point GetGameDrawPoint()
    {
        Point p = this.getLocation();

        return new Point(GameManager.DrawLocX(p.x), GameManager.DrawLocY(p.y));
    }
}
