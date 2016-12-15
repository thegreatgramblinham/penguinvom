package MainGame.Animation.Scripts;

import CharacterFunctions.Abilities.Ability;
import GameObjects.BattleCharacters.BattleCharacterBase;
import MainGame.Animation.AnimationScript;
import MainGame.Animation.Events.AbilityAnimationExecutionEvent;

import java.util.List;

/**
 * Controls an animation for the given ability object.
 */
public abstract class AbilityAnimationScript<T extends AbilityAnimationExecutionEvent> extends AnimationScript<T>
{
    //Private Variables
    private Ability _ability;
    private BattleCharacterBase _user;
    private List<BattleCharacterBase> _targets;

    //Constructor
    protected AbilityAnimationScript(Ability ability, BattleCharacterBase user,
            List<BattleCharacterBase> targets, int totalFramesAllotted)
    {
        super(totalFramesAllotted);

        _ability = ability;
        _user = user;
        _targets = targets;
    }

    //Get Methods
    public Ability GetAbility()
    {
        return _ability;
    }

    public BattleCharacterBase GetUser()
    {
        return _user;
    }

    public List<BattleCharacterBase> GetTargets()
    {
        return _targets;
    }
}
