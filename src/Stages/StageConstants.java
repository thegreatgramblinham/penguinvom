package Stages;

public final class StageConstants
{
    private final static String _STAGE_FILE_EXT = "pld";

    //Stage xPaths
    private final static String _S_ROOT = "/Stage";
    private final static String _GLOBAL_ROOT = _S_ROOT + "/Global";
    private final static String _TRIGGER_ROOT = _S_ROOT + "/Triggers";
    private final static String _OBJECT_ROOT = _S_ROOT + "/Objects";

    public final static String S_LEVEL_WIDTH = _GLOBAL_ROOT + "/LevelSize/Width";
    public final static String S_LEVEL_HEIGHT = _GLOBAL_ROOT + "/LevelSize/Height";

    public final static String S_ENTRANCE = _TRIGGER_ROOT + "/Entrance";
    public final static String S_EXIT = _TRIGGER_ROOT + "/Exit";

    public final static String S_BACKDROP= _OBJECT_ROOT + "/Backdrop";
    public final static String S_FLOOR = _OBJECT_ROOT + "/Floor";
    public final static String S_WALL = _OBJECT_ROOT + "/Wall";
    public final static String S_PROP = _OBJECT_ROOT + "/Prop";
    public final static String S_ENEMY = _OBJECT_ROOT + "/Enemy";


    //Stage File Paths
    private final static String _PATHBASE = "src/config/stages/";
    public final static String CastleGarden = _PATHBASE + "castleGarden."+_STAGE_FILE_EXT;
}
