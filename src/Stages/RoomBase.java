package Stages;

import GameObjectBase.enums.Side;
import SectorBase.Sector;

import java.awt.*;

public abstract class RoomBase
{
    //Variables
    protected Sector _sector;

    //Constructor
    public RoomBase(Sector sector)
    {
        _sector = sector;
    }

    //Get Methods
    public Sector GetSector()
    {
        return _sector;
    }

    //Set Methods

    //Public Methods
    public abstract Point GetPlayerStartingLocation(Side s);

    //Private Methods
    protected void Init()
    {
        InitBackdrop();
        InitStageBounds();
        InitExits();
    }

    protected abstract void InitBackdrop();

    protected abstract void InitStageBounds();

    protected abstract void InitExits();

}
