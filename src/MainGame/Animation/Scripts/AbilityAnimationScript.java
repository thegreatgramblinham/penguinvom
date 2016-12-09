package MainGame.Animation.Scripts;

import CharacterFunctions.Abilities.Ability;
import MainGame.Animation.AnimationScript;
import MainGame.Animation.Events.AbilityExecutionEvent;
import MainGame.Animation.Steps.ScriptStep;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Controls an animation for the given ability object.
 */
public class AbilityAnimationScript extends AnimationScript<AbilityExecutionEvent>
{
    //Private Variables
    private Ability _ability;

    //Constructor
    public AbilityAnimationScript(Ability ability)
    {
        _ability = ability;
    }

    //Private Methods
    @Override
    protected ArrayList<ScriptStep> GenerateScript()
    {
        throw new NotImplementedException();
    }
}
