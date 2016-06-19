package MainGame.Battle;

import MainGame.Battle.enums.Turn;
import Menus.Battle.BattleMenuManager;
import Stages.BattleStage;
import javafx.scene.canvas.GraphicsContext;

public class BattleManager
{
    //Variables
    private BattleStage _stage;
    private Turn _turn;
    private BattleMenuManager _menuManager;

    //Constructor
    public BattleManager(BattleStage stage)
    {
        _stage = stage;
        InitMenus();
    }

    //Get Methods
    public BattleStage GetStage()
    {
        return _stage;
    }

    //Set Methods

    //Public Methods
    public void DrawMenus(GraphicsContext gc)
    {
        _menuManager.DrawMenus(gc);
    }

    public void HandleKeyPress()
    {
        //if in attack animation

        //if in menu
        _menuManager.HandleKeyPress();
    }

    //Private Methods
    private void InitMenus()
    {
        _menuManager = new BattleMenuManager(_stage);
    }

}
