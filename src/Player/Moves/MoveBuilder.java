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
        String id = XMLParser.ParseStringPathContents(d, "/Move/Id");
        String alias = XMLParser.ParseStringPathContents(d, "/Move/Alias");
        String typeStr = XMLParser.ParseStringPathContents(d, "/Move/MoveType");
        int baseDamage = XMLParser.ParseIntPathContents(d, "/Move/BaseDamage");
        String animationFile = XMLParser.ParseStringPathContents(d, "/Move/AnimationFile");

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
