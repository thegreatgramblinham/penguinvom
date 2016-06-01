package Stages;

import XMLParsing.XMLParser;
import org.w3c.dom.NodeList;

public final class StageBuilder
{

    //Variables

    //Constructor
    private StageBuilder(){}

    //Public Methods
    public static void BuildStage(String filePath) throws Exception
    {
        XMLParser parser = new XMLParser(filePath);

        int lvlWidth = XMLParser.ParseIntPathContents(
            parser.OpenNodeList(StageConstants.S_LEVEL_WIDTH), 0);
        int lvlHeight = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(StageConstants.S_LEVEL_HEIGHT), 0);

        NodeList test = parser.OpenNodeList(StageConstants.S_ENEMY);

    }

    //Private Methods

}
