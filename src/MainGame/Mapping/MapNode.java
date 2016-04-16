package MainGame.Mapping;

import SectorBase.enums.Direction;
import Stages.StageObject;

public class MapNode
{
    //Variables
    private StageObject _stage;

    private MapNode _up;
    private MapNode _down;
    private MapNode _left;
    private MapNode _right;

    //Constructor
    public MapNode(StageObject s)
    {
        _stage = s;
    }

    //Get Methods
    public StageObject GetStage()
    {
        return _stage;
    }

    public MapNode GetUp()
    {
        return _up;
    }

    public MapNode GetDown()
    {
        return _down;
    }

    public MapNode GetLeft()
    {
        return _left;
    }

    public MapNode GetRight()
    {
        return _right;
    }

    //Public Methods
    public void LinkDirection(MapNode node, Direction s)
    {
        switch(s)
        {
            case Up:
                _up = node;
                if(node.GetDown() != this)
                    node.LinkDirection(this, Direction.Down);
                break;

            case Down:
                _down = node;
                if(node.GetUp() != this)
                    node.LinkDirection(this, Direction.Up);
                break;

            case Left:
                _left = node;
                if(node.GetRight() != this)
                    node.LinkDirection(this, Direction.Right);
                break;

            case Right:
                _right = node;
                if(node.GetLeft() != this)
                    node.LinkDirection(this, Direction.Left);
                break;
        }
    }

}
