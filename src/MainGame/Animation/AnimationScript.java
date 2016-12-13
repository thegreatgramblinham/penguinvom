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
    private int _totalFramesAllotted;
    private ArrayList<ScriptStep> _steps;

    //Constructor
    public AnimationScript(int totalFramesAllotted)
    {
        _totalFramesAllotted = totalFramesAllotted;
        _steps = GenerateScript();
    }

    //Get Methods
    public int GetTotalFramesAllotted()
    {
        return _totalFramesAllotted;
    }

    //Public Methods
    public void Execute(T e, GraphicsContext gc)
    {
        for(ScriptStep step : _steps)
        {
            step.Execute(e, gc);
        }
    }

    //Abstract Methods
    protected abstract ArrayList<ScriptStep> GenerateScript();


}
