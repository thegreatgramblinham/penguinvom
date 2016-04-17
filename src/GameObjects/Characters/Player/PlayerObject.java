package GameObjects.Characters.Player;

import Animation.SpriteAnimation;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;
import GameObjects.Characters.Enemies.EnemyBase;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class PlayerObject extends CharacterBase
{
    //Private Constants
    private final static int WIDTH = 64;
    private final static int HEIGHT = 64;

    //Constructor
    public PlayerObject(Point location, float mass, int health)
    {
        super(
                new Rectangle(location.x, location.y, WIDTH, HEIGHT),
                new Rectangle(location.x+10, location.y+10, WIDTH-20, HEIGHT-20),
                false,
                mass,
                health,
                new SpriteAnimation("src/ImageAssets/player/shuf0000.png", 64, 64, 5, 60, true),
                null
        );

        this.SetAlias("PlayerObject");
    }

    //Public Methods
    @Override
    public void DrawWalkAnimation(GraphicsContext gc)
    {
        if (this.GetVelocity() != null
                && this.GetVelocity().GetSpeed() != 0.0)
        {
            if(this.GetVelocity().GetSpeed() < 0.2)
                _walkCycle.SetAnimationFps(2);
            else if(this.GetVelocity().GetSpeed() < 1.0)
                _walkCycle.SetAnimationFps(5);
            else
                _walkCycle.SetAnimationFps(10);
        }

        super.DrawWalkAnimation(gc);
    }

    @Override
    public void OnCollide(GameWorldObject other)
    {
        if(other instanceof EnemyBase)
            this.SetHealth(GetHealth() -
                    ((EnemyBase)other).GetTouchDamage());
    }

    @Override
    public void OnRemoval()
    {
        System.out.println("Game over man!");
    }
}
