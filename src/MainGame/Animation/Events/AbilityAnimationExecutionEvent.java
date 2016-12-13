package MainGame.Animation.Events;

import CharacterFunctions.Abilities.Ability;
import GameObjects.Characters.CharacterBase;
import MainGame.Animation.AnimationExecutionEvent;

import java.util.Collection;

public abstract class AbilityAnimationExecutionEvent extends AnimationExecutionEvent
{
    //Properties
    private Ability _ability;
    private CharacterBase _user;
    private Collection<CharacterBase> _targets;

    //Constructor
    public AbilityAnimationExecutionEvent(Ability ability, CharacterBase user, Collection<CharacterBase> targets)
    {
        _ability = ability;
        _user = user;
        _targets = targets;
    }

    //Get Methods
    private Ability GetAbility()
    {
        return _ability;
    }

    private CharacterBase GetUser()
    {
        return _user;
    }

    private Collection<CharacterBase> GetTargets()
    {
        return _targets;
    }

    //In here we are going to calculate a move distance
    //stage attacker move animation
    //stage user input animation
    //stage attacker attack animation
    //stage defender(s) recoil animation
    //stage attacker return animation
}
