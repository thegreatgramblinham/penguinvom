package Menus.Battle;

import Animation.SpriteAnimation;
import Animation.enums.AnimationOrientation;
import MainGame.GameConstants;
import Menus.Battle.enums.BattleMenuType;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class BatMenu extends BattleMenuBase
{
    //Private Constants
    private final static int WIDTH = 179;
    private final static int HEIGHT = 177;

    //Constructor
    public BatMenu(Point location)
    {
        super(
                new Rectangle(location.x, location.y, WIDTH, HEIGHT),
                new SpriteAnimation("src/ImageAssets/menus/battle/cardMenuAnimation.png",
                        WIDTH,
                        HEIGHT,
                        60,
                        GameConstants.ENGINE_FPS,
                        false),
                BattleMenuType.Bat
        );
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void DrawTransitionAnimation(GraphicsContext gc)
    {
        _animation.DrawSpriteFrame(gc, this.GetGameDrawPoint(), AnimationOrientation.Default);
    }

    @Override
    public void Draw(GraphicsContext gc)
    {
        _animation.DrawFrameAtIndex(gc, this.GetGameDrawPoint(), 4, AnimationOrientation.Default);
    }

    //Private Methods

}
