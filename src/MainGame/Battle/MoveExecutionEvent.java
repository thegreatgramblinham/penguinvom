package MainGame.Battle;

import GameObjects.Characters.CharacterBase;
import Player.Moves.Move;

import java.util.Collection;

public class MoveExecutionEvent
{
    //Properties
    private Move _moveToPerform;
    private CharacterBase _attacker;
    private Collection<CharacterBase> _defenders;

    //Constructor
    public MoveExecutionEvent(Move move, CharacterBase attacker,
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
