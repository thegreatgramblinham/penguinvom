package Stages.Objects.Scenery;

import javafx.scene.image.Image;

import java.awt.*;

public class Backdrop extends SceneryObject
{
    //Constructor
    public Backdrop(Image sprite, Point location)
    {
        super(
                new Rectangle(location.x, location.y, (int)sprite.getWidth(), (int)sprite.getHeight()),
                sprite,
                false
        );

        this.SetCanCollide(false);
    }
}
