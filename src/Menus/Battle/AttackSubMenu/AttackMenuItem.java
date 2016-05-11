package Menus.Battle.AttackSubMenu;

import MainGame.ViewPort;
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

    //Private Variables
    private Image[] _text;

    //Constructor
    public AttackMenuItem(Point location, Image[] text) //todo text, icon
    {
        super(new Rectangle(location.x, location.y, WIDTH, HEIGHT));
        Init();
        _text = text;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void Draw(GraphicsContext gc)
    {
        super.Draw(gc);

        for (int i = 0; i < _text.length; i++)
        {
            Image letter = _text[i];
            gc.drawImage(letter,
                    ViewPort.DrawLocX(this.x) + i* TextImager.LETTER_WIDTH,
                    ViewPort.DrawLocY(this.y));
        }
    }

    //Private Methods
    private void Init()
    {
        _sprite = new Image(
                new File("src/ImageAssets/menus/battle/attackSubMenuItem.png")
                        .toURI().toString());
    }

}
