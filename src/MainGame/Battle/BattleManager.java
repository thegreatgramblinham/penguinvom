package MainGame.Battle;

import MainGame.Battle.enums.Turn;
import Menus.WindowManager;
import Stages.Battle.BattleStage;

public class BattleManager
{
    //Variables
    private BattleStage _stage;
    private Turn _turn;
    private WindowManager _windowManager;


    //Constructor
    public BattleManager(BattleStage stage)
    {
        _stage = stage;
    }

    //Get Methods
    public BattleStage GetStage()
    {
        return _stage;
    }

    //Set Methods

    //Public Methods

    //Private Methods
    private void InitMenus()
    {

    }

}
