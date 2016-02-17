package GameObjects.Environmental;

import GameObjectBase.GameWorldObject;

import java.awt.*;

public class Backdrop extends GameWorldObject
{

    public Backdrop(Rectangle size, boolean canCollide)
    {
        super(size, true, 1.0F);

        this.SetCanCollide(canCollide);
    }
}
