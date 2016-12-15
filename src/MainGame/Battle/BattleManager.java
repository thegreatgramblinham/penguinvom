package MainGame.Battle;

import MainGame.Animation.AnimationScript;
import MainGame.Animation.Events.BasicJumpAbilityAnimationExecutionEvent;
import MainGame.Animation.Scripts.AbilityAnimationScript;
import MainGame.Animation.Scripts.BasicJumpAbilityAnimationScript;
import MainGame.Battle.enums.Turn;
import Menus.Battle.BattleMenuManager;
import Menus.Battle.EnemySelection.AbilitySelectionEvent;
import Menus.Battle.Interfaces.IBattleSelectionEventConsumer;
import Stages.BattleStage;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BattleManager implements IBattleSelectionEventConsumer
{
    //Variables
    private BattleStage _stage;
    private Turn _turn;
    private BattleMenuManager _menuManager;
    private GraphicsContext gc;

    private HashSet<AbilityAnimationScript> _currentlyRunningAnimations;

    //Constructor
    public BattleManager(BattleStage stage)
    {
        _stage = stage;
        InitMenus();
        _currentlyRunningAnimations = new HashSet<>();
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

    public void DrawBattleAnimationFrame(GraphicsContext gc)
    {
        HashSet<AbilityAnimationScript> toRemove = new HashSet<>();

        for(AbilityAnimationScript script : _currentlyRunningAnimations)
        {
            script.Execute(
                    new BasicJumpAbilityAnimationExecutionEvent(
                            script.GetAbility(), script.GetUser(), script.GetTargets()),
                    gc);

            if(script.IsCompleted())
                toRemove.add(script);
        }

        for(AbilityAnimationScript script : toRemove)
        {
            _currentlyRunningAnimations.remove(script);
        }
    }

    @Override
    public void OnAbilitySelection(AbilitySelectionEvent e)
    {
        System.out.println("Selection Fired!");

        BasicJumpAbilityAnimationScript script
                = new BasicJumpAbilityAnimationScript(e.GetAbility(), e.GetUser(), e.GetTargets(), 180);
        _currentlyRunningAnimations.add(script);
    }

    //Private Methods
    private void InitMenus()
    {
        _menuManager = new BattleMenuManager(_stage);
        _menuManager.Subscribe(this);
    }


}
