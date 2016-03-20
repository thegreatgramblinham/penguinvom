package GameObjects.Projectiles;

import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;

import java.awt.*;
import java.io.File;

public class EnemyBullet extends ProjectileBase
{
    //Public Constants
    public static final int HEIGHT = 16;
    public static final int WIDTH = 16;

    //Constructor
    public EnemyBullet(Point p, GameObject owner)
    {
        super(new Rectangle(p.x, p.y, HEIGHT, WIDTH), 0.0F, owner, 5);
        this.SetSprite(new javafx.scene.image.Image(
                new File("src/ImageAssets/projectiles/enemyBullet0000.png")
                        .toURI().toString()));
        this.SetAlias("EnemyBullet");
    }

    @Override
    public void OnCollide(GameWorldObject other)
    {
        this.SetNeedsDeletion(true);
        super.OnCollide(other);
    }
}
