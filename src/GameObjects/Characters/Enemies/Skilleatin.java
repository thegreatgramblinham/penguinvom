package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.SkilleatinAi;
import MainGame.GameConstants;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Skilleatin extends EnemyBase
{
    //Private Constants
    private final static int WIDTH = 64;
    private final static int HEIGHT = 64;
    private final static float INITIAL_MOVEMENT_SPEED = 0.5F;

    //Constructor
    public Skilleatin(Point location, float mass, int health)
    {
        super(
                new Rectangle(location.x, location.y, WIDTH, HEIGHT),
                new Rectangle(location.x, location.y, WIDTH-20, HEIGHT-20),
                false,
                mass,
                health,
                INITIAL_MOVEMENT_SPEED,
                new SpriteAnimation("src/imageAssets/enemies/skilleatin1WalkCycle.png",
                        WIDTH, HEIGHT, 5, GameConstants.ENGINE_FPS, true),
                new SpriteAnimation("src/imageAssets/enemies/skilleatin1WalkCycle.png",
                        WIDTH, HEIGHT, 1, GameConstants.ENGINE_FPS, true)
        );

        this.SetAlias("Skilleatin");
        this.SetAi(new SkilleatinAi(this));
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
        return new Skilleatin(NGetLocation(),GetMass(), GetHealth());
    }

    //Private Methods

}
