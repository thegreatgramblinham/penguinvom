package Stages;

import XMLParsing.XMLParser;

public final class StageBuilder
{

    //Variables

    //Constructor
    private StageBuilder(){}

    //Public Methods
    public static void BuildStage(String filePath) throws Exception
    {
        XMLParser parser = new XMLParser(filePath);

        String id = XMLParser.ParseStringPathContents(
                parser.OpenNodeList(StageConstants.S_ID), 0);

    }

    //Private Methods

}
