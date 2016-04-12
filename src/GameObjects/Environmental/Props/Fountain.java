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
        super(new Rectangle(location.x, location.y, WIDTH, HEIGHT), true, 1.0F,
                new SpriteAnimation("src/ImageAssets/props/fountain0000", WIDTH, HEIGHT, 7,
                        GameConstants.ENGINE_FPS, true));
    }

    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods

}
