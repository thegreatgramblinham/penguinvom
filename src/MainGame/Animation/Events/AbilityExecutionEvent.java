package MainGame.Animation.Events;

import GameObjects.Characters.CharacterBase;
import MainGame.Animation.AnimationExecutionEvent;
import Player.Moves.Move;

import java.util.Collection;

public class AbilityExecutionEvent extends AnimationExecutionEvent
{
    //Properties
    private Move _moveToPerform;
    private CharacterBase _attacker;
    private Collection<CharacterBase> _defenders;

    //Constructor
    public AbilityExecutionEvent(Move move, CharacterBase attacker,
                                 Collection<CharacterBase> defenders)
    {
        _moveToPerform = move;
        _attacker = attacker;
        _defenders = defenders;
    }

    //Get Methods
    private Move GetMove()
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
