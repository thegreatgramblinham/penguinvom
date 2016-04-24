package GameObjects.BattleCharacters;

import GameObjects.Base.GameObject;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class BattleCharacterBase extends GameObject
{
    //Variables

    //Constructor
    public BattleCharacterBase(Rectangle size, Rectangle hitBox)
    {
        super(size, hitBox, false, 1.0F);
    }

    //Get Methods

    //Set Methods

    //Abstract Methods
    public abstract void DrawRestingAnimation(GraphicsContext gc);

    //Public Methods

    //Private Methods

}
