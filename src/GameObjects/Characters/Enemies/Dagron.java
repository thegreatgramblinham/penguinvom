package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.DagronAi;
import GameObjects.Characters.Enemies.AI.enums.AiAction;
import MainGame.GameConstants;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Dagron extends EnemyBase
{
    //Private Constants
    private final static int WIDTH = 64;
    private final static int HEIGHT = 64;
    private final static float INITIAL_MOVEMENT_SPEED = 0.5F;

    //Constructor
    public Dagron(Point location, float mass, int health)
    {
        super(
                new Rectangle(location.x, location.y, WIDTH, HEIGHT),
                new Rectangle(location.x, location.y, WIDTH-20, HEIGHT-20),
                false,
                mass,
                health,
                INITIAL_MOVEMENT_SPEED,
                new SpriteAnimation("src/ImageAssets/enemies/dagron1WalkCycle.png",
                        WIDTH, HEIGHT, 7, GameConstants.ENGINE_FPS, true),
                new SpriteAnimation("src/ImageAssets/enemies/dagron1WalkCycle.png",
                        WIDTH, HEIGHT, 2, GameConstants.ENGINE_FPS, true)
        );

        this.SetAlias("Dagron");
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
        this.DrawWalkAnimation(gc); //Needs to be draw resting animation
        _ai.Stand();
    }

    @Override
    public Object clone()
    {
        return new Dagron(NGetLocation(),GetMass(), GetHealth());
    }
}
