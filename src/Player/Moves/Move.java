package Player.Moves;

import Player.Moves.enums.MoveType;

public class Move
{
    //Variables
    private String _id;
    private String _alias;
    private MoveType _type;
    private int _baseDamage;
    private String _animation;


    //Constructor
    public Move(String id, String alias, MoveType type,
                int baseDamage, String animation)
    {
        _id = id;
        _alias = alias;
        _type = type;
        _baseDamage = baseDamage;
        _animation = animation;
    }

    //Get Methods
    public String GetId()
    {
        return _id;
    }

    public String GetAlias()
    {
        return _alias;
    }

    public MoveType GetMoveType()
    {
        return _type;
    }

    public int GetBaseDamage()
    {
        return _baseDamage;
    }

    public String GetAnimation()
    {
        return _animation;
    }

    //Public Methods

    //Private Methods

}
