package GameObjects.Characters.Player;

import Animation.SpriteAnimation;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;

import java.awt.*;

public class PlayerObject extends CharacterBase
{

    public PlayerObject(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation(
                        "src/ImageAssets/player/shuf64png.png", 64, 64, 10, 60, true));
    }
}
