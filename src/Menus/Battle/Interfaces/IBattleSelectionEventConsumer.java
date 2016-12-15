package Menus.Battle.Interfaces;

import Menus.Battle.EnemySelection.AbilitySelectionEvent;

/**
 * Interface defining reception of menu selections in the battle menus.
 */
public interface IBattleSelectionEventConsumer
{
    public void OnAbilitySelection(AbilitySelectionEvent e);
}
