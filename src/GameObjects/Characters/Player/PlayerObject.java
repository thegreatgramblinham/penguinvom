package GameObjects.Characters.Player;

import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;

import java.awt.*;

public class PlayerObject extends CharacterBase
{

    public PlayerObject(Rectangle size, float mass)
    {
        super(size, false, mass);
    }
}
