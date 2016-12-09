package MainGame.Animation.Events;

import CharacterFunctions.Abilities.Ability;
import GameObjects.Characters.CharacterBase;
import MainGame.Animation.AnimationExecutionEvent;

import java.util.Collection;

public class AbilityExecutionEvent extends AnimationExecutionEvent
{
    //Properties
    private Ability _moveToPerform;
    private CharacterBase _attacker;
    private Collection<CharacterBase> _defenders;

    //Constructor
    public AbilityExecutionEvent(Ability move, CharacterBase attacker,
                                 Collection<CharacterBase> defenders)
    {
        _moveToPerform = move;
        _attacker = attacker;
        _defenders = defenders;
    }

    //Get Methods
    private Ability GetMove()
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
}
