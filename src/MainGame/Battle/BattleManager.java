package MainGame.Battle;

import MainGame.Battle.enums.Turn;
import MainGame.ViewPort;
import Menus.Battle.BatMenu;
import Menus.MenuManager;
import Stages.Battle.BattleStage;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class BattleManager
{
    //Variables
    private BattleStage _stage;
    private Turn _turn;
    private MenuManager _menuManager;


    //Constructor
    public BattleManager(BattleStage stage)
    {
        _stage = stage;
        InitMenus();
    }

    //Get Methods
    public BattleStage GetStage()
    {
        return _stage;
    }

    //Set Methods

    //Public Methods
    public void DrawMenus(GraphicsContext gc)
    {
        _menuManager.DrawMenus(gc);
    }

    //Private Methods
    private void InitMenus()
    {
        _menuManager = new MenuManager();
        _menuManager.AddMenu(new BatMenu( new Point(ViewPort.SecLocX(390), ViewPort.SecLocY(120))));
    }

}
