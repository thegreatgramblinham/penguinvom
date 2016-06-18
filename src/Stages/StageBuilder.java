package Stages;

import GameObjectBase.enums.Side;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Environmental.Props.PropBase;
import GameObjects.GameObjectConstants;
import GameObjects.Triggers.RoomChangeTrigger;
import GameObjects.enums.ObjectCategory;
import GeneralHelpers.ConversionHelper;
import MainGame.GameConstants;
import Stages.Objects.Scenery.Backdrop;
import Stages.Objects.Scenery.Floor;
import Stages.Objects.Scenery.Wall;
import XMLParsing.XMLParser;
import javafx.scene.image.Image;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public final class StageBuilder
{

    //Variables

    //Constructor
    private StageBuilder() {}

    //Public Methods
    public static XmlBuiltStage BuildStage(String roomId) throws Exception
    {
        XMLParser parser = new XMLParser(roomId);

        int lvlWidth = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(StageConstants.S_LEVEL_WIDTH));
        int lvlHeight = XMLParser.ParseIntPathContents(
                parser.OpenNodeList(StageConstants.S_LEVEL_HEIGHT));

        Point viewPortStart = ParseViewPortStart(parser);

        HashMap<Side, Rectangle> exits = ParseExits(parser);
        HashMap<Side, Rectangle> entrances = ParseEntrances(parser);

        ArrayList<Backdrop> backdrops = ParseBackdrops(parser);
        ArrayList<Floor> floors = ParseFloor(parser);
        ArrayList<Wall> walls = ParseWall(parser);
        ArrayList<PropBase> props = ParseProps(parser);
        ArrayList<EnemyBase> enemies = ParseEnemies(parser);

        return new XmlBuiltStage(roomId, lvlWidth, lvlHeight, viewPortStart, exits, entrances, backdrops,
                floors, walls, props, enemies);
    }

    //Private Methods
    private static Point ParseViewPortStart(XMLParser parser) throws Exception
    {
        NodeList viewTag = parser.OpenNodeList(StageConstants.S_VIEWPORT);
        if(viewTag.getLength() == 0)
            return GameConstants.GAME_STARTING_POINT;

        StageObjectRectProperties props = ParseRectContents(parser, StageConstants.S_VIEWPORT);

        return new Point(props.GetXLoc(),props.GetYLoc());
    }

    private static HashMap<Side, Rectangle> ParseExits(XMLParser parser) throws Exception
    {
        HashMap<Side, Rectangle> roomChangeTriggers = new HashMap<>();

        ArrayList<StageObjectRectProperties> exits
                = ParseChildRectProperties(parser, StageConstants.GetExitIndexFormatXPath());

        for(StageObjectRectProperties props : exits)
        {
            Side exitSide = ConversionHelper.StringToSide(props.GetName());

            if(exitSide == null) continue;

            roomChangeTriggers.put(exitSide, new Rectangle(props.GetXLoc(), props.GetYLoc(),
                    props.GetWidth(), props.GetHeight()));
        }

        return roomChangeTriggers;
    }

    private static HashMap<Side, Rectangle> ParseEntrances(XMLParser parser) throws Exception
    {
        HashMap<Side, Rectangle> roomEntrances = new HashMap<>();

        ArrayList<StageObjectRectProperties> entrances
                = ParseChildRectProperties(parser, StageConstants.GetEntranceIndexFormatXPath());

        for(StageObjectRectProperties props : entrances)
        {
            Side entranceSide = ConversionHelper.StringToSide(props.GetName());

            if(entranceSide == null) continue;

            roomEntrances.put(entranceSide, new Rectangle(props.GetXLoc(), props.GetYLoc(),
                    props.GetWidth(), props.GetHeight()));
        }

        return roomEntrances;
    }

    private static ArrayList<Backdrop> ParseBackdrops(XMLParser parser) throws Exception
    {
        ArrayList<Backdrop> backdrops = new ArrayList<>();

        ArrayList<StageObjectRectProperties> backdropProperties
                = ParseChildRectProperties(parser, StageConstants.GetBackdropIndexFormatXPath());

        for(StageObjectRectProperties props : backdropProperties)
        {
            String filePath = GameObjectConstants.GetImageFilePathFromId(props.GetName(), ObjectCategory.Backdrop);

            Image img = new Image(new File(filePath).toURI().toString());

            Backdrop b = new Backdrop(img, new Point(props.GetXLoc(), props.GetYLoc()), props.GetRenderLayer());

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
            String filePath = GameObjectConstants.GetImageFilePathFromId(props.GetName(), ObjectCategory.Floor);

            Image img = new Image(new File(filePath).toURI().toString());

            Floor f = new Floor(img, new Point(props.GetXLoc(), props.GetYLoc()), props.GetRenderLayer());

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
            String filePath = GameObjectConstants.GetImageFilePathFromId(props.GetName(), ObjectCategory.Wall);

            Image img = new Image(new File(filePath).toURI().toString());

            Wall w = new Wall(img, new Point(props.GetXLoc(), props.GetYLoc()), props.GetRenderLayer());

            walls.add(w);
        }

        return walls;
    }

    private static ArrayList<PropBase> ParseProps(XMLParser parser) throws Exception
    {
        ArrayList<PropBase> propInstances = new ArrayList<>();

        ArrayList<StageObjectRectProperties> propProps
                = ParseChildRectProperties(parser, StageConstants.GetPropIndexFormatXPath());

        for(StageObjectRectProperties props : propProps)
        {
            PropBase p = GameObjectConstants.GetPropInstanceFromId(props.GetName(), props);

            if(p != null)
                propInstances.add(p);
        }

        return propInstances;
    }

    private static ArrayList<EnemyBase> ParseEnemies(XMLParser parser) throws Exception
    {
        ArrayList<EnemyBase> enemies = new ArrayList<>();

        ArrayList<StageObjectRectProperties> enemyProperties
                = ParseChildRectProperties(parser, StageConstants.GetEnemyIndexFormatXPath());

        for(StageObjectRectProperties props : enemyProperties)
        {
            EnemyBase e = GameObjectConstants.GetEnemyInstanceFromId(props.GetName(), props);

            if(e != null)
                enemies.add(e);
        }

        return enemies;
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
