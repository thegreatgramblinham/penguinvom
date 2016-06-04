package Stages.Objects.Scenery;

import java.util.HashMap;

public final class SceneryConstants
{
    private SceneryConstants() {}

    //Static Constants
    private static String _ROOT_FILE = "src/imageAssets/backgrounds/";

    //Static Variables
    private static HashMap<String, String> _sceneryImageIdToFilePath;
    private static boolean _mapInit = false;

    //Private Methods
    private static void MapInit()
    {
        _sceneryImageIdToFilePath = new HashMap<>();

        //Add scenery pieces here.
        _sceneryImageIdToFilePath.put("BrightSky1", _ROOT_FILE+"brightsky0000");
        _sceneryImageIdToFilePath.put("GardenGrassSector1", _ROOT_FILE+"gardenGrassSector0000");
        _sceneryImageIdToFilePath.put("CastleRearWall1", _ROOT_FILE+"largeWallSector0000");
        _sceneryImageIdToFilePath.put("CastleFloor1", _ROOT_FILE+"largeWoodSector0000");

        _mapInit = true;
    }
}
