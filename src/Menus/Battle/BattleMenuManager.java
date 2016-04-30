package Menus.Battle;

import MainGame.GameConstants;
import MainGame.ViewPort;
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

    }

    //Private Methods
    private void Init()
    {
        _battleCarousel = new BattleMenuCarousel(
                new Point(
                        ViewPort.SecLocX(390),
                        ViewPort.SecLocY(120)));
        AddMenu(_battleCarousel);
    }

}
