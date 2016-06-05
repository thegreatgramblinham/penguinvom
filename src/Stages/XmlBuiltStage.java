package Stages;

import GameObjectBase.enums.Side;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Environmental.Props.PropBase;
import Stages.Objects.Scenery.Backdrop;
import Stages.Objects.Scenery.Floor;
import Stages.Objects.Scenery.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class XmlBuiltStage
{
    //Private Variables
    private int _stageWidth;
    private int _stageHeight;
    private HashMap<Side, Rectangle> _exits;
    private HashMap<Side, Rectangle> _entrances;
    private ArrayList<Backdrop> _backdrops;
    private ArrayList<Floor> _floors;
    private ArrayList<Wall> _walls;
    private ArrayList<PropBase> _props;
    private ArrayList<EnemyBase> _enemies;

    //Constructor
    public XmlBuiltStage(int stageWidth, int stageHeight, HashMap<Side, Rectangle> exits,
            HashMap<Side, Rectangle> entrances, ArrayList<Backdrop> backdrops,
            ArrayList<Floor> floors, ArrayList<Wall> walls, ArrayList<PropBase> props,
            ArrayList<EnemyBase> enemies)
    {
        _stageWidth = stageWidth;
        _stageHeight = stageHeight;

        _exits = exits;
        _entrances = entrances;

        _backdrops = backdrops;
        _floors = floors;
        _walls = walls;
        _props = props;
        _enemies = enemies;
    }

    //Get Methods

    //Set Methods
}
