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
        Document d = XMLParser.CreateDocument(path);
        String id = XMLParser.ParseStringPathContents(d, MoveXMLConstants.M_ID);
        String alias = XMLParser.ParseStringPathContents(d, MoveXMLConstants.M_ALIAS);
        String typeStr = XMLParser.ParseStringPathContents(d, MoveXMLConstants.M_TYPE);
        int baseDamage = XMLParser.ParseIntPathContents(d, MoveXMLConstants.M_BASEDMG);
        String animationFile = XMLParser.ParseStringPathContents(d, MoveXMLConstants.M_ANIMATION);

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
