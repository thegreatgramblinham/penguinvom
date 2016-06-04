package Stages.Objects.Scenery;

import javafx.scene.image.Image;

import java.awt.*;

public class Wall extends SceneryObject
{
    //Constructor
    public Wall(Image sprite, Point location, int renderLayer)
    {
        super(
                new Rectangle(location.x, location.y, (int)sprite.getWidth(), (int)sprite.getHeight()),
                sprite,
                true,
                renderLayer
        );

        this.SetCanCollide(true);
    }
}
