package GameObjects.Environmental.Props;

import Animation.SpriteAnimation;
import MainGame.GameConstants;

import java.awt.*;

public class Fountain extends PropBase
{
    //Private Constants
    private final static int WIDTH = 380;
    private final static int HEIGHT = 140;

    //Variables

    //Constructor
    public Fountain(Point location)
    {
        super(
                new Rectangle(location.x, location.y, WIDTH, HEIGHT),
                new Rectangle(location.x+30, location.y+50, WIDTH-60, HEIGHT-50),
                true,
                1.0F,
                new SpriteAnimation("src/ImageAssets/props/fountain0000.png", WIDTH, HEIGHT, 5,
                        GameConstants.ENGINE_FPS, true)
        );

        this.SetAlias("Fountain");
    }

    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods

}
