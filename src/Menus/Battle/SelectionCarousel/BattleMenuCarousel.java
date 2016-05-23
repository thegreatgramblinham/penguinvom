package Menus.Battle.SelectionCarousel;

import Menus.Base.MenuBase;
import Menus.Battle.enums.BattleMenuType;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.*;

public class BattleMenuCarousel extends MenuBase
{
    //Variables
    private ArrayList<BattleMenuBase> _menuList;
    private int _selectedIndex;
    private LinkedList<Integer> _animationQueue;

    //Constructor
    public BattleMenuCarousel(Point location)
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
        if(!GetIsVisible()) return;

        BattleMenuBase card = _menuList.get(_selectedIndex);
        boolean needToDrawSelected = true;

        if(_animationQueue.size() > 0)
        {
            card.DrawTransitionAnimation(gc);
            needToDrawSelected = false;

            if(card.TransitionComplete())
            {
                _selectedIndex = _animationQueue.pop();
                needToDrawSelected = true;
            }
        }

        if(needToDrawSelected)
        {
            card = _menuList.get(_selectedIndex);
            card.Draw(gc);
        }

    }

    public void Advance()
    {
        int listIndex;
        if(_selectedIndex < _menuList.size() - 1)
            listIndex = _selectedIndex + 1;
        else
            listIndex = 0;

        _menuList.get(listIndex)._animation.ResetAnimation();
        _animationQueue.add(listIndex);
    }

    public BattleMenuType GetSelection()
    {
        return _menuList.get(_selectedIndex).GetType();
    }

    //Private Methods
    private void Init()
    {
        _menuList = new ArrayList<>();
        _animationQueue = new LinkedList<>();
        _selectedIndex = 0;

        _menuList.add(new BatMenu(new Point(this.x, this.y)));
    }
}
