package MainGame.Battle;

import GameObjects.Characters.CharacterBase;
import Player.Moves.Move;

public class MoveExecutionEvent
{
    //Properties
    private Move _moveToPerform;
    private CharacterBase _attacker;
    private CharacterBase _defender;

    //Constructor
    public MoveExecutionEvent(Move move, CharacterBase attacker,
                              CharacterBase defender)
    {
        _moveToPerform = move;
        _attacker = attacker;
        _defender = defender;
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

    private CharacterBase GetDefender()
    {
        return _defender;
    }
}
