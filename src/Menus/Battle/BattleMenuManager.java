package Menus.Battle;

import MainGame.GameConstants;
import MainGame.ViewPort;
import Menus.Battle.SelectionCarousel.BattleMenuCarousel;
import Menus.Battle.enums.BattleMenuType;
import Menus.MenuManager;
import javafx.scene.input.KeyCode;

import java.awt.*;

public class BattleMenuManager extends MenuManager
{
    //Variables
    private BattleMenuCarousel _battleCarousel;

    //Constructor
    public BattleMenuManager()
    {
        super();
        Init();
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
        }

    }

    //Private Methods
    private void Init()
    {
        _battleCarousel = new BattleMenuCarousel(
                new Point(
                        ViewPort.SecLocX(390),
                        ViewPort.SecLocY(70)));
        AddMenu(_battleCarousel);
    }

}
