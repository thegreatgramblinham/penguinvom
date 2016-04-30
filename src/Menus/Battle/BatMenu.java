package Menus.Battle;

import Animation.SpriteAnimation;
import MainGame.GameConstants;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class BatMenu extends BattleMenu
{
    //Private Constants
    private final static int WIDTH = 179;
    private final static int HEIGHT = 176;

    //Constructor
    public BatMenu(Rectangle rect)
    {
        super(
                rect,
                new SpriteAnimation("src/ImageAssets/menu/battle/cardMenuAnimation.png",
                        WIDTH,
                        HEIGHT,
                        60,
                        GameConstants.ENGINE_FPS,
                        false)
        );
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void DrawTransitionAnimation(GraphicsContext gc)
    {

    }

    @Override
    public void DrawSelectedAnimation(GraphicsContext gc)
    {

    }

    //Private Methods

}
