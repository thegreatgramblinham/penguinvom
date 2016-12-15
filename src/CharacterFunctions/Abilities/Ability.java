package CharacterFunctions.Abilities;

import CharacterFunctions.Abilities.enums.AbilityId;
import CharacterFunctions.Abilities.enums.AbilityType;

public class Ability
{
    //Variables
    private AbilityId _id;
    private String _alias;
    private AbilityType _type;
    private int _baseDamage;

    //Constructor
    public Ability(AbilityId id, String alias, AbilityType type,
                   int baseDamage)
    {
        _id = id;
        _alias = alias;
        _type = type;
        _baseDamage = baseDamage;
    }

    //Get Methods
    public AbilityId GetId()
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


    //Public Methods

    //Private Methods
}
