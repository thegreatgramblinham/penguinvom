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
    public int GetRenderLayer()
    {
        return _renderLayer;
    }

    public String GetName()
    {
        return _name;
    }

    public int GetXLoc()
    {
        return _xLoc;
    }

    public int GetYLoc()
    {
        return _yLoc;
    }

    public int GetWidth()
    {
        return _width;
    }

    public int GetHeight()
    {
        return _height;
    }

}
