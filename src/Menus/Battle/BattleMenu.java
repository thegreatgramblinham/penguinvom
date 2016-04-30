package Menus.Battle;

import Animation.SpriteAnimation;
import Menus.MenuItem;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class BattleMenu extends MenuItem
{
    //Private variables
    private SpriteAnimation _animation;
    //todo visibility

    //Constructor
    public BattleMenu(Rectangle rect, SpriteAnimation menuAnimation)
    {
        super(rect);
    }

    //Public Methods
    public abstract void DrawTransitionAnimation(GraphicsContext gc);

    public abstract void DrawSelectedAnimation(GraphicsContext gc);
}
