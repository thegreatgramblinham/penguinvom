package MainGame.Animation;

import MainGame.Animation.Steps.ScriptStep;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Base script container.
 * @param <T> Type of AnimationExecutionEvent.
 */
public abstract class AnimationScript<T extends AnimationExecutionEvent>
{
    //Private Variables
    private T _event;
    private int _totalFramesAllotted;
    private int _frameCounter;
    private ArrayList<ScriptStep> _steps;

    private UUID _instanceId;

    //Constructor
    public AnimationScript(int totalFramesAllotted)
    {
        _totalFramesAllotted = totalFramesAllotted;
        _steps = GenerateScript();
        _frameCounter = 0;
        _instanceId = UUID.randomUUID();
    }

    //Get Methods
    public int GetTotalFramesAllotted()
    {
        return _totalFramesAllotted;
    }

    //Public Methods
    public void Execute(T e, GraphicsContext gc)
    {
        //todo instead of foreach, determine which execute to call based on frame counter.
        for(ScriptStep step : _steps)
        {
            step.Execute(e, gc);
        }
        _frameCounter++;
    }

    public boolean IsCompleted()
    {
        return _frameCounter >= _totalFramesAllotted;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimationScript<?> script = (AnimationScript<?>) o;

        return _instanceId.equals(script._instanceId);
    }

    @Override
    public int hashCode()
    {
        return _instanceId.hashCode();
    }

    //Abstract Methods
    protected abstract ArrayList<ScriptStep> GenerateScript();


}
