package CharacterFunctions.Abilities;

import CharacterFunctions.Abilities.enums.AbilityId;
import CharacterFunctions.Abilities.enums.AbilityType;
import XMLParsing.XMLParser;

import java.util.Enumeration;

public final class AbilityBuilder
{
    //Constructor
    private AbilityBuilder() {}

    //Public Methods
    public static Ability BuildAbility(String path) throws Exception
    {
        XMLParser parser = new XMLParser(path);

        AbilityId id = AbilityId.valueOf(XMLParser.ParseStringPathContents(
                parser.OpenNodeList(AbilityConstants.M_ID)));
        String alias = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(AbilityConstants.M_ALIAS));
        String typeStr = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(AbilityConstants.M_TYPE));
        int baseDamage = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(AbilityConstants.M_BASEDMG));
        String animationFile = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(AbilityConstants.M_ANIMATION));

        AbilityType type = null;

        switch(typeStr)
        {
            case "Jump":
                type = AbilityType.Jump;
                break;
            case "Bat":
                type = AbilityType.Bat;
                break;
            case "Tactical":
                type = AbilityType.Tactical;
                break;
            default:
                throw new Exception("< AbilityType not found >");
        }

        return new Ability(id, alias, type, baseDamage, animationFile);
    }

    //Private Methods


}
