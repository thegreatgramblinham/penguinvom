package GameObjects.Projectiles;

import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class Bullet extends ProjectileBase
{
    //Constructor
    public Bullet(Point p, GameObject owner)
    {
        super(new Rectangle(p.x, p.y, 16, 16), 0.01F, owner, 5);
        this.SetSprite(new Image(
                new File("src/ImageAssets/projectiles/bullet10000.png")
                        .toURI().toString()));
    }

    @Override
    public void OnCollide(GameWorldObject other)
    {
        this.SetNeedsDeletion(true);
        super.OnCollide(other);
    }
}
