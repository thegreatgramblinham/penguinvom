package Menus.Text;

import javafx.scene.image.Image;

public class Letter
{
    //Variables
    private Image _sprite;

    private int _xPos;

    //Constructor
    public Letter(Image sprite)
    {
        _sprite = sprite;
    }

    //Get Methods
    public int GetWidth()
    {
        return (int)_sprite.getWidth();
    }

    public int GetHeight()
    {
        return (int)_sprite.getHeight();
    }

    public Image GetSprite()
    {
        return _sprite;
    }

    public int GetXPos()
    {
        return _xPos;
    }

    //Set Methods
    public void SetRelativeX(int x)
    {
        _xPos = x;
    }

    //Public Methods

    //Private Methods

}
