package Menus.Battle.EnemySelection;

import Menus.Base.MenuBase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class EnemySelectionCursor extends MenuBase
{
    //Private Constants
    private final static int WIDTH = 34;
    private final static int HEIGHT = 34;

    private final static int VERTICAL_RENDER_PADDING = HEIGHT + 4;

    private final static Image SPRITE //this can be replaced by an animation if need be.
            = new Image(new File("src/ImageAssets/menus/battle/enemySelectionArrow.png")
                .toURI().toString());

    //Private Variables
    private Point[] _positions;
    private int _selectionIndex = 0;

    //Constructor
    public EnemySelectionCursor(Point[] positions)
    {
        super(new Rectangle(positions[0].x, positions[0].y, WIDTH, HEIGHT));
        _positions = positions;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void Draw(GraphicsContext gc)
    {
        Point drawPoint = this.GetGameDrawPoint();
        gc.drawImage(SPRITE, drawPoint.x, drawPoint.y - VERTICAL_RENDER_PADDING);
    }

    public void IncrementSelection()
    {
        if(_selectionIndex < _positions.length - 1)
            _selectionIndex++;
        else
            _selectionIndex = 0;

        this.NSetLocation(_positions[_selectionIndex]);
    }

    public void DecrementSelection()
    {
        if (_selectionIndex > 0)
            _selectionIndex--;
        else
            _selectionIndex = _positions.length - 1;

        this.NSetLocation(_positions[_selectionIndex]);
    }

    public int ReturnSelection()
    {
        return _selectionIndex;
    }

}
