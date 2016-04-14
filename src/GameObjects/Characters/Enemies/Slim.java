package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.SlimAi;
import MainGame.GameConstants;
import MainGame.GameManager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Slim extends EnemyBase
{
    //Private Constants
    private final static int WIDTH = 64;
    private final static int HEIGHT = 64;

    //Constructor
    public Slim(Point location, float mass, int health)
    {
        super(
                new Rectangle(location.x, location.y, WIDTH, HEIGHT),
                new Rectangle(location.x, location.y, WIDTH-20, HEIGHT-20),
                false,
                mass,
                health,
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png",
                        WIDTH, HEIGHT, 10, GameConstants.ENGINE_FPS, true),
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png",
                        WIDTH, HEIGHT, 2, GameConstants.ENGINE_FPS, true)
        );

        this.SetAlias("BigSlim");
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

    @Override
    public Object clone()
    {
        return new Slim(NGetLocation(),GetMass(), GetHealth());
    }

    @Override
    public void OnDeath()
    {
        MiniSlim s1 = new MiniSlim(new Point(x, y + (int)this.GetHalfHeight()), 0.5F, 5);
        MiniSlim s2 = new MiniSlim(
                new Point(x + (int)this.GetHalfWidth() + 1, y + (int)this.GetHalfHeight() + 10), 0.5F, 5);

        GameManager.QueueObjectForAddition(s1, GameConstants.ENEMY_RENDER_GROUP,
                GameConstants.ENEMY_GROUP);
        GameManager.QueueObjectForAddition(s2, GameConstants.ENEMY_RENDER_GROUP,
                GameConstants.ENEMY_GROUP);

        super.OnDeath();
    }
}
