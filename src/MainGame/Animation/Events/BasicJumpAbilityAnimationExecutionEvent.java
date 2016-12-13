package MainGame.Animation.Events;

import CharacterFunctions.Abilities.Ability;
import GameObjects.Characters.CharacterBase;

import java.util.List;

/**
 * Holds state of game objects at time of Basic Jump animation.
 */
public class BasicJumpAbilityAnimationExecutionEvent extends AbilityAnimationExecutionEvent
{

    public BasicJumpAbilityAnimationExecutionEvent(Ability ability, CharacterBase user, List<CharacterBase> targets)
    {
        super(ability, user, targets);


    }
}
