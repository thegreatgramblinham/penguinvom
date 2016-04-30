package Menus.Battle;

import Animation.SpriteAnimation;
import Menus.Base.MenuBase;
import Menus.Battle.enums.BattleMenuType;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class BattleMenuBase extends MenuBase
{
    //Private variables
    protected SpriteAnimation _animation;
    protected BattleMenuType _type;
    //todo visibility

    //Constructor
    public BattleMenuBase(Rectangle rect, SpriteAnimation menuAnimation,
                          BattleMenuType type)
    {
        super(rect);
        _type = type;
        _animation = menuAnimation;
    }

    //Get Methods
    public BattleMenuType GetType()
    {
        return _type;
    }

    //Public Methods
    public boolean TransitionComplete()
    {
        return _animation.IsAnimationFinished();
    }

    //Abstract Methods
    public abstract void DrawTransitionAnimation(GraphicsContext gc);

}
