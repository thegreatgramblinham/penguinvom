package GameObjects.Base;

import GameObjectBase.GameWorldObject;
import MainGame.GameManager;
import MainGame.ViewPort;

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

        return new Point(ViewPort.DrawLocX(p.x), ViewPort.DrawLocY(p.y));
    }
}
