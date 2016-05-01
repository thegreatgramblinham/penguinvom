package Menus.Battle.AttackSubMenu;

import Menus.Base.MenuBase;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;

public class AttackMenu extends MenuBase
{
    //Private Constants
    private final static int ITEM_PADDING = 4;

    //Variables
    private ArrayList<AttackMenuItem> _items;

    //Constructor
    public AttackMenu(Point location)
    {
        super(new Rectangle(location.x, location.y, 0, 0));
        Init();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void Draw(GraphicsContext gc)
    {
        for(AttackMenuItem item : _items)
        {
            item.Draw(gc);
        }
    }

    //Private Methods
    private void Init()
    {
        _items = new ArrayList<>();

        for(int i = 0; i < 5; i++)
        {
            _items.add(new AttackMenuItem(new Point(this.x,
                    this.y + i * (AttackMenuItem.HEIGHT + ITEM_PADDING))));
        }
    }
}
