package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.SlimAi;
import MainGame.GameConstants;
import MainGame.GameManager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Slim extends EnemyBase
{
    //Constructor
    public Slim(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png", 64, 64, 10, 60, true),
                new SpriteAnimation("src/ImageAssets/enemies/blobSheet.png", 64, 64, 2, 60, true));
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
        return new Slim(GetBounds(),GetMass(), GetHealth());
    }

    @Override
    public void OnDeath()
    {
        MiniSlim s1 = new MiniSlim(
                new Rectangle(x, y + (int)this.GetHalfHeight(), 32, 32), 0.5F, 5);
        MiniSlim s2 = new MiniSlim(
                new Rectangle(x + (int)this.GetHalfWidth() + 1,
                        y + (int)this.GetHalfHeight() + 10, 32, 32), 0.5F, 5);

        GameManager.QueueObjectForAddition(s1, GameConstants.ENEMY_RENDER_GROUP,
                GameConstants.ENEMY_GROUP);
        GameManager.QueueObjectForAddition(s2, GameConstants.ENEMY_RENDER_GROUP,
                GameConstants.ENEMY_GROUP);

        super.OnDeath();
    }
}
