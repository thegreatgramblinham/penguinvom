package Stages;

import GameObjectBase.enums.Side;
import SectorBase.Sector;

import java.awt.*;

public class CastleGarden extends RoomBase
{
    //Variables

    //Constructor
    public CastleGarden(Sector sector)
    {
        super(sector);
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public Point GetPlayerStartingLocation(Side s)
    {
        return null;
    }

    //Private Methods
    @Override
    protected void InitBackdrop()
    {

    }

    @Override
    protected void InitStageBounds()
    {

    }

    @Override
    protected void InitExits()
    {

    }

}
