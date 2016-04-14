package GameObjects.Projectiles;

import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class Bullet extends ProjectileBase
{
    //Public Constants
    public static final int HEIGHT = 16;
    public static final int WIDTH = 16;

    //Constructor
    public Bullet(Point p, GameObject owner)
    {
        super(
                new Rectangle(p.x, p.y, HEIGHT, WIDTH),
                new Rectangle(p.x, p.y, HEIGHT, WIDTH),
                0.01F,
                owner,
                5
        );

        this.SetSprite(new Image(
                new File("src/ImageAssets/projectiles/bullet10000.png")
                        .toURI().toString()));
        
        this.SetAlias("PlayerBullet");
    }

    @Override
    public void OnCollide(GameWorldObject other)
    {
        this.SetNeedsDeletion(true);
        super.OnCollide(other);
    }
}
