package Stages;

import GameObjects.ImageConstants;
import GameObjects.enums.ObjectCategory;
import Stages.Objects.Scenery.Backdrop;
import Stages.Objects.Scenery.Floor;
import Stages.Objects.Scenery.Wall;
import XMLParsing.XMLParser;
import javafx.scene.image.Image;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.io.File;
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

        ArrayList<Backdrop> backdrops = ParseBackdrops(parser);
        ArrayList<Floor> floors = ParseFloor(parser);
        ArrayList<Wall> walls = ParseWall(parser);
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

    private static ArrayList<Backdrop> ParseBackdrops(XMLParser parser) throws Exception
    {
        ArrayList<Backdrop> backdrops = new ArrayList<>();

        ArrayList<StageObjectRectProperties> backdropProperties
                = ParseChildRectProperties(parser, StageConstants.GetBackdropIndexFormatXPath());

        for(StageObjectRectProperties props : backdropProperties)
        {
            String filePath = ImageConstants.GetImageFilePathFromId(props.getName(), ObjectCategory.Backdrop);

            Image img = new Image(new File(filePath).toURI().toString());

            Backdrop b = new Backdrop(img, new Point(props.getXLoc(), props.getYLoc()));

            backdrops.add(b);
        }

        return backdrops;
    }

    private static ArrayList<Floor> ParseFloor(XMLParser parser) throws Exception
    {
        ArrayList<Floor> floors = new ArrayList<>();

        ArrayList<StageObjectRectProperties> floorProperties
                = ParseChildRectProperties(parser, StageConstants.GetFloorIndexFormatXPath());

        for(StageObjectRectProperties props : floorProperties)
        {
            String filePath = ImageConstants.GetImageFilePathFromId(props.getName(), ObjectCategory.Floor);

            Image img = new Image(new File(filePath).toURI().toString());

            Floor f = new Floor(img, new Point(props.getXLoc(), props.getYLoc()));

            floors.add(f);
        }

        return floors;
    }

    private static ArrayList<Wall> ParseWall(XMLParser parser) throws Exception
    {
        ArrayList<Wall> walls = new ArrayList<>();

        ArrayList<StageObjectRectProperties> wallProperties
                = ParseChildRectProperties(parser, StageConstants.GetWallIndexFormatXPath());

        for(StageObjectRectProperties props : wallProperties)
        {
            String filePath = ImageConstants.GetImageFilePathFromId(props.getName(), ObjectCategory.Wall);

            Image img = new Image(new File(filePath).toURI().toString());

            Wall w = new Wall(img, new Point(props.getXLoc(), props.getYLoc()));

            walls.add(w);
        }

        return walls;
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
