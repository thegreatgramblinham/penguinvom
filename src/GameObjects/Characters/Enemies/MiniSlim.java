package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.SlimAi;
import GameObjects.Characters.Enemies.AI.enums.AiAction;
import MainGame.GameConstants;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class MiniSlim extends EnemyBase
{
    //Private Constants
    private final static int WIDTH = 32;
    private final static int HEIGHT = 32;
    private final static float INITIAL_MOVEMENT_SPEED = 0.5F;

    //Constructor
    public MiniSlim(Point location, float mass, int health)
    {
        super(
                new Rectangle(location.x, location.y, WIDTH, HEIGHT),
                new Rectangle(location.x, location.y, WIDTH-20, HEIGHT-20),
                false,
                mass,
                health,
                INITIAL_MOVEMENT_SPEED,
                new SpriteAnimation("src/ImageAssets/enemies/miniSlim1WalkCycle.png",
                        WIDTH, HEIGHT, 10, GameConstants.ENGINE_FPS, true),
                new SpriteAnimation("src/ImageAssets/enemies/miniSlim1WalkCycle.png",
                        WIDTH, HEIGHT, 2, GameConstants.ENGINE_FPS, true)
        );

        this.SetAlias("MiniSlim");
        this.SetAi(new SlimAi(this));
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

    //Private Methods

}
