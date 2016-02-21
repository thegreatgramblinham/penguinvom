package GameObjects.Characters.Player;

import Animation.SpriteAnimation;
import GameObjectBase.GameWorldObject;
import GameObjects.Characters.CharacterBase;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class PlayerObject extends CharacterBase
{

    public PlayerObject(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health,
                new SpriteAnimation(
                        "src/ImageAssets/player/shuf64png.png", 64, 64, 5, 60, true));
    }

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
}
