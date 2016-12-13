package MainGame.Animation.Scripts;

import CharacterFunctions.Abilities.Ability;
import MainGame.Animation.AnimationScript;
import MainGame.Animation.Events.AbilityAnimationExecutionEvent;

/**
 * Controls an animation for the given ability object.
 */
public abstract class AbilityAnimationScript<T extends AbilityAnimationExecutionEvent> extends AnimationScript<T>
{
    //Private Variables
    private Ability _ability;

    //Constructor
    protected AbilityAnimationScript(Ability ability, int totalFramesAllotted)
    {
        super(totalFramesAllotted);

        _ability = ability;
    }

    //Get Methods
    private Ability GetAbility()
    {
        return _ability;
    }
}
