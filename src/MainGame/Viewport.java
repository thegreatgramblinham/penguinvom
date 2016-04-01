package MainGame;

import java.awt.*;

class Viewport
{
    //Public Static Fields
    public static final int X_RES = 800; //1056;
    public static final int Y_RES = 600; //856;

    //Private Fields
    private Point _location;

    private int _xRes;
    private int _yRes;

    //Constructor
    public Viewport(int width, int height, Point location)
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
}
