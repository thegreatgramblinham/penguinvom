package GameObjects.Characters.Enemies.AI.interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface IAiController
{
    boolean PerformAndDrawAction(GraphicsContext gc);

    void Advance(GraphicsContext gc);

    void Attack(GraphicsContext gc);

    void Retreat(GraphicsContext gc);

    void Stand(GraphicsContext gc);
}
