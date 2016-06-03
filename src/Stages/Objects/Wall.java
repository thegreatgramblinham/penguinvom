package Stages.Objects;

import javafx.scene.image.Image;

import java.awt.*;

public class Wall extends SceneryObject
{
    //Constructor
    public Wall(Image sprite, Point location)
    {
        super(
                new Rectangle(location.x, location.y, (int)sprite.getWidth(), (int)sprite.getHeight()),
                sprite,
                false
        );

        this.SetCanCollide(true);
    }
}
