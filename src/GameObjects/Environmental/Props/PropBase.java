package GameObjects.Environmental.Props;

import Animation.SpriteAnimation;
import Animation.enums.AnimationOrientation;
import GameObjects.Base.GameObject;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class PropBase extends GameObject
{
    //Variables
    private SpriteAnimation _restingAnimation;

    //Constructor
    public PropBase(Rectangle size, boolean isImmobile, float mass,
                    SpriteAnimation restingAnimation)
    {
        super(size, isImmobile, mass);

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