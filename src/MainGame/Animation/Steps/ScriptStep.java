package MainGame.Animation.Steps;

import MainGame.Animation.AnimationExecutionEvent;
import MainGame.Animation.enums.CameraAction;
import javafx.scene.canvas.GraphicsContext;

/**
 * Serves as a container for each phase of an object animation
 * e.g. character move, tree shake, etc. that would be part of something
 * like a cutscene.
 */
public abstract class ScriptStep<T extends AnimationExecutionEvent>
{
    //Protected Variables
    protected int _framesAlloted;
    protected int _frameCounter;

    //Properties
    private CameraAction _camAction;

    //Constructor
    protected ScriptStep(CameraAction camAction, int framesAlloted)
    {
        _camAction = camAction;
        _framesAlloted = framesAlloted;
        _frameCounter = 0;
    }

    //Abstract Methods
    public abstract void Execute(T event, GraphicsContext gc);
    protected abstract void InitMove();

    //Public Methods
    public void ExcuteCameraActionFrame()
    {
        //Todo mess with the viewport here.
    }

    public boolean Complete()
    {
        return _frameCounter >= _framesAlloted;
    }

}
