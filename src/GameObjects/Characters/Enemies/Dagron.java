package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;

import java.awt.*;

public class Dagron extends EnemyBase
{
    public Dagron(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png", 64, 64, 7, 60, true),
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png", 64, 64, 0, 60, true));
    }
}
