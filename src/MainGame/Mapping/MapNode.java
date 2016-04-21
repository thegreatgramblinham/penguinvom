package MainGame.Mapping;

import SectorBase.enums.Direction;
import Stages.OverworldStage;
import Stages.StageObject;

import java.util.UUID;

public class MapNode
{
    //Private Variables
    private OverworldStage _stage;
    private UUID _id;

    private MapNode _up;
    private MapNode _down;
    private MapNode _left;
    private MapNode _right;



    //Constructor
    public MapNode(OverworldStage s)
    {
        _stage = s;
        _id = UUID.randomUUID();
    }

    //Get Methods
    public OverworldStage GetStage()
    {
        return _stage;
    }

    public UUID GetId()
    {
        return _id;
    }

    //Public Methods
    public void LinkDirection(MapNode node, Direction s)
    {
        switch(s)
        {
            case Up:
                _up = node;
                if(node.GetLink(Direction.Down) != this)
                    node.LinkDirection(this, Direction.Down);
                break;

            case Down:
                _down = node;
                if(node.GetLink(Direction.Up) != this)
                    node.LinkDirection(this, Direction.Up);
                break;

            case Left:
                _left = node;
                if(node.GetLink(Direction.Right) != this)
                    node.LinkDirection(this, Direction.Right);
                break;

            case Right:
                _right = node;
                if(node.GetLink(Direction.Left) != this)
                    node.LinkDirection(this, Direction.Left);
                break;
        }
    }

    public MapNode GetLink(Direction s)
    {
        switch(s)
        {
            case Up:
                return _up;
            case Down:
                return _down;
            case Left:
                return _left;
            case Right:
                return _right;
        }

        return null;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapNode mapNode = (MapNode) o;

        return _id.equals(mapNode._id);

    }

    @Override
    public int hashCode()
    {
        return _id.hashCode();
    }
}
