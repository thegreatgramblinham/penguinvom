package MainGame.Animation;

import MainGame.Animation.Steps.ScriptStep;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Base script container.
 * @param <T> Type of AnimationExecutionEvent.
 */
public abstract class AnimationScript<T extends AnimationExecutionEvent>
{
    //Private Variables
    private int _totalFramesAllotted;

    private int _currentScriptIndex;
    private int _totalFrameCounter;
    private int _currentFrameCounter;

    private ArrayList<ScriptStep> _steps;
    private LinkedHashMap<ScriptStep, Integer> _stepToFrameMap;

    private UUID _instanceId;

    //Constructor
    public AnimationScript(int totalFramesAllotted)
    {
        _totalFramesAllotted = totalFramesAllotted;
        _totalFrameCounter = 0;
        _currentFrameCounter = 0;
        _currentScriptIndex = 0;
        _instanceId = UUID.randomUUID();
        GenerateScriptFrameMap();
    }

    //Get Methods
    public int GetTotalFramesAllotted()
    {
        return _totalFramesAllotted;
    }

    //Public Methods
    public void Execute(T e, GraphicsContext gc)
    {
        if(this,IsCompleted()) return;

        ScriptStep currStep = _steps.get(_currentScriptIndex);

        currStep.Execute(e, gc);
        if(currStep.Complete())
        {
            _currentScriptIndex++;
            _currentFrameCounter = 0;
        }
        else
        {
            _currentFrameCounter++;
        }

        _totalFrameCounter++;
    }

    public boolean IsCompleted()
    {
        return _totalFrameCounter >= _totalFramesAllotted;
    }

    public void Reset()
    {
        _totalFrameCounter = 0;
        _currentFrameCounter = 0;
        _currentScriptIndex = 0;
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

    //Private Methods
    private void GenerateScriptFrameMap()
    {
        _steps = GenerateScript();
        _stepToFrameMap = new LinkedHashMap<>();

        for(ScriptStep step : _steps)
        {
            _stepToFrameMap.put(step, step.GetFramesAllotted());
        }
    }


}
