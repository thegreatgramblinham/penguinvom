package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.SlimAi;

import java.awt.*;

public class Slim extends EnemyBase
{
    //Constructor
    public Slim(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png", 64, 64, 10, 60, true),
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png", 64, 64, 2, 60, true));
        this.SetAlias("BigSlim");
        this.SetAI(new SlimAi(this));
    }
}
