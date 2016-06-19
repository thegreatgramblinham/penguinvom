package Stages;

import GameObjects.BattleCharacters.BattleCharacterGroup;
import GameObjects.BattleCharacters.EnemyBattleCharacter;
import GameObjects.BattleCharacters.PlayerBattleCharacter;
import SectorBase.Sector;
import javafx.scene.image.Image;

import java.awt.*;

public class XmlBuiltBattleStage extends BattleStage
{
    //Variables

    //Constructor
    public XmlBuiltBattleStage(String roomId, Sector sector,
            PlayerBattleCharacter player,
            BattleCharacterGroup<EnemyBattleCharacter> enemies) throws Exception
    {
        super(roomId, sector, player, enemies);
    }

    //Get Methods
    @Override
    protected int GetStageHeight()
    {
        return 0;
    }

    @Override
    protected int GetStageWidth()
    {
        return 0;
    }

    @Override
    protected Image GetBackgroundTexture()
    {
        return null;
    }

    @Override
    protected Image GetWallTexture()
    {
        return null;
    }

    @Override
    protected Image GetFloorTexture()
    {
        return null;
    }

    @Override
    public Point GetViewLocation()
    {
        return null;
    }

    @Override
    public Point GetPlayerCharacterLocation()
    {
        return null;
    }

    @Override
    public Point GetPartnerCharacterLocation()
    {
        return null;
    }

    @Override
    public Point[] GetEnemyCharacterLocation()
    {
        return new Point[0];
    }

    //Private Methods
    @Override
    protected void InitObjects()
    {

    }

    @Override
    protected void InitProps()
    {

    }

    @Override
    protected void InitBackdrop()
    {

    }

    @Override
    protected void InitWall()
    {

    }

    @Override
    protected void InitFloor()
    {

    }

}
