package MainGame.Battle.MoveScripting.Steps;

import MainGame.Battle.MoveScripting.Steps.enums.CameraAction;
import javafx.scene.canvas.GraphicsContext;

public abstract class BattleScriptStep
{
    //TODO will serve as a container for each phase of a move animation
    //eg
    //attacker walk => attacker hit => defender recoil => attack return position

    //Protected Variables
    protected int _framesAlloted;
    protected int _frameCounter;

    //Properties
    private CameraAction _camAction;

    //Constructor
    public BattleScriptStep(CameraAction camAction, int framesAlloted)
    {
        _camAction = camAction;
        _framesAlloted = framesAlloted;
        _frameCounter = 0;
    }

    //Abstract Methods
    public abstract void Execute(GraphicsContext gc);
    protected abstract void InitMove();

    //Public Methods
    public void ExcuteCameraActionFrame()
    {
        //Todo mess with the viewport here.
    }

    public boolean MoveComplete()
    {
        return _frameCounter >= _framesAlloted;
    }

}
