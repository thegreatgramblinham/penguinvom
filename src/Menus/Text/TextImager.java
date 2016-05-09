package Menus.Text;

import XMLParsing.XMLParser;
import javafx.scene.image.Image;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class TextImager
{
    //Private Constants
    private final static String FONT_FILEPATH = "src/ImageAssets/menus/text/pixelFont.png";
    private final static String FONT_COORD_FILEPATH = "src/config/menus/font.xml";

    private final static int START_INDEX = 2;
    private final static int LETTER_HEIGHT = 16;

    private final Image _fontImage = new Image(new File(FONT_FILEPATH).toURI().toString());

    //Variables
    private HashMap<Character, Point> _imageCoordinates;

    //Constructor
    public TextImager()
    {
        Init();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    public Image TextToImage(String text) //todo wrap
    {
        return null;
    }

    //Private Methods
    private void Init()
    {
        _imageCoordinates = new HashMap<>();
        try
        {
            XMLParser parser = new XMLParser(FONT_COORD_FILEPATH);
            NodeList characterList = parser.OpenNodeList("/Font/Symbol/Character");
            NodeList xList = parser.OpenNodeList("/Font/Symbol/X");
            NodeList yList = parser.OpenNodeList("/Font/Symbol/Y");


            for (int i = 0; i < characterList.getLength(); i++)
            {
                String character
                        = XMLParser.ParseStringPathContents(characterList, i);
                int x = XMLParser.ParseIntPathContents(xList, i);
                int y = XMLParser.ParseIntPathContents(yList, i);

                _imageCoordinates.put(character.charAt(0), new Point(x, y));
            }
        }
        catch(Exception e)
        {

        }
    }


}
