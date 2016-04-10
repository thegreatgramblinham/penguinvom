package GameObjects.Triggers;

import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;

import java.awt.*;

public class SectorChangeTrigger extends GameObject
{
    //Variables

    //Constructor
    public SectorChangeTrigger(Rectangle size)
    {
        super(size, true, 0.0F);
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void OnCollide(GameWorldObject obj)
    {
        //Change the current engine sector.
    }

    //Private Methods

}
