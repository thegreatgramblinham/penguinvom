package MainGame.Animation.Steps;

import MainGame.Animation.AnimationExecutionEvent;
import MainGame.Animation.enums.CameraAction;
import javafx.scene.canvas.GraphicsContext;

import java.util.UUID;

/**
 * Serves as a container for each phase of an object animation
 * e.g. character move, tree shake, etc. that would be part of something
 * like a cutscene.
 */
public abstract class ScriptStep<T extends AnimationExecutionEvent>
{
    //Private Variables
    protected int _framesAlloted;
    protected int _frameCounter;
    private UUID _id;

    //Properties
    private CameraAction _camAction;

    //Constructor
    protected ScriptStep(CameraAction camAction, int framesAlloted)
    {
        _camAction = camAction;
        _framesAlloted = framesAlloted;
        _frameCounter = 0;
        _id = UUID.randomUUID();
    }

    //Abstract Methods
    public abstract void Execute(T event, GraphicsContext gc);

    //Public Methods
    public void ExcuteCameraActionFrame()
    {
        //Todo mess with the viewport here.
    }

    public int GetFramesAllotted()
    {
        return _framesAlloted;
    }

    public boolean Complete()
    {
        return _frameCounter >= _framesAlloted;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScriptStep<?> that = (ScriptStep<?>) o;

        return _id.equals(that._id);

    }

    @Override
    public int hashCode()
    {
        return _id.hashCode();
    }
}
