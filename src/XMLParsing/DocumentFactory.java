package XMLParsing;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public final class DocumentFactory
{
    //Private Constants
    private DocumentBuilderFactory _dFactory = DocumentBuilderFactory.newInstance();

    //Constructor
    private DocumentFactory() {}

    //Get Methods

    //Set Methods

    //Public Methods
    public Document CreateDocument(String filePath) throws ParserConfigurationException, IOException, SAXException
    {
        File textFile = new File(filePath);
        DocumentBuilder dBuilder = _dFactory.newDocumentBuilder();
        Document d = dBuilder.parse(textFile);
        d.getDocumentElement().normalize();

        return d;
    }

    //Private Methods

}
