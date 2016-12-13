package MainGame.Animation.Events;

import CharacterFunctions.Abilities.Ability;
import GameObjects.Characters.CharacterBase;
import MainGame.Animation.AnimationExecutionEvent;

import java.util.Collection;
import java.util.List;

public abstract class AbilityAnimationExecutionEvent extends AnimationExecutionEvent
{
    //Properties
    private Ability _ability;
    private CharacterBase _user;
    private List<CharacterBase> _targets;

    //Constructor
    public AbilityAnimationExecutionEvent(Ability ability, CharacterBase user, List<CharacterBase> targets)
    {
        _ability = ability;
        _user = user;
        _targets = targets;
    }

    //Get Methods
    public Ability GetAbility()
    {
        return _ability;
    }

    public CharacterBase GetUser()
    {
        return _user;
    }

    public List<CharacterBase> GetTargets()
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
