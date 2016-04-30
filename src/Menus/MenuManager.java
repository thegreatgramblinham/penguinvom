package Menus;

import Menus.Base.MenuBase;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashSet;

public class MenuManager
{
    //Variables
    private HashSet<MenuBase> _menus;

    //Constructor
    public MenuManager()
    {
        _menus = new HashSet<>();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    public void AddMenu(MenuBase menu)
    {
        _menus.add(menu);
    }

    public void DrawMenus(GraphicsContext gc)
    {
        for(MenuBase menu : _menus)
        {
            menu.DrawSelectedAnimation(gc);
        }
    }

    //Private Methods

}
