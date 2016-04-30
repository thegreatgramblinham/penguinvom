package Menus.Base;

import MainGame.ViewPort;
import Menus.MenuItem;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class MenuBase extends MenuItem
{
    //Variables

    //Constructor
    public MenuBase(Rectangle rect)
    {
        super(rect);
    }

    //Public Methods
    public Point GetGameDrawPoint()
    {
        Point p = this.getLocation();

        return new Point(ViewPort.DrawLocX(p.x), ViewPort.DrawLocY(p.y));
    }

    //Abstract Methods
    public abstract void Draw(GraphicsContext gc);
}
