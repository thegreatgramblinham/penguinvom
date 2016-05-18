package Menus.Battle.AttackSubMenu;

import Menus.Base.MenuBase;
import Menus.Text.TextImager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;

public class AttackMenu extends MenuBase
{
    //Private Constants
    private final static int ITEM_PADDING = 4;

    //Variables
    private ArrayList<AttackMenuItem> _items;
    private AttackMenuItemSelection _selectionGraphic;
    private int _selectedIndex;
    private TextImager _textImager = new TextImager();

    //Constructor
    public AttackMenu(Point location)
    {
        super(new Rectangle(location.x, location.y, 0, 0));
        InitItems();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void Draw(GraphicsContext gc)
    {
        if(!GetIsVisible()) return;

        for(AttackMenuItem item : _items)
        {
            item.Draw(gc);
        }

        _selectionGraphic.Draw(gc);
    }

    public void IncrementSelection()
    {
        if(_selectedIndex < _items.size() - 1)
            _selectedIndex++;
        else
            _selectedIndex = 0;

        InitSelection();
    }

    public void DecrementSelection()
    {
        if (_selectedIndex > 0)
            _selectedIndex--;
        else
            _selectedIndex = _items.size() - 1;

        InitSelection();
    }

    //Private Methods
    private void InitItems()
    {
        _items = new ArrayList<>();
        _selectedIndex = 0;

        for(int i = 0; i < 5; i++)
        {
            _items.add(new AttackMenuItem(
                    new Point(this.x, this.y + i * (AttackMenuItem.HEIGHT + ITEM_PADDING)),
                    _textImager.TextToImage("This is a test sentence!")));
        }

        InitSelection();
    }

    private void InitSelection()
    {
        _selectionGraphic = new AttackMenuItemSelection
                (
                        new Point(
                                this.x - ITEM_PADDING,
                                this.y + (_selectedIndex *
                                        (AttackMenuItemSelection.HEIGHT
                                                - AttackMenuItemSelection.BORDER_PADDING)
                                        - ITEM_PADDING)
                        )

                );
    }
}
