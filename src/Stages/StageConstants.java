package Stages;

public final class StageConstants
{
    private final static String _STAGE_FILE_EXT = "pld";

    //Stage xPaths
    private final static String _S_ROOT = "/Stage";
    private final static String _GLOBAL_ROOT = _S_ROOT + "/Global";
    private final static String _TRIGGER_ROOT = _S_ROOT + "/Triggers";
    private final static String _OBJECT_ROOT = _S_ROOT + "/Objects";
    private final static String _INDEX_PREFIX = "/I";

    public final static String S_LEVEL_WIDTH = _GLOBAL_ROOT + "/LevelSize/Width";
    public final static String S_LEVEL_HEIGHT = _GLOBAL_ROOT + "/LevelSize/Height";

    public final static String S_ENTRANCE = _TRIGGER_ROOT + "/Entrance";
    public final static String S_EXIT = _TRIGGER_ROOT + "/Exit";

    public final static String S_BACKDROP= _OBJECT_ROOT + "/Backdrop";
    public final static String S_FLOOR = _OBJECT_ROOT + "/Floor";
    public final static String S_WALL = _OBJECT_ROOT + "/Wall";
    public final static String S_PROP = _OBJECT_ROOT + "/Prop";
    public final static String S_ENEMY = _OBJECT_ROOT + "/Enemy";

    public final static String S_NAME= "/Name";
    public final static String S_TYPE = "/Type";
    public final static String S_X = "/X";
    public final static String S_Y = "/Y";
    public final static String S_WIDTH = "/Width";
    public final static String S_HEIGHT = "/Height";
    public final static String S_RENDER_LAYER = "/RenderLayer";

    //Stage File Paths
    private final static String _PATHBASE = "src/config/stages";
    private final static String _OVERWORLD_ROOT = _PATHBASE + "/overworld";
    private final static String _BATTLE_ROOT = _PATHBASE + "/battle";
    public final static String CastleGarden = _OVERWORLD_ROOT + "/castleGarden."+_STAGE_FILE_EXT;


    //Public Methods
    public static String GetExitPathAtIndex(int i)
    {
        return S_EXIT + _INDEX_PREFIX + i;
    }

    public static String GetEntraceXPathAtIndex(int i)
    {
        return S_ENTRANCE + _INDEX_PREFIX + i;
    }

    public static String GetBackdropXPathAtIndex(int i)
    {
        return S_BACKDROP + _INDEX_PREFIX + i;
    }

    public static String GetFloorXPathAtIndex(int i)
    {
        return S_FLOOR + _INDEX_PREFIX + i;
    }

    public static String GetWallXPathAtIndex(int i)
    {
        return S_WALL + _INDEX_PREFIX + i;
    }

    public static String GetPropXPathAtIndex(int i)
    {
        return S_PROP + _INDEX_PREFIX + i;
    }

    public static String GetEnemyXPathAtIndex(int i)
    {
        return S_ENEMY + _INDEX_PREFIX + i;
    }

    public static String GetExitIndexFormatXPath()
    {
        return S_EXIT + _INDEX_PREFIX + "%s";
    }

    public static String GetEntranceIndexFormatXPath()
    {
        return S_ENTRANCE + _INDEX_PREFIX + "%s";
    }

    public static String GetBackdropIndexFormatXPath()
    {
        return S_BACKDROP + _INDEX_PREFIX + "%s";
    }

    public static String GetFloorIndexFormatXPath()
    {
        return S_FLOOR + _INDEX_PREFIX + "%s";
    }

    public static String GetWallIndexFormatXPath()
    {
        return S_WALL + _INDEX_PREFIX + "%s";
    }

    public static String GetPropIndexFormatXPath()
    {
        return S_PROP + _INDEX_PREFIX + "%s";
    }

    public static String GetEnemyIndexFormatXPath()
    {
        return S_ENEMY + _INDEX_PREFIX + "%s";
    }
}
