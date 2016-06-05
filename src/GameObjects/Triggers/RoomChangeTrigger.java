package GameObjects.Triggers;

import GameObjectBase.GameWorldObject;
import GameObjectBase.enums.Side;
import GameObjects.Base.GameObject;
import GameObjects.Characters.Player.PlayerObject;
import MainGame.GameManager;
import MainGame.Mapping.StageMap;
import SectorBase.enums.Direction;

import java.awt.*;
import java.lang.reflect.Type;

public class RoomChangeTrigger extends GameObject
{
    //Variables
    private String _roomId;
    private Side _enteringFrom;
    private Direction _mapLinkDirection;

    //Constructor
    public RoomChangeTrigger(Rectangle size, //StageObject changeTo,
                             String roomId,
                             Direction mapLinkDirection,
                             Side enteringFrom)
    {
        super(size, size, true, 0.0F);
        this.SetAlias("Room Change Trigger");

        _roomId = roomId;
        _mapLinkDirection = mapLinkDirection;
        _enteringFrom = enteringFrom;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void OnCollide(GameWorldObject obj)
    {
        if(obj instanceof PlayerObject)
            GameManager.QueueStageTransition(
                    StageMap.Query(_roomId, _mapLinkDirection),
                    _enteringFrom);
    }

    //Private Methods

}
