package Menus.Battle;

import Animation.SpriteAnimation;
import Menus.Base.MenuBase;
import Menus.MenuItem;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class BattleMenu extends MenuBase
{
    //Private variables
    protected SpriteAnimation _animation;
    //todo visibility

    //Constructor
    public BattleMenu(Rectangle rect, SpriteAnimation menuAnimation)
    {
        super(rect);
        _animation = menuAnimation;
    }

    //Public Methods
    public abstract void DrawTransitionAnimation(GraphicsContext gc);


}
