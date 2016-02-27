package GameObjects.Environmental;

import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;

import java.awt.*;

public class Backdrop extends GameObject
{

    public Backdrop(Rectangle size, boolean canCollide)
    {
        super(size, true, 1.0F);

        this.SetCanCollide(canCollide);
    }
}
