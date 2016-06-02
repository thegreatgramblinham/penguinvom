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
                parser.OpenNodeList(StageConstants.S_LEVEL_WIDTH));
        int lvlHeight = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(StageConstants.S_LEVEL_HEIGHT));

        int i = 0;
        NodeList nodes = parser.OpenNodeList(StageConstants.GetEnemyXPathAtIndex(i));
        while(nodes.getLength() > 0)
        {
            ParseRectContents(parser, StageConstants.GetEnemyXPathAtIndex(i));
            i++;
            nodes = parser.OpenNodeList(StageConstants.GetEnemyXPathAtIndex(i));
        }


    }

    //Private Methods
    private static void ParseRectContents(XMLParser parser, String xPathRoot) throws Exception
    {
        String name = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(xPathRoot + StageConstants.S_NAME));

    }

}
