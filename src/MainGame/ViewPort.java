package MainGame;

import java.awt.*;

public class ViewPort
{
    //Private Constants
    private static final int SCROLL_SPEED = 2;
    private static final double LOWER_SCROLL_PARTITION = (1.0/2.5);
    private static final double HIGHER_SCROLL_PARTITION = (1.5/2.5);
    
    //Public Static Fields
    public static final int X_RES = 1067; //1024;
    public static final int Y_RES = 600; //768;

    //Private Fields
    private static Point _location;

    private int _xRes;
    private int _yRes;

    private int _minLeft;
    private int _minTop;
    private int _maxRight;
    private int _maxBottom;

    //Constructor
    public ViewPort(int width, int height, Point location,
                    int minLeft, int minTop, int maxRight, int maxBottom)
    {
        _location = location;
        _xRes = width;
        _yRes = height;
        _minLeft = minLeft;
        _minTop = minTop;
        _maxRight = maxRight;
        _maxBottom = maxBottom;
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
        int yPos = GetViewRelativeY(p.y);
        if( xPos < (_xRes * LOWER_SCROLL_PARTITION))
        {
            if(_location.x > _minLeft)
                ScrollLeft();
        }
        if( xPos > (_xRes * HIGHER_SCROLL_PARTITION))
        {
            if(_location.x < _maxRight)
                ScrollRight();
        }
        if(yPos < (_yRes * LOWER_SCROLL_PARTITION))
        {
            if(_location.y > _minTop)
                ScrollUp();
        }
        if(yPos > (_yRes * HIGHER_SCROLL_PARTITION))
        {
            if(_location.y < _maxBottom)
                ScrollDown();
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

    private void ScrollUp()
    {
        _location = new Point(_location.x,
                _location.y - SCROLL_SPEED);
    }

    private void ScrollDown()
    {
        _location = new Point(_location.x,
                _location.y + SCROLL_SPEED);
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
