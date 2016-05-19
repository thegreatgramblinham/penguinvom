package Player.Moves;

public final class MoveConstants
{
    //Move xPaths
    private final static String _M_ROOT = "/Move";
    public final static String M_ID = _M_ROOT + "/Id";
    public final static String M_ALIAS = _M_ROOT + "/Alias";
    public final static String M_TYPE = _M_ROOT + "/MoveType";
    public final static String M_BASEDMG = _M_ROOT + "/BaseDamage";
    public final static String M_ANIMATION = _M_ROOT + "/AnimationFile";

    //Move File Paths
    private final static String _PATHBASE = "src/config/moves/";
    public final static String BASIC_JUMP = _PATHBASE + "basicJump.xml";
}
