package Player.Moves;

import Player.Moves.enums.MoveType;
import XMLParsing.XMLParser;
import org.w3c.dom.Document;

public final class MoveBuilder
{
    //Constructor
    private MoveBuilder() {}

    //Public Methods
    public static Move BuildMove(String path) throws Exception
    {
        XMLParser parser = new XMLParser(path);

        String id = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(MoveConstants.M_ID), 0);
        String alias = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(MoveConstants.M_ALIAS), 0);
        String typeStr = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(MoveConstants.M_TYPE), 0);
        int baseDamage = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(MoveConstants.M_BASEDMG), 0);
        String animationFile = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(MoveConstants.M_ANIMATION), 0);

        MoveType type = null;

        switch(typeStr)
        {
            case "Jump":
                type = MoveType.Jump;
                break;
            case "Bat":
                type = MoveType.Bat;
                break;
            case "Tactical":
                type = MoveType.Tactical;
                break;
            default:
                throw new Exception("< MoveType not found >");
        }

        return new Move(id, alias, type, baseDamage, animationFile);
    }

    //Private Methods


}
