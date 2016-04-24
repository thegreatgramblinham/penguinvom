package Stages.Battle;

import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;
import Stages.StageObject;

import java.awt.*;


public abstract class BattleStage extends StageObject
{

    //Variables
    protected PlayerBattleCharacter _playerCharacter;
    protected BattleCharacterGroup<EnemyBattleCharacter> _enemyCharacters;

    //Constructor
    public BattleStage(Sector sector, PlayerBattleCharacter player,
                       BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        super(sector);
        _playerCharacter = player;
        _enemyCharacters = enemies;

        InitCharacterPositions();
    }

    //Get Methods
    public Point GetViewLocation()
    {
        return new Point(
                (int)(GameConstants.GAME_STARTING_POINT.x + 170),
                GameConstants.GAME_STARTING_POINT.y - 75);
    }

    public Point GetPlayerCharacterLocation()
    {
        return new Point(ViewPort.SecLocX(325), ViewPort.SecLocY(300));
    }

    public Point GetPartnerCharacterLocation()
    {
        return null;
    }

    public Point[] GetEnemyCharacterLocation(int enemyCount)
    {
        Point[] pointArr = new Point[enemyCount];

        pointArr[0] = new Point(ViewPort.SecLocX(1025), ViewPort.SecLocY(300));

        return pointArr;
    }

    //Set Methods

    //Public Methods

    //Private Methods
    private void InitCharacterPositions()
    {
        //Player
        _sector.AddObject(_playerCharacter, GameConstants.PLAYER_RENDER_GROUP,
                GameConstants.PLAYER_COLLISION_GROUP);
        _playerCharacter.NSetLocation(GetPlayerCharacterLocation());

        //Enemies
        Point[] enemyLocations = GetEnemyCharacterLocation(_enemyCharacters.size());
        for(EnemyBattleCharacter enemyCharacter : _enemyCharacters)
        {
            _sector.AddObject(enemyCharacter, GameConstants.PLAYER_RENDER_GROUP,
                    GameConstants.PLAYER_COLLISION_GROUP);
            enemyCharacter.NSetLocation(enemyLocations[0]);
        }


    }

}
