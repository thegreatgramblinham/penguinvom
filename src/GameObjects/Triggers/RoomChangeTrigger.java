package GameObjects.Triggers;

import GameObjectBase.GameWorldObject;
import GameObjects.Base.GameObject;
import Stages.StageObject;

import java.awt.*;

public class RoomChangeTrigger extends GameObject
{
    //Variables
    private StageObject _changeTo;

    //Constructor
    public RoomChangeTrigger(Rectangle size)
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
