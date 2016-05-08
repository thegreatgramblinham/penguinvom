package XMLParsing;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

public final class XMLParser
{
    //Private Constants
    private DocumentBuilderFactory _dFactory = DocumentBuilderFactory.newInstance();
    private XPath _xPathParser = XPathFactory.newInstance().newXPath();

    //Constructor
    private XMLParser() {}

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

    public String ParseStringPathContents(Document d, String xPath) throws XPathExpressionException
    {
        NodeList node = EvaluateXPath(d, xPath);
        return node.item(0).getTextContent();
    }

    public int ParseIntPathContents(Document d, String xPath) throws XPathExpressionException
    {
        NodeList node = EvaluateXPath(d, xPath);
        String text = node.item(0).getTextContent();
        return Integer.parseInt(text);
    }

    public double ParseDoublePathContents(Document d, String xPath) throws XPathExpressionException
    {
        NodeList node = EvaluateXPath(d, xPath);
        String text = node.item(0).getTextContent();
        return Double.parseDouble(text);
    }

    //Private Methods
    private NodeList EvaluateXPath(Document d, String xPath) throws XPathExpressionException
    {
        XPathExpression expr = _xPathParser.compile(xPath);
        NodeList node = (NodeList)expr.evaluate(d, XPathConstants.NODESET);
        return node;
    }

}
