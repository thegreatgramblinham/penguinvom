package MainGame.Mapping;

import SectorBase.enums.Direction;
import Stages.*;

import java.util.HashMap;

public class StageMap
{
    /*
        Controls the layout of stage order and spacial relation.
        **NOTE: This class has static dependencies, only one instance is allowed.**
    */

    //Private Variables
    private static HashMap<String, MapNode> _loadedStageGroup;

    //Constructor
    public StageMap() throws Exception
    {
        _loadedStageGroup = new HashMap<>();
        LoadStageGroup1();
    }

    //Public Static Methods
    public static OverworldStage Query(String id, Direction d)
    {
        if(!_loadedStageGroup.containsKey(id)) return null;

        MapNode n = _loadedStageGroup.get(id).GetLink(d);

        return n != null
                ? n.GetStage()
                : null;
    }

    public static OverworldStage Query(String id)
    {
        if(!_loadedStageGroup.containsKey(id)) return null;

        return _loadedStageGroup.get(id).GetStage();
    }

    //Private Methods
    private void LoadStageGroup1() throws Exception
    {
        MapNode garden =
                new MapNode(StageBuilder.BuildOverworldStage(StageConstants.CastleGarden));

        MapNode castle =
                new MapNode(StageBuilder.BuildOverworldStage(StageConstants.MainCastle));

        garden.LinkDirection(castle, Direction.Right);
        castle.LinkDirection(garden, Direction.Left);

        _loadedStageGroup.put(StageConstants.CastleGarden, garden);
        _loadedStageGroup.put(StageConstants.MainCastle, castle);
;    }

}
