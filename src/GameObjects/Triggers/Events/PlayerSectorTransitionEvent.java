package GameObjects.Triggers.Events;

import GameObjectBase.enums.Side;
import Stages.OverworldStage;

public class PlayerSectorTransitionEvent
{
    //Private Variables
    private OverworldStage _changeTo;
    private Side _enteringFrom;

    //Constructor
    public PlayerSectorTransitionEvent(OverworldStage changeTo, Side enteringFrom)
    {
        _changeTo = changeTo;
        _enteringFrom = enteringFrom;
    }

    //Get Methods
    public OverworldStage GetChangeTo()
    {
        return _changeTo;
    }

    public Side GetEnteringFrom()
    {
        return _enteringFrom;
    }
}
