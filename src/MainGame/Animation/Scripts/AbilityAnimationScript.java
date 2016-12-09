package MainGame.Animation.Scripts;

import CharacterFunctions.Abilities.Ability;
import MainGame.Animation.AnimationScript;
import MainGame.Animation.Events.AbilityExecutionEvent;
import MainGame.Animation.Steps.ScriptStep;
import javafx.scene.canvas.GraphicsContext;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Controls an animation for the given ability object.
 */
public class AbilityAnimationScript<T extends Ability> extends AnimationScript<AbilityExecutionEvent<T>>
{
    //Private Variables
    private T _ability;

    //Constructor
    public AbilityAnimationScript(T ability)
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
