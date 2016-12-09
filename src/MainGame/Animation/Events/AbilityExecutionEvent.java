package MainGame.Animation.Events;

import CharacterFunctions.Abilities.Ability;
import GameObjects.Characters.CharacterBase;
import MainGame.Animation.AnimationExecutionEvent;

import java.util.Collection;

public class AbilityExecutionEvent<T extends Ability> extends AnimationExecutionEvent
{
    //Properties
    private T _moveToPerform;
    private CharacterBase _attacker;
    private Collection<CharacterBase> _defenders;

    //Constructor
    public AbilityExecutionEvent(T move, CharacterBase attacker,
                                 Collection<CharacterBase> defenders)
    {
        _moveToPerform = move;
        _attacker = attacker;
        _defenders = defenders;
    }

    //Get Methods
    private T GetAbility()
    {
        return _moveToPerform;
    }

    private CharacterBase GetAttacker()
    {
        return _attacker;
    }

    private Collection<CharacterBase> GetDefenders()
    {
        return _defenders;
    }

    //In here we are going to calculate a move distance
    //stage attacker move animation
    //stage user input animation
    //stage attacker attack animation
    //stage defender(s) recoil animation
    //stage attacker return animation
}
