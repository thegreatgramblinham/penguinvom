package Menus.Battle.EnemySelection;

import CharacterFunctions.Abilities.Ability;
import GameObjects.BattleCharacters.BattleCharacterBase;

import java.util.List;

/**
 * Container for all metadata related to an ability usage at execution.
 */
public class AbilitySelectionEvent
{
    //Private Variables
    private Ability _ability;
    private BattleCharacterBase _user;
    private List<BattleCharacterBase> _targets;

    //Constructor
    public AbilitySelectionEvent(Ability ability, BattleCharacterBase user, List<BattleCharacterBase> targets)
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
}
