package MainGame.Mapping;

import SectorBase.Sector;
import SectorBase.enums.Direction;
import Stages.CastleGardenStage;
import Stages.MainCastleStage;
import Stages.StageObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;

public class StageMap
{
    /*
        Controls the layout of stage order and spacial relation.
        **NOTE: This class has static dependencies, only one instance is allowed.**
    */

    //Private Variables
    private static HashMap<Type, MapNode> _nodeLookup;


    //Constructor
    public StageMap(Sector s1, Sector s2) throws Exception
    {
        _nodeLookup = new HashMap<>();
        InitStageGroup1(s1, s2);
    }

    //Public Static Methods
    public static StageObject Query(Type t, Direction d)
    {
        if(!_nodeLookup.containsKey(t)) return null;

        MapNode n = _nodeLookup.get(t).GetLink(d);

        return n != null
                ? n.GetStage()
                : null;
    }

    public static StageObject Query(Type t)
    {
        if(!_nodeLookup.containsKey(t)) return null;

        return _nodeLookup.get(t).GetStage();
    }

    //Private Methods
    private void InitStageGroup1(Sector s1, Sector s2) throws Exception
    {
        HashSet<MapNode> nodeSet = new HashSet<>();

        MapNode garden = new MapNode(new CastleGardenStage(s1));
        MapNode castle = new MapNode(new MainCastleStage(s2));

        garden.LinkDirection(castle, Direction.Right);
        castle.LinkDirection(garden, Direction.Left);

        _nodeLookup.put(CastleGardenStage.class, garden);
        _nodeLookup.put(MainCastleStage.class, castle);
;    }

}
