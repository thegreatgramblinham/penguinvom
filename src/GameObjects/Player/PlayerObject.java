package GameObjects.Player;

import GameObjectBase.GameWorldObject;

import java.awt.*;

public class PlayerObject extends GameWorldObject
{

    public PlayerObject(Rectangle size, float mass)
    {
        super(size, false, mass);
    }
}
