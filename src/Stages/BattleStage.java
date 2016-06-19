package Stages;

import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import MainGame.GameConstants;
import SectorBase.Sector;

import java.awt.*;


public abstract class BattleStage extends StageObject
{

    //Variables
    protected PlayerBattleCharacter _playerCharacter;
    protected BattleCharacterGroup<EnemyBattleCharacter> _enemyCharacters;

    //Constructor
    public BattleStage(String roomId, Sector sector, PlayerBattleCharacter player,
                       BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        super(roomId, sector);
        _playerCharacter = player;
        _enemyCharacters = enemies;

        InitCharacterPositions();
    }

    //Abstract Methods
    public abstract Point GetViewLocation();
//    {
//        return new Point(
//                (int)(GameConstants.GAME_STARTING_POINT.x + 170),
//                GameConstants.GAME_STARTING_POINT.y - 75);
//    }

    public abstract Point GetPlayerCharacterLocation();
//    {
//        return new Point(ViewPort.SecLocX(325), ViewPort.SecLocY(300));
//    }

    public abstract Point GetPartnerCharacterLocation();

    public abstract Point[] GetEnemyCharacterLocation();
//    {
//        Point[] pointArr = new Point[_enemyCharacters.size()];
//
//        pointArr[0] = new Point(ViewPort.SecLocX(1025), ViewPort.SecLocY(300));
//
//        return pointArr;
//    }

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
        Point[] enemyLocations = GetEnemyCharacterLocation();
        for(EnemyBattleCharacter enemyCharacter : _enemyCharacters)
        {
            _sector.AddObject(enemyCharacter, GameConstants.PLAYER_RENDER_GROUP,
                    GameConstants.PLAYER_COLLISION_GROUP);
            enemyCharacter.NSetLocation(enemyLocations[0]);
        }
    }

//    @Override
//    protected void InitProps()
//    {
//        Backdrop trimCurtain = new Backdrop(new Rectangle(
//            ViewPort.SecLocX(0),
//            ViewPort.SecLocY(-100),
//            (int) GetBackgroundTexture().getWidth(),
//            (int) GetBackgroundTexture().getHeight()),
//            false, false, "trimCurtain");
//        trimCurtain.SetSprite(new Image(
//                new File("src/ImageAssets/backgrounds/stageTrim1.png")
//                        .toURI().toString()));
//        _sector.AddObject(trimCurtain, GameConstants.CURTAIN_RENDER_GROUP_5,
//                GameConstants.NO_COLLISION_GROUP);
//
//        //main traveller left
//        Image mainCurtainLeftImage = new Image(
//                new File("src/ImageAssets/backgrounds/mainCurtainLeft1.png")
//                        .toURI().toString());
//        Backdrop mainCurtainLeft = new Backdrop(new Rectangle(
//                ViewPort.SecLocX(30),
//                ViewPort.SecLocY(-375),
//                (int) mainCurtainLeftImage.getWidth(),
//                (int) mainCurtainLeftImage.getHeight()),
//                false, false, "mainCurtainLeft1");
//        mainCurtainLeft.SetSprite(mainCurtainLeftImage);
//        _sector.AddObject(mainCurtainLeft, GameConstants.CURTAIN_RENDER_GROUP_4,
//                GameConstants.NO_COLLISION_GROUP);
//
//        Image rearCurtainLeftImage = new Image(
//                new File("src/ImageAssets/backgrounds/rearCurtainLeft1.png").toURI().toString());
//
//        //Left mid leg
//        Backdrop rearCurtainLeft = new Backdrop(new Rectangle(
//                ViewPort.SecLocX(60),
//                ViewPort.SecLocY(-410),
//                (int) rearCurtainLeftImage.getWidth(),
//                (int) rearCurtainLeftImage.getHeight()),
//                false, false, "rearCurtainMidLeft");
//        rearCurtainLeft.SetSprite(rearCurtainLeftImage);
//        _sector.AddObject(rearCurtainLeft, GameConstants.CURTAIN_RENDER_GROUP_3,
//                GameConstants.NO_COLLISION_GROUP);
//
//        //Upstage leg left
//        rearCurtainLeft = new Backdrop(new Rectangle(
//                ViewPort.SecLocX(90),
//                ViewPort.SecLocY(-455),
//                (int) rearCurtainLeftImage.getWidth(),
//                (int) rearCurtainLeftImage.getHeight()),
//                false, false, "rearCurtainUpstageLeft");
//        rearCurtainLeft.SetSprite(rearCurtainLeftImage);
//        _sector.AddObject(rearCurtainLeft, GameConstants.CURTAIN_RENDER_GROUP_2,
//                GameConstants.NO_COLLISION_GROUP);
//
//
//        //main traveller Right
//        Image mainCurtainRightImage = new Image(
//                new File("src/ImageAssets/backgrounds/mainCurtainRight1.png")
//                        .toURI().toString());
//        Backdrop mainCurtainRight = new Backdrop(new Rectangle(
//                ViewPort.SecLocX(1162),
//                ViewPort.SecLocY(-375),
//                (int) mainCurtainRightImage.getWidth(),
//                (int) mainCurtainRightImage.getHeight()),
//                false, false, "mainCurtainRight1");
//        mainCurtainRight.SetSprite(mainCurtainRightImage);
//        _sector.AddObject(mainCurtainRight, GameConstants.CURTAIN_RENDER_GROUP_4,
//                GameConstants.NO_COLLISION_GROUP);
//
//        Image rearCurtainRightImage = new Image(
//                new File("src/ImageAssets/backgrounds/rearCurtainRight1.png").toURI().toString());
//
//        //Right mid leg
//        Backdrop rearCurtainRight = new Backdrop(new Rectangle(
//                ViewPort.SecLocX(1132),
//                ViewPort.SecLocY(-410),
//                (int) rearCurtainRightImage.getWidth(),
//                (int) rearCurtainRightImage.getHeight()),
//                false, false, "rearCurtainMidRight");
//        rearCurtainRight.SetSprite(rearCurtainRightImage);
//        _sector.AddObject(rearCurtainRight, GameConstants.CURTAIN_RENDER_GROUP_3,
//                GameConstants.NO_COLLISION_GROUP);
//
//        //Upstage leg Right
//        rearCurtainRight = new Backdrop(new Rectangle(
//                ViewPort.SecLocX(1102),
//                ViewPort.SecLocY(-455),
//                (int) rearCurtainRightImage.getWidth(),
//                (int) rearCurtainRightImage.getHeight()),
//                false, false, "rearCurtainUpstageRight");
//        rearCurtainRight.SetSprite(rearCurtainRightImage);
//        _sector.AddObject(rearCurtainRight, GameConstants.CURTAIN_RENDER_GROUP_2,
//                GameConstants.NO_COLLISION_GROUP);
//
//    }



}