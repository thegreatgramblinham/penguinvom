package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.DagronAi;
import GameObjects.Characters.Enemies.AI.enums.AiAction;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Skilleatin extends EnemyBase
{
    //Variables

    //Constructor
    public Skilleatin(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation("src/ImageAssets/enemies/skilleatin0000.png", 64, 64, 5, 60, true),
                new SpriteAnimation("src/ImageAssets/enemies/skilleatin0000.png", 64, 64, 2, 60, true));
        this.SetAlias("Skilleatin");
        this.SetAi(new DagronAi(this));
    }

    //Public Methods
    @Override
    public void Advance(GraphicsContext gc)
    {
        //Animation
        this.DrawWalkAnimation(gc);
        _ai.Advance();
    }

    @Override
    public void Attack(GraphicsContext gc)
    {
        //Draw attack animation.
        this.DrawWalkAnimation(gc);
        _ai.Attack();
    }

    @Override
    public void Retreat(GraphicsContext gc)
    {

    }

    @Override
    public void Stand(GraphicsContext gc)
    {

    }

    @Override
    public Object clone()
    {
        return new Skilleatin(GetBounds(),GetMass(), GetHealth());
    }

    //Private Methods

}
