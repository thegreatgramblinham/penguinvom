package MainGame.Animation.Scripts;

import MainGame.Animation.AnimationScript;
import MainGame.Animation.Events.AbilityExecutionEvent;
import MainGame.Animation.Steps.ScriptStep;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by Sam on 09/12/2016.
 */
public class AbilityAnimationScript extends AnimationScript<AbilityExecutionEvent>
{

    //Constructor
    public AbilityAnimationScript()
    {

    }

    //Private Methods
    @Override
    protected ArrayList<ScriptStep> GenerateScript()
    {
        throw new NotImplementedException();
    }
}
