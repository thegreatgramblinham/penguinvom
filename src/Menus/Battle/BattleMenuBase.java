package Menus.Battle;

import Animation.SpriteAnimation;
import Menus.Base.MenuBase;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class BattleMenuBase extends MenuBase
{
    //Private variables
    protected SpriteAnimation _animation;
    //todo visibility

    //Constructor
    public BattleMenuBase(Rectangle rect, SpriteAnimation menuAnimation)
    {
        super(rect);
        _animation = menuAnimation;
    }

    //Public Methods
    public boolean TransitionComplete()
    {
        return _animation.IsAnimationFinished();
    }

    //Abstract Methods
    public abstract void DrawTransitionAnimation(GraphicsContext gc);


}
