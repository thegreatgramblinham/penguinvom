package GameObjects.Environmental.Props;

import Animation.SpriteAnimation;
import Animation.enums.AnimationOrientation;
import GameObjects.Base.GameObject;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class PropBase extends GameObject
{
    //Variables
    private SpriteAnimation _restingAnimation;

    //Constructor
    public PropBase(Rectangle size, Rectangle hitbox, boolean isImmobile, float mass,
                    SpriteAnimation restingAnimation)
    {
        super(size, hitbox, isImmobile, mass);

        _restingAnimation = restingAnimation;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    public void DrawAnimation(GraphicsContext gc)
    {
        _restingAnimation.DrawSpriteFrame(gc,
                this.GetGameDrawPoint(), AnimationOrientation.Default);
    }

    //Private Methods

}
