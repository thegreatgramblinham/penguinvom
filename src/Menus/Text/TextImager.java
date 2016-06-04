package Menus.Text;

import Logging.LumberJack;
import XMLParsing.XMLParser;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class TextImager
{
    //Public Constants
    public final static int LETTER_HEIGHT = 16;
    public final static int LETTER_WIDTH = 16;

    //Private Constants
    private final static String FONT_FILEPATH = "src/imageAssets/menus/text/pixelFont.png";
    private final static String FONT_COORD_FILEPATH = "src/config/menus/font.xml";

    private final Image _fontImage = new Image(new File(FONT_FILEPATH).toURI().toString());

    //Variables
    private HashMap<Character, CharacterMetaDataNode> _imageCoordinates;

    private int _xStartIndex;
    private int _yStartIndex;

    private int _charSpacing;
    private int _spaceWidth;

    //Constructor
    public TextImager()
    {
        Init();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    public TextBlock TextToImage(String text) //todo wrap?
    {
        TextBlock textBlock = new TextBlock(_charSpacing, _spaceWidth);
        char[] charArray = text.toCharArray();
        PixelReader reader = _fontImage.getPixelReader();

        for (int i = 0; i < charArray.length; i++)
        {
            char c = charArray[i];

            if(c == ' ')
            {
                textBlock.AppendSpace();
                continue; //TODO SPACE HANDLING!!!!
            }

            CharacterMetaDataNode node = _imageCoordinates.get(c);

            WritableImage letterImage = new WritableImage(
                    reader,
                    (TextImager.LETTER_WIDTH * node.x) + _xStartIndex,
                    (TextImager.LETTER_HEIGHT * node.y) + _yStartIndex,
                    node.characterWidth ,
                    LETTER_HEIGHT - _yStartIndex);

            textBlock.AppendLetter(new Letter(letterImage));
        }

        return textBlock;
    }

    //Private Methods
    private void Init()
    {
        _imageCoordinates = new HashMap<>();
        try
        {
            XMLParser parser = new XMLParser(FONT_COORD_FILEPATH);

            //Global information
            NodeList xOff = parser.OpenNodeList("/Font/Global/XOffset");
            NodeList yOff = parser.OpenNodeList("/Font/Global/YOffset");
            NodeList charSpacing = parser.OpenNodeList("/Font/Global/CharacterSpacing");
            NodeList spaceWidth = parser.OpenNodeList("/Font/Global/SpaceWidth");

            _xStartIndex = XMLParser.ParseIntPathContents(xOff);
            _yStartIndex = XMLParser.ParseIntPathContents(yOff);
            _charSpacing = XMLParser.ParseIntPathContents(charSpacing);
            _spaceWidth = XMLParser.ParseIntPathContents(spaceWidth);

            //Character information
            NodeList characterList = parser.OpenNodeList("/Font/Symbol/Character");
            NodeList xList = parser.OpenNodeList("/Font/Symbol/X");
            NodeList yList = parser.OpenNodeList("/Font/Symbol/Y");
            NodeList cWidthList = parser.OpenNodeList("/Font/Symbol/Width");

            for (int i = 0; i < characterList.getLength(); i++)
            {
                String character
                        = XMLParser.ParseStringPathContents(characterList, i);
                int x = XMLParser.ParseIntPathContents(xList, i);
                int y = XMLParser.ParseIntPathContents(yList, i);
                int charWidth = XMLParser.ParseIntPathContents(cWidthList, i);

                _imageCoordinates.put(character.charAt(0),
                        new CharacterMetaDataNode(x, y, charWidth));
            }
        }
        catch(Exception e)
        {
            LumberJack.LogException("Failed to initialize text metadata", e);
        }
    }


}
