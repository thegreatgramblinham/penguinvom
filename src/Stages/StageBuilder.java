package Stages;

import XMLParsing.XMLParser;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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

        ParseExits(parser);
        ParseEntrances(parser);

        ParseBackdrops(parser);
        ParseFloor(parser);
        ParseWall(parser);
        ParseProps(parser);
        ParseEnemies(parser);
    }

    //Private Methods
    private static void ParseExits(XMLParser parser) throws Exception
    {
        ArrayList<StageObjectRectProperties> exits
                = ParseChildRectProperties(parser, StageConstants.GetExitIndexFormatXPath());
        //todo make exit objects
    }

    private static void ParseEntrances(XMLParser parser) throws Exception
    {
        ArrayList<StageObjectRectProperties> entrances
                = ParseChildRectProperties(parser, StageConstants.GetEntranceIndexFormatXPath());
        //todo make entrance objects
    }

    private static void ParseBackdrops(XMLParser parser) throws Exception
    {
        ArrayList<StageObjectRectProperties> backdrops
                = ParseChildRectProperties(parser, StageConstants.GetBackdropIndexFormatXPath());
        //todo make backdrop objects
    }

    private static void ParseFloor(XMLParser parser) throws Exception
    {
        ArrayList<StageObjectRectProperties> floor
                = ParseChildRectProperties(parser, StageConstants.GetFloorIndexFormatXPath());
        //todo make floor object
    }

    private static void ParseWall(XMLParser parser) throws Exception
    {
        ArrayList<StageObjectRectProperties> wall
                = ParseChildRectProperties(parser, StageConstants.GetWallIndexFormatXPath());
        //todo make wall object
    }

    private static void ParseProps(XMLParser parser) throws Exception
    {
        ArrayList<StageObjectRectProperties> props
                = ParseChildRectProperties(parser, StageConstants.GetPropIndexFormatXPath());
        //todo make prop objects
    }

    private static void ParseEnemies(XMLParser parser) throws Exception
    {
        ArrayList<StageObjectRectProperties> enemies
                = ParseChildRectProperties(parser, StageConstants.GetEnemyIndexFormatXPath());
        //todo make enemy objects
    }

    private static ArrayList<StageObjectRectProperties> ParseChildRectProperties(
            XMLParser parser, String xPathToFormat) throws Exception
    {
        ArrayList<StageObjectRectProperties> rectProps = new ArrayList<>();

        int i = 0;
        String formattedPath = String.format(xPathToFormat, i);
        NodeList nodes = parser.OpenNodeList(formattedPath);
        while (nodes.getLength() > 0)
        {
            rectProps.add(ParseRectContents(parser, formattedPath));
            i++;

            formattedPath = String.format(xPathToFormat, i);
            nodes = parser.OpenNodeList(formattedPath);
        }

        return rectProps;
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
