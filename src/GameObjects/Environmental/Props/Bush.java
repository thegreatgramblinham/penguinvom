package GameObjects.Environmental.Props;

import Animation.SpriteAnimation;
import MainGame.GameConstants;

import java.awt.*;

public class Bush extends PropBase
{
    //Constants
    public final static int WIDTH = 128;
    public final static int HEIGHT = 128;

    //Constructor
    public Bush(Point location)
    {
        super(new Rectangle(location.x, location.y, WIDTH, HEIGHT), true, 1.0F,
                new SpriteAnimation("src/ImageAssets/props/bush0000.png", WIDTH, HEIGHT, 1,
                        GameConstants.ENGINE_FPS, true));
    }


    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods

}
