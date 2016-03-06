package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.DagronAi;
import GameObjects.Characters.Enemies.AI.enums.AiAction;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Dagron extends EnemyBase
{
    public Dagron(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation("src/ImageAssets/enemies/dagron0000.png", 64, 64, 7, 60, true),
                new SpriteAnimation("src/ImageAssets/enemies/dagron0000.png", 64, 64, 2, 60, true));
        this.SetAlias("Dagron");
        this.SetAi(new DagronAi(this));
    }

    @Override
    public boolean PerformAndDrawAction(GraphicsContext gc)
    {
        if(_ai == null) return false;

        AiAction act = _ai.QueryAction();

        switch(act)
        {
            case Advance:
                this.Advance(gc);
                break;
            case Attack:
                this.Attack(gc);
                break;
            case Retreat:
                break;
            case Stand:
                this.Stand(gc);
                break;
        }

        return true;
    }

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
        this.DrawWalkAnimation(gc); //Needs to be draw resting animation
        _ai.Stand();
    }

    @Override
    public Object clone()
    {
        return new Dagron(GetBounds(),GetMass(), GetHealth());
    }
}
