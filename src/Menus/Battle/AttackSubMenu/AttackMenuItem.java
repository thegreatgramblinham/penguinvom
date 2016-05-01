package Menus.Battle.AttackSubMenu;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class AttackMenuItem extends AttackMenuItemBase
{
    //Public Constants
    public final static int HEIGHT = 24;

    //Private Constants
    private final static int WIDTH = 400;

    //Constructor
    public AttackMenuItem(Point location) //todo text, icon
    {
        super(new Rectangle(location.x, location.y, WIDTH, HEIGHT));
        Init();
    }

    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods
    private void Init()
    {
        _sprite = new Image(
                new File("src/ImageAssets/menus/battle/attackSubMenuItem.png")
                        .toURI().toString());
    }

}
