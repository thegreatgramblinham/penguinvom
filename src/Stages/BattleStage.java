package Stages;

import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import MainGame.GameConstants;
import SectorBase.Sector;

import java.awt.*;


public abstract class BattleStage extends StageObject
{
    //Private Variables
    protected PlayerBattleCharacter _playerCharacter;
    protected BattleCharacterGroup<EnemyBattleCharacter> _enemyCharacters;

    private Point _uiRoot;

    //Constructor
    public BattleStage(String roomId, Sector sector, Point uiRoot, PlayerBattleCharacter player,
                       BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        super(roomId, sector);

        _uiRoot = uiRoot;
        _playerCharacter = player;
        _enemyCharacters = enemies;

        InitCharacterPositions();
    }

    //Abstract Methods
    public abstract Point GetViewLocation();
    public abstract Point GetPlayerCharacterLocation();
    public abstract Point GetPartnerCharacterLocation();
    public abstract Point[] GetEnemyCharacterLocation();

    //Set Methods

    //Get Methods
    public Point GetUIRoot()
    {
        return _uiRoot;
    }

    //Private Methods
    protected void InitCharacterPositions()
    {
        //Player
        _sector.AddObject(_playerCharacter, GameConstants.PLAYER_RENDER_GROUP,
                GameConstants.PLAYER_COLLISION_GROUP);
        _playerCharacter.NSetLocation(GetPlayerCharacterLocation());

        //Enemies
        Point[] enemyLocations = GetEnemyCharacterLocation();
        for(EnemyBattleCharacter enemyCharacter : _enemyCharacters)
        {
            _sector.AddObject(enemyCharacter, GameConstants.PLAYER_RENDER_GROUP,
                    GameConstants.PLAYER_COLLISION_GROUP);
            enemyCharacter.NSetLocation(enemyLocations[0]);
        }
    }
}
