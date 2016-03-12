package GameObjects.Environmental;

import GameObjects.Base.GameObject;

import java.awt.*;

public class Backdrop extends GameObject
{

    public Backdrop(Rectangle size, boolean isImmobile, boolean canCollide, String alias)
    {
        super(size, isImmobile, 1.0F);

        this.SetCanCollide(canCollide);
        this.SetAlias(alias);
    }
}
