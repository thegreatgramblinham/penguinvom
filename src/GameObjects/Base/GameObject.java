package GameObjects.Base;

import GameObjectBase.GameWorldObject;
import MainGame.GameManager;
import MainGame.ViewPort;

import java.awt.*;

public class GameObject extends GameWorldObject
{
    public GameObject(Rectangle size, Rectangle hitBox, boolean isImmobile, float mass)
    {
        super(size, hitBox, isImmobile, mass);
    }

    public Point GetGameDrawPoint()
    {
        Point p = this.getLocation();

        return new Point(ViewPort.DrawLocX(p.x), ViewPort.DrawLocY(p.y));
    }

    public Point GetGameHitBoxDrawPoint()
    {
        Point p = new Point(this.GetHitBox().x, this.GetHitBox().y);

        return new Point(ViewPort.DrawLocX(p.x), ViewPort.DrawLocY(p.y));
    }
}
