package Menus.Battle;

import Menus.Base.MenuBase;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.*;

public class BattleMenuCarousel extends MenuBase
{
    //Private Constants
    private final static int WIDTH = 179;
    private final static int HEIGHT = 177;

    //Variables
    private ArrayList<BattleMenuBase> _menuList;
    private int _selectedIndex;
    private LinkedList<Integer> _animationQueue;


    //Constructor
    public BattleMenuCarousel(Point location)
    {
        super(new Rectangle(location.x, location.y, WIDTH, HEIGHT));
        Init();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void DrawSelectedAnimation(GraphicsContext gc)
    {
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
            card.DrawSelectedAnimation(gc);
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

    //Private Methods
    private void Init()
    {
        _menuList = new ArrayList<>();
        _animationQueue = new LinkedList<>();
        _selectedIndex = 0;

        _menuList.add(new BatMenu(new Point(this.x, this.y)));
    }
}
