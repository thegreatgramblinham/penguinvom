package Menus.Battle.AttackSubMenu;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class AttackMenuItemSelection extends AttackMenuItemBase
{
    //Public Constants
    public final static int HEIGHT = 32;
    public final static int BORDER_PADDING = 4;

    //Private Constants
    private final static int WIDTH = 408;

    //Variables

    //Constructor
    public AttackMenuItemSelection(Point location)
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
                new File("src/imageAssets/menus/battle/attackSubMenuSelection.png")
                        .toURI().toString());
    }

}
