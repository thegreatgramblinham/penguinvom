package Stages.Objects.Scenery;

import GameObjects.Base.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class SceneryObject extends GameObject
{
    //Properties
    private int _renderLayer;

    //Constructor
    public SceneryObject(Rectangle bounds, Image sprite, boolean isImmobile,
            int renderLayer)
    {
        super(bounds, bounds, isImmobile, 1.0F);

        this.SetSprite(sprite);
        _renderLayer = renderLayer;
    }

    //Get Methods
    public int GetRenderLayer()
    {
        return _renderLayer;
    }

    //Public Methods
    //@Override
    public void Draw(GraphicsContext gc)
    {
        Point drawPoint = this.GetGameDrawPoint();

        gc.drawImage(this.GetSprite(), drawPoint.x, drawPoint.y);
    }
}
