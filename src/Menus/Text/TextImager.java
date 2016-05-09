package Menus.Text;

import XMLParsing.XMLParser;
import javafx.scene.image.Image;
import org.w3c.dom.Document;

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
        Document d = XMLParser.CreateDocument(FONT_COORD_FILEPATH);

        int nodeListLength = XMLParser.GetNodeListLength(d, "/Font/Symbol");

        for(int i = 0; i < nodeListLength; i++)
        {
            String character
                    = XMLParser.ParseStringPathContents(d, i,"/Font/Symbol/Character");
            int x = XMLParser.ParseIntPathContents(d, i, "/Font/Symbol/X");
            int y = XMLParser.ParseIntPathContents(d, i, "/Font/Symbol/Y");

            _imageCoordinates.put(character.charAt(0), new Point(x, y));
        }
    }


}
