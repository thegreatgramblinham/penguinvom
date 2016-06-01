package MainGame.Mapping;

import MainGame.GameManager;
import SectorBase.enums.Direction;
import Stages.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class StageMap
{
    /*
        Controls the layout of stage order and spacial relation.
        **NOTE: This class has static dependencies, only one instance is allowed.**
    */

    //Private Variables
    private static HashMap<Type, MapNode> _loadedStageGroup;

    //Constructor
    public StageMap() throws Exception
    {
        _loadedStageGroup = new HashMap<>();
        LoadStageGroup1();
    }

    //Public Static Methods
    public static OverworldStage Query(Type t, Direction d)
    {
        if(!_loadedStageGroup.containsKey(t)) return null;

        MapNode n = _loadedStageGroup.get(t).GetLink(d);

        return n != null
                ? n.GetStage()
                : null;
    }

    public static OverworldStage Query(Type t)
    {
        if(!_loadedStageGroup.containsKey(t)) return null;

        return _loadedStageGroup.get(t).GetStage();
    }

    //Private Methods
    private void LoadStageGroup1() throws Exception
    {
        StageBuilder.BuildStage(StageConstants.CastleGarden);

        MapNode garden = new MapNode(
                new CastleGardenStage(GameManager.CreateNewEngineSector()));
        MapNode castle = new MapNode(
                new MainCastleStage(GameManager.CreateNewEngineSector()));

        garden.LinkDirection(castle, Direction.Right);
        castle.LinkDirection(garden, Direction.Left);

        _loadedStageGroup.put(CastleGardenStage.class, garden);
        _loadedStageGroup.put(MainCastleStage.class, castle);
;    }

}
