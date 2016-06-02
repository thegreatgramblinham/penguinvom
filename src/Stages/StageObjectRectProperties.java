package Stages;

public class StageObjectRectProperties
{
    //Variables
    private String _name;
    private int _xLoc;
    private int _yLoc;
    private int _width;
    private int _height;
    private int _renderLayer;

    //Constructor
    public StageObjectRectProperties(String name, int x, int y, int width,
                                     int height, int renderLayer)
    {
        _name = name;
        _xLoc = x;
        _yLoc = y;
        _width = width;
        _height = height;
        _renderLayer = renderLayer;
    }

    //Get Methods
    public int getRenderLayer()
    {
        return _renderLayer;
    }

    public String getName()
    {
        return _name;
    }

    public int getXLoc()
    {
        return _xLoc;
    }

    public int getYLoc()
    {
        return _yLoc;
    }

    public int getWidth()
    {
        return _width;
    }

    public int getHeight()
    {
        return _height;
    }

}
