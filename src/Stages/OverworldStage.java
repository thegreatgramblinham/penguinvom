package Stages;

import GameObjectBase.enums.Side;
import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import GameObjects.Environmental.Backdrop;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;
import Stages.Battle.BattleStage;

import java.awt.*;

public abstract class OverworldStage extends StageObject
{
    //Constructor
    public OverworldStage(Sector sector) throws Exception
    {
        super(sector);

        Init();
    }

    //Abstract Methods
    protected abstract void InitExits();
    public abstract Point GetPlayerStartingLocation(Side s);
    public abstract BattleStage CreateBattleStage(
            PlayerBattleCharacter player,
            BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception;

    //Public Methods
    public Point GetViewLocation()
    {
        return new Point(
                (int)(GameConstants.GAME_STARTING_POINT.x),
                GameConstants.GAME_STARTING_POINT.y);
    }

    //Private Methods
    private void Init()
    {
        InitStageBounds();
    }

    private void InitStageBounds()
    {
        //Non-rendered Game Bounds
        Backdrop topBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(1), GetStageWidth(),1), true, true,
                "TopBounds");
        _sector.AddObject(topBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP);

        Backdrop botBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(GetStageHeight()), GetStageWidth(),1),
                true, true, "BottomBounds");
        _sector.AddObject(botBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP);

        Backdrop leftBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(1),
                ViewPort.SecLocY(1),1, GetStageHeight()), true, true,
                "LeftBounds");
        _sector.AddObject(leftBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP);

        Backdrop rightBound = new Backdrop(new Rectangle(
                ViewPort.SecLocX(GetStageWidth()),
                ViewPort.SecLocY(1), 1, GetStageHeight()),
                true, true, "RightBounds");
        _sector.AddObject(rightBound, GameConstants.ROOM_RENDER_GROUP
                , GameConstants.PLAYER_GAMEBOUNDS_COLLISION_GROUP);
    }

}
