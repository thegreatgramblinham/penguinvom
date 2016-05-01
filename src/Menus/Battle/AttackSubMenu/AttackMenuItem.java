package Menus.Battle.AttackSubMenu;

import MainGame.ViewPort;
import Menus.Base.MenuBase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class AttackMenuItem extends MenuBase
{
    //Public Constants
    public final static int HEIGHT = 24;

    //Private Constants
    private final static int WIDTH = 400;

    //Variables
    private Image _sprite;

    //Constructor
    public AttackMenuItem(Point location) //todo text, icon
    {
        super(new Rectangle(location.x, location.y, WIDTH, HEIGHT));
        Init();
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
    private void Init()
    {
        _sprite = new Image(
                new File("src/ImageAssets/menus/battle/attackSubMenuItem.png")
                        .toURI().toString());
    }

}
