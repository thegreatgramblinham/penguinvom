package Menus.Battle;

import MainGame.GameConstants;
import MainGame.ViewPort;
import Menus.Battle.AttackSubMenu.AttackMenu;
import Menus.Battle.SelectionCarousel.BattleMenuCarousel;
import Menus.Battle.enums.BattleMenuType;
import Menus.MenuManager;
import javafx.scene.input.KeyCode;

import java.awt.*;

public class BattleMenuManager extends MenuManager
{
    //Variables
    private BattleMenuCarousel _battleCarousel;
    private AttackMenu _currentAttackMenu;

    //Constructor
    public BattleMenuManager()
    {
        super();
        InitCarousel();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void HandleKeyPress()
    {
        if(GameConstants.IsKeyPressed(KeyCode.UP))
        {
            _battleCarousel.Advance();
        }
        else if(GameConstants.IsKeyPressed(KeyCode.ENTER))
        {
            BattleMenuType battleSelection = _battleCarousel.GetSelection();
            //todo open the selection sub menu
            InitSubMenu(battleSelection);
        }

    }

    //Private Methods
    private void InitCarousel()
    {
        _battleCarousel = new BattleMenuCarousel(
                new Point(
                        ViewPort.SecLocX(390),
                        ViewPort.SecLocY(70)));
        AddMenu(_battleCarousel);
    }

    private void InitSubMenu(BattleMenuType type)
    {
        _currentAttackMenu = new AttackMenu(
                new Point(
                        ViewPort.SecLocX(680),
                        ViewPort.SecLocY(100)));
        AddMenu(_currentAttackMenu);
    }

}
