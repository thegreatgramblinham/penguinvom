package MainGame;

import GameObjects.Characters.Player.PlayerObject;

import java.awt.*;

public class ViewPort
{
    //Public Static Fields
    public static final int X_RES = 800; //1056;
    public static final int Y_RES = 600; //856;

    //Private Fields
    private static Point _location;

    private int _xRes;
    private int _yRes;

    //Constructor
    public ViewPort(int width, int height, Point location)
    {
        _location = location;
        _xRes = width;
        _yRes = height;
    }

    //Get Methods
    public int GetWidth()
    {
        return _xRes;
    }

    public int GetHeight()
    {
        return _yRes;
    }


    //Public Methods
    public void ScrollIntoView(Point p)
    {
        int xPos = GetViewRelativeX(p.x);
        if( xPos < (_xRes * (1.0/5.0)))
        {
            //scroll left
            ScrollLeft(1);
        }
        if( xPos > (_xRes * (4.0/5.0)))
        {
            //scroll right
            ScrollRight(1);
        }
    }

    //Private Methods
    private void ScrollLeft(int units)
    {
        _location = new Point(_location.x-1, _location.y);
    }

    private void ScrollRight(int units)
    {
        _location = new Point(_location.x+1, _location.y);
    }

    //Private Static Methods
    private static int GetViewRelativeX(int x)
    {
        return x -_location.x;
    }

    private static int GetViewRelativeY(int y)
    {
        return y -_location.y;
    }

    //Public Static Methods
    public static int SecLocX(int offset)
    {
        return _location.x + offset;
    }

    public static int SecLocY(int offset)
    {
        return _location.y + offset;
    }

    public static int DrawLocX(int offset)
    {
        return  GetDrawPoint().x + offset;
    }

    public static int DrawLocY(int offset)
    {
        return  GetDrawPoint().y + offset;
    }

    //Private Static Methods
    private static Point GetDrawPoint()
    {
        return new Point(-_location.x, -_location.y);
    }
}
