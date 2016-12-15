package Menus.Battle.Interfaces;

/**
 * Interface defining subscription to menu selections in the battle menus.
 */
public interface IBattleSelectionEventProducer
{
    public void Subscribe(IBattleSelectionEventConsumer consumer);
    public void Unsubscribe(IBattleSelectionEventConsumer consumer);
}
