package MainGame.Animation.Scripts;

import CharacterFunctions.Abilities.Ability;
import MainGame.Animation.Events.BasicJumpAbilityAnimationExecutionEvent;
import MainGame.Animation.Steps.ScriptStep;

import java.util.ArrayList;

/**
 * Animation of a basic jump attack.
 */
public class BasicJumpAbilityAnimationScript extends AbilityAnimationScript<BasicJumpAbilityAnimationExecutionEvent>
{
    //Variables

    //Constructor
    public BasicJumpAbilityAnimationScript(Ability ability)
    {
        super(ability);
    }

    //Private Methods
    @Override
    protected ArrayList<ScriptStep> GenerateScript()
    {
        return null;
    }
}
