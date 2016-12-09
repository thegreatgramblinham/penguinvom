package CharacterFunctions.Abilities;

import CharacterFunctions.Abilities.enums.AbilityType;

public class Ability
{
    //Variables
    private String _id;
    private String _alias;
    private AbilityType _type;
    private int _baseDamage;
    private String _animation;

    //Constructor
    public Ability(String id, String alias, AbilityType type,
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

    public AbilityType GetAbilityType()
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
