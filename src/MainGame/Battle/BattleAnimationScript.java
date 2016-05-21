package MainGame.Battle;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class BattleAnimationScript
{
    //Private Variables
    private MoveExecutionEvent _event;
    private ArrayList<BattleScriptStep> _steps;

    //Constructor
    public BattleAnimationScript(MoveExecutionEvent e)
    {
        _event = e;
        GenerateScript();
    }

    //Public Methods
    public void Execute(GraphicsContext gc)
    {

    }

    //Private Methods
    private void GenerateScript()
    {
        //In here we are going to calculate a move distance

        //stage attacker move animation

        //stage user input animation

        //stage attacker attack animation

        //stage defender(s) recoil animation

        //stage attacker return animation

    }
}
