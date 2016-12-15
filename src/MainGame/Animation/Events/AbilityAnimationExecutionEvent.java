package MainGame.Animation.Events;

import CharacterFunctions.Abilities.Ability;
import GameObjects.BattleCharacters.BattleCharacterBase;
import MainGame.Animation.AnimationExecutionEvent;

import java.util.List;

public abstract class AbilityAnimationExecutionEvent extends AnimationExecutionEvent
{
    //Properties
    private Ability _ability;
    private BattleCharacterBase _user;
    private List<BattleCharacterBase> _targets;

    //Constructor
    public AbilityAnimationExecutionEvent(Ability ability, BattleCharacterBase user, List<BattleCharacterBase> targets)
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

    public BattleCharacterBase GetUser()
    {
        return _user;
    }

    public List<BattleCharacterBase> GetTargets()
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
