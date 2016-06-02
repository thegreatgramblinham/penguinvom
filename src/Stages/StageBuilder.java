package Stages;

import XMLParsing.XMLParser;
import org.w3c.dom.NodeList;

public final class StageBuilder
{

    //Variables

    //Constructor
    private StageBuilder() {}

    //Public Methods
    public static void BuildStage(String filePath) throws Exception
    {
        XMLParser parser = new XMLParser(filePath);

        int lvlWidth = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(StageConstants.S_LEVEL_WIDTH));
        int lvlHeight = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(StageConstants.S_LEVEL_HEIGHT));

        ParseEnemies(parser);


    }

    //Private Methods
    private static void ParseEnemies(XMLParser parser) throws Exception
    {
        int i = 0;
        NodeList nodes = parser.OpenNodeList(StageConstants.GetEnemyXPathAtIndex(i));
        while (nodes.getLength() > 0)
        {
            ParseRectContents(parser, StageConstants.GetEnemyXPathAtIndex(i));
            i++;
            nodes = parser.OpenNodeList(StageConstants.GetEnemyXPathAtIndex(i));
        }
    }

    private static StageObjectRectProperties ParseRectContents(XMLParser parser,
            String xPathRoot) throws Exception
    {
        String name = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(xPathRoot + StageConstants.S_NAME));

        int xLoc = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(xPathRoot + StageConstants.S_X));

        int yLoc = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(xPathRoot + StageConstants.S_Y));

        int width = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(xPathRoot + StageConstants.S_WIDTH));

        int height = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(xPathRoot + StageConstants.S_HEIGHT));

        int renderLayer = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(xPathRoot + StageConstants.S_RENDER_LAYER));

        return new StageObjectRectProperties(name, xLoc, yLoc, width, height, renderLayer);
    }

}
