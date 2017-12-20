package Menus.Battle.AttackSubMenu;

import MainGame.ViewPort;
import Menus.Base.MenuBase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;


public abstract class AttackMenuItemBase extends MenuBase
{
    //Variables
    protected Image _sprite;

    //Constructor
    public AttackMenuItemBase(Rectangle rect)
    {
        super(rect);
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void Draw(GraphicsContext gc)
    {
        //todo draw base image and then text + icon on top.
        gc.drawImage(_sprite, ViewPort.DrawLocX(this.x),
                ViewPort.DrawLocY(this.y));
    }

    //Private Methods

}
