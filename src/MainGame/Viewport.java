package MainGame;

import GameObjects.Characters.Player.PlayerObject;

import java.awt.*;

public class ViewPort
{
    //Private Constants
    private static final int SCROLL_SPEED = 2;
    private static final double LEFTHAND_SCROLL_PARTITION = (1.0/5.0);
    private static final double RIGHTHAND_SCROLL_PARTITION = (4.0/5.0);
    
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
        if( xPos < (_xRes * LEFTHAND_SCROLL_PARTITION))
        {
            //scroll left
            ScrollLeft();
        }
        if( xPos > (_xRes * RIGHTHAND_SCROLL_PARTITION))
        {
            //scroll right
            ScrollRight();
        }
    }

    //Private Methods
    private void ScrollLeft()
    {
        _location = new Point(_location.x - SCROLL_SPEED,
                _location.y);
    }

    private void ScrollRight()
    {
        _location = new Point(_location.x + SCROLL_SPEED,
                _location.y);
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
