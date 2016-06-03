package Stages.Objects;

import GameObjects.Base.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class SceneryObject extends GameObject
{
    //Constructor
    public SceneryObject(Rectangle bounds, Image sprite, boolean isImmobile)
    {
        super(bounds, bounds, isImmobile, 1.0F);

        this.SetSprite(sprite);
    }

    //Public Methods
    //@Override
    public void Draw(GraphicsContext gc)
    {
        Point drawPoint = this.GetGameDrawPoint();

        gc.drawImage(this.GetSprite(), drawPoint.x, drawPoint.y);
    }
}
