package GameObjects.Triggers;

import GameObjectBase.GameWorldObject;
import GameObjectBase.enums.Side;
import GameObjects.Base.GameObject;
import GameObjects.Characters.Player.PlayerObject;
import MainGame.GameManager;
import Stages.StageObject;

import java.awt.*;

public class RoomChangeTrigger extends GameObject
{
    //Variables
    private StageObject _changeTo;
    private Side _enteringFrom;

    //Constructor
    public RoomChangeTrigger(Rectangle size, StageObject changeTo,
                             Side enteringFrom)
    {
        super(size, size, true, 0.0F);
        _changeTo = changeTo;
        _enteringFrom = enteringFrom;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void OnCollide(GameWorldObject obj)
    {
        if(obj instanceof PlayerObject)
            GameManager.QueueSectorTransition(_changeTo, _enteringFrom);
    }

    //Private Methods

}
