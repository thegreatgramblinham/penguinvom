package MainGame.Animation.Events;

import CharacterFunctions.Abilities.Ability;
import GameObjects.BattleCharacters.BattleCharacterBase;

import java.util.List;

/**
 * Holds state of game objects at time of Basic Jump animation.
 */
public class BasicJumpAbilityAnimationExecutionEvent extends AbilityAnimationExecutionEvent
{

    public BasicJumpAbilityAnimationExecutionEvent(Ability ability, BattleCharacterBase user, List<BattleCharacterBase> targets)
    {
        super(ability, user, targets);
    }
}
