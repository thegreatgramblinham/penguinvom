package Stages.Objects.Scenery;

import javafx.scene.image.Image;

import java.awt.*;

public class Backdrop extends SceneryObject
{
    //Constructor
    public Backdrop(Image sprite, Point location, int renderLayer)
    {
        super(
                new Rectangle(location.x, location.y, (int)sprite.getWidth(), (int)sprite.getHeight()),
                sprite,
                false,
                renderLayer
        );

        this.SetCanCollide(false);
    }
}
