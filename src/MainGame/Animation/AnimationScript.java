package MainGame.Animation;

import MainGame.Animation.Steps.ScriptStep;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

/**
 * Base script container.
 * @param <T> Type of AnimationExecutionEvent.
 */
public abstract class AnimationScript<T extends AnimationExecutionEvent>
{
    //Private Variables
    private T _event;
    private ArrayList<ScriptStep> _steps;

    //Constructor
    public AnimationScript()
    {
        _steps = GenerateScript();
    }

    //Public Methods
    public void Execute(T e, GraphicsContext gc)
    {
        _event = e;
    }

    //Abstract Methods
    protected abstract ArrayList<ScriptStep> GenerateScript();

    //In here we are going to calculate a move distance
    //stage attacker move animation
    //stage user input animation
    //stage attacker attack animation
    //stage defender(s) recoil animation
    //stage attacker return animation

}
