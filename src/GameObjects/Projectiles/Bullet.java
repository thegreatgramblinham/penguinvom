package GameObjects.Projectiles;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class Bullet extends ProjectileBase
{
    //Constructor
    public Bullet(Point p)
    {
        super(new Rectangle(p.x, p.y, 16, 16), 0.01F, 5);
        this.SetSprite(new Image(
                new File("src/ImageAssets/projectiles/bullet10000.png")
                        .toURI().toString()));
    }
}
