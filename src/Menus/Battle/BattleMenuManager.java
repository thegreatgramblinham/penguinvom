package Menus.Battle;

import MainGame.GameConstants;
import MainGame.ViewPort;
import Menus.Battle.AttackSubMenu.AttackMenu;
import Menus.Battle.SelectionCarousel.BattleMenuCarousel;
import Menus.Battle.enums.BattleMenuState;
import Menus.Battle.enums.BattleMenuType;
import Menus.MenuManager;
import javafx.scene.input.KeyCode;

import java.awt.*;

public class BattleMenuManager extends MenuManager
{
    //Variables
    private BattleMenuCarousel _battleCarousel;
    private AttackMenu _currentAttackMenu;
    private BattleMenuState _state;

    //Constructor
    public BattleMenuManager()
    {
        super();
        InitCarousel();
        _state = BattleMenuState.CarouselSelection;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void HandleKeyPress()
    {
        if(GameConstants.IsKeyPressed(KeyCode.UP))
        {
            switch(_state)
            {
                case CarouselSelection:
                    _battleCarousel.Advance();
                    break;
                case SubMenuSelection:
                    break;
                case EnemySelection:
                    break;
            }

        }
        else if(GameConstants.IsKeyPressed(KeyCode.ENTER))
        {
            switch(_state)
            {
                case CarouselSelection:
                    BattleMenuType battleSelection = _battleCarousel.GetSelection();
                    InitSubMenu(battleSelection);
                    _state = BattleMenuState.SubMenuSelection;
                    break;
                case SubMenuSelection:
                    break;
                case EnemySelection:
                    break;
            }
        }
        else if(GameConstants.IsKeyPressed(KeyCode.BACK_SPACE))
        {
            switch(_state)
            {
                case CarouselSelection:
                    break;
                case SubMenuSelection:
                    RemoveMenu(_currentAttackMenu);
                    _currentAttackMenu = null;
                    _state = BattleMenuState.CarouselSelection;
                    break;
                case EnemySelection:
                    break;
            }
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
