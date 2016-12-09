package Menus.Battle;

import MainGame.GameConstants;
import MainGame.ViewPort;
import Menus.Battle.AttackSubMenu.AttackMenu;
import Menus.Battle.EnemySelection.EnemySelectionCursor;
import Menus.Battle.SelectionCarousel.BattleMenuCarousel;
import Menus.Battle.enums.BattleMenuState;
import Menus.Battle.enums.BattleMenuType;
import Menus.MenuManager;
import Stages.BattleStage;
import javafx.scene.input.KeyCode;

import java.awt.*;

public class BattleMenuManager extends MenuManager
{
    //Private Constants
    private final int KEY_RESET_TIMER = 10;

    //Private Variables
    private BattleMenuCarousel _battleCarousel;
    private AttackMenu _currentAttackMenu;
    private EnemySelectionCursor _currentEnemyCursor;
    private BattleStage _stage;
    private BattleMenuState _state;
    private int _keyCooldownTimer;
    private Point _uiRoot;

    //Constructor
    public BattleMenuManager(BattleStage stage)
    {
        super();
        _uiRoot = stage.GetUIRoot();
        InitCarousel();
        _state = BattleMenuState.CarouselSelection;
        _stage = stage;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void HandleKeyPress()
    {
        if(_keyCooldownTimer < KEY_RESET_TIMER)
        {
            _keyCooldownTimer++;
            return;
        }

        boolean keyPressed = false;

        if(GameConstants.IsKeyPressed(KeyCode.UP))
        {
            switch(_state)
            {
                case CarouselSelection:
                    _battleCarousel.Advance();
                    break;
                case SubMenuSelection:
                    _currentAttackMenu.DecrementSelection();
                    break;
                case EnemySelection:
                    _currentEnemyCursor.IncrementSelection();
                    break;
            }

            keyPressed = true;
        }
        if(GameConstants.IsKeyPressed(KeyCode.DOWN))
        {
            switch(_state)
            {
                case CarouselSelection:
                    break;
                case SubMenuSelection:
                    _currentAttackMenu.IncrementSelection();
                    break;
                case EnemySelection:
                    _currentEnemyCursor.DecrementSelection();
                    break;
            }

            keyPressed = true;
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
                    _currentAttackMenu.SetIsVisible(false);
                    _battleCarousel.SetIsVisible(false);
                    InitEnemyCursor();
                    _state = BattleMenuState.EnemySelection;
                    break;
                case EnemySelection:
                    break;
            }

            keyPressed = true;
        }
        else if(GameConstants.IsKeyPressed(KeyCode.BACK_SPACE))
        {
            switch(_state)
            {
                case CarouselSelection:
                    break;
                case SubMenuSelection:
                    DisposeSubMenu();
                    _state = BattleMenuState.CarouselSelection;
                    break;
                case EnemySelection:
                    _battleCarousel.SetIsVisible(true);
                    _currentAttackMenu.SetIsVisible(true);
                    DisposeEnemyCursor();
                    _state = BattleMenuState.SubMenuSelection;
                    break;
            }

            keyPressed = true;
        }

        if(keyPressed)
            _keyCooldownTimer = 0;
    }

    //Private Methods
    private void InitCarousel()
    {
        _battleCarousel = new BattleMenuCarousel(new Point(_uiRoot.x + 290, _uiRoot.y + 70));
        AddMenu(_battleCarousel);
    }

    private void InitSubMenu(BattleMenuType type)
    {
        _currentAttackMenu = new AttackMenu(new Point(_uiRoot.x + 580, _uiRoot.y + 100));
        AddMenu(_currentAttackMenu);
    }

    private void InitEnemyCursor()
    {
        _currentEnemyCursor = new EnemySelectionCursor(_stage.GetEnemyCharacterLocation());
        AddMenu(_currentEnemyCursor);
    }

    private void DisposeEnemyCursor()
    {
        RemoveMenu(_currentEnemyCursor);
        _currentEnemyCursor = null;
    }

    private void DisposeSubMenu()
    {
        RemoveMenu(_currentAttackMenu);
        _currentAttackMenu = null;
    }

}
