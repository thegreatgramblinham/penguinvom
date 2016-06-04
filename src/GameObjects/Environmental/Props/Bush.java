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
        super(
                new Rectangle(location.x, location.y, WIDTH, HEIGHT),
                new Rectangle(location.x + 25, location.y + 30, WIDTH - 50, HEIGHT - 45),
                true,
                1.0F,
                new SpriteAnimation("src/imageAssets/props/bush1.png", WIDTH, HEIGHT, 1,
                        GameConstants.ENGINE_FPS, true)
        );

        this.SetAlias("Bush");
    }


    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods

}
