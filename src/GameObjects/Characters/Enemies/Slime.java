package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.CharacterBase;

import java.awt.*;

public class Slime extends EnemyBase
{
    //Constructor
    public Slime(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png", 64, 64, 10, 60, true),
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png", 64, 64, 2, 60, true));
    }
}
