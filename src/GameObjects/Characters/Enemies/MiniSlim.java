package GameObjects.Characters.Enemies;

import Animation.SpriteAnimation;
import GameObjects.Characters.Enemies.AI.SlimAi;
import GameObjects.Characters.Enemies.AI.enums.AiAction;
import MainGame.GameConstants;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class MiniSlim extends EnemyBase
{

    //Variables

    //Constructor
    public MiniSlim(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation("src/ImageAssets/enemies/miniblob.png",
                        32, 32, 10, GameConstants.ENGINE_FPS, true),
                new SpriteAnimation("src/ImageAssets/enemies/miniblob.png",
                        32, 32, 2, GameConstants.ENGINE_FPS, true));
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
