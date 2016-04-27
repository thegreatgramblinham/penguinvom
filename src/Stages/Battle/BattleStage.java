package Stages.Battle;

import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import GameObjects.Environmental.Backdrop;
import MainGame.GameConstants;
import MainGame.ViewPort;
import SectorBase.Sector;
import Stages.StageObject;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;


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
    @Override
    protected void InitProps()
    {
        Backdrop trimCurtain = new Backdrop(new Rectangle(
            ViewPort.SecLocX(0),
            ViewPort.SecLocY(-100),
            (int) GetSkyTexture().getWidth(),
            (int) GetSkyTexture().getHeight()),
            false, false, "trimCurtain");
        trimCurtain.SetSprite(new Image(
                new File("src/ImageAssets/backgrounds/battleStage/stageTrim.png")
                        .toURI().toString()));
        _sector.AddObject(trimCurtain, GameConstants.PROP_RENDER_GROUP_FORWARD,
                GameConstants.PROP_COLLISION_GROUP);

        Backdrop mainCurtainLeft = new Backdrop(new Rectangle(
                ViewPort.SecLocX(30),
                ViewPort.SecLocY(-760),
                (int) GetSkyTexture().getWidth(),
                (int) GetSkyTexture().getHeight()),
                false, false, "mainCurtainLeft");
        mainCurtainLeft.SetSprite(new Image(
                new File("src/ImageAssets/backgrounds/battleStage/mainCurtainLeft.png")
                        .toURI().toString()));
        _sector.AddObject(mainCurtainLeft, GameConstants.PROP_RENDER_GROUP_MID,
                GameConstants.PROP_COLLISION_GROUP);

    }

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
