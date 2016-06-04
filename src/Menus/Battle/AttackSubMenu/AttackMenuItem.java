package Menus.Battle.AttackSubMenu;

import MainGame.ViewPort;
import Menus.Text.TextBlock;
import Menus.Text.TextImager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class AttackMenuItem extends AttackMenuItemBase
{
    //Public Constants
    public final static int HEIGHT = 24;

    //Private Constants
    private final static int WIDTH = 400;
    private final static int TEXT_MARGIN_TOP = 5;
    private final static int TEXT_MARGIN_LEFT = 6;

    //Private Variables
    private TextBlock _text;

    //Constructor
    public AttackMenuItem(Point location, TextBlock text) //todo text, icon
    {
        super(new Rectangle(location.x, location.y, WIDTH, HEIGHT));
        Init();
        _text = text;
        _text.NSetLocation(new Point(this.x + TEXT_MARGIN_LEFT, this.y + TEXT_MARGIN_TOP));
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void Draw(GraphicsContext gc)
    {
        super.Draw(gc);

        _text.Draw(gc);
    }

    //Private Methods
    private void Init()
    {
        _sprite = new Image(
                new File("src/ImageAssets/menus/battle/attackSubMenuItem.png")
                        .toURI().toString());
    }

}
