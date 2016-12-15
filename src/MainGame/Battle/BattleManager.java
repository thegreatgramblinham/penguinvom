package MainGame.Battle;

import MainGame.Animation.Scripts.BasicJumpAbilityAnimationScript;
import MainGame.Battle.enums.Turn;
import Menus.Battle.BattleMenuManager;
import Menus.Battle.EnemySelection.AbilitySelectionEvent;
import Menus.Battle.Interfaces.IBattleSelectionEventConsumer;
import Stages.BattleStage;
import javafx.scene.canvas.GraphicsContext;

public class BattleManager implements IBattleSelectionEventConsumer
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

    @Override
    public void OnAbilitySelection(AbilitySelectionEvent e)
    {
        System.out.println("Selection Fired!");

        //BasicJumpAbilityAnimationScript script = new BasicJumpAbilityAnimationScript();
    }

    //Private Methods
    private void InitMenus()
    {
        _menuManager = new BattleMenuManager(_stage);
        _menuManager.Subscribe(this);
    }


}
