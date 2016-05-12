package Menus.Text;

import MainGame.ViewPort;
import Menus.Base.MenuBase;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;

public class TextBlock extends MenuBase
{
    //Variables
    private ArrayList<Letter> _text;
    private int _characterSpacing;
    private int _spaceWidth;

    private int _totalCharWidth;
    private int _spaceCount;

    //Constructor
    public TextBlock(int characterSpacing, int spaceWidth)
    {
        super(new Rectangle());
        Init();
        _characterSpacing = characterSpacing;
        _spaceWidth = spaceWidth;
    }

    //Public Methods
    public void AppendLetter(Letter image)
    {
        _text.add(image);
        image.SetRelativeX(InsertionCursor());

        _totalCharWidth += image.GetWidth();
    }

    public void AppendSpace()
    {
        _spaceCount++;
    }

    @Override
    public void Draw(GraphicsContext gc)
    {
        for (Letter letter : _text)
        {
            gc.drawImage(
                    letter.GetSprite(),
                    ViewPort.DrawLocX(this.x + letter.GetXPos()),
                    ViewPort.DrawLocY(this.y));
        }
    }

    //Private Methods
    private void Init()
    {
        _text = new ArrayList<>();
        _totalCharWidth = 0;
        _spaceCount = 0;
    }

    private int InsertionCursor()
    {
        return (_spaceCount * _spaceWidth)
                + (_characterSpacing * _text.size())
                + _totalCharWidth;
    }


}
