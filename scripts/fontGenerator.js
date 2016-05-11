//Imports
var fs = require('fs');

//Constants
var fileName = "font.xml";
var outputPath = "../src/config/menus/";

//Shared Variables
var xmlFileString = "";

//Main
BuildFile();
fs.writeFile(outputPath + fileName, xmlFileString, "utf8", Completed);

//Methods
function BuildFile() 
{
    //Open Font tag
    xmlFileString += "<Font>" + "\n";
    
    WriteGlobalData();
    WriteCapitals();
    WriteLowercase();
    WriteNumericAndSymbols();
        
    //Close Font tag
    xmlFileString += "</Font>";
}

function WriteGlobalData()
{
    //Open Global tag
    xmlFileString += "\t<Global>" + "\n";
    
    xmlFileString += "\t\t" + "<XOffset>"                      
    xmlFileString += 2;              
    xmlFileString += "</XOffset>" + "\n"
    
    xmlFileString += "\t\t" + "<YOffset>"                      
    xmlFileString += 2;              
    xmlFileString += "</YOffset>" + "\n"
    
    xmlFileString += "\t\t" + "<CharacterSpacing>"                      
    xmlFileString += 2;              
    xmlFileString += "</CharacterSpacing>" + "\n"
    
    xmlFileString += "\t\t" + "<SpaceWidth>"                      
    xmlFileString += 8;              
    xmlFileString += "</SpaceWidth>" + "\n"
    
    //Close Global tag
    xmlFileString += "\t</Global>" + "\n";
}

function WriteCapitals() 
{
    var capitalCounter = 65;
    
    //Two rows of 13 each
    for(i = 0; i < 2; i++)
    {
        for(j = 0; j < 13; j++)
        {
            var chr = String.fromCharCode(capitalCounter);
            WriteToFile(chr, j, i);         
            capitalCounter++;
        }
    }    
}

function WriteLowercase() 
{
    var lowercaseCounter = 97;
    
    //Two rows of 13 each
    for(i = 2; i < 4; i++)
    {
        for(j = 0; j < 13; j++)
        {
            var chr = String.fromCharCode(lowercaseCounter);
            WriteToFile(chr, j, i);         
            lowercaseCounter++;
        }
    } 
}

function WriteNumericAndSymbols() 
{
    //Numerics
    for(i = 0; i < 10; i++)
    {
        WriteToFile(i, i, 4);
    }
    
    //Symbols
    WriteToFile('!', 10, 4);
    WriteToFile('?', 11, 4);
    WriteToFile('#', 12, 4);
}

function WriteToFile(character, x, y)
{
    //Open Symbol
    xmlFileString += "\t" + "<Symbol>"+ "\n";
    
    //Character
    xmlFileString += "\t\t" + "<Character>"                   
    xmlFileString += character;    
    xmlFileString += "</Character>" + "\n"

    //X
    xmlFileString += "\t\t" + "<X>"                      
    xmlFileString += x;              
    xmlFileString += "</X>" + "\n"
    
    //Y
    xmlFileString += "\t\t" + "<Y>"                      
    xmlFileString += y;              
    xmlFileString += "</Y>" + "\n"
    
    //Width
    xmlFileString += "\t\t" + "<Width>"                      
    xmlFileString += GetCharWidth(character);              
    xmlFileString += "</Width>" + "\n"
    
    //Close Symbol
    xmlFileString += "\t" + "</Symbol>"+ "\n";
}

function GetCharWidth(character) 
{  
    var c = character.toString().charCodeAt(0);
    
    if(c >= 65 && c <= 90)
    {
        switch(c)
        {
            case 67: //C
            case 69: //E
            case 70: //F
                return 8;
            case 73: //I
                return 2;
            default:
                return 10;          
        }
    }
    else if(c >= 97 && c <= 122)   
    {
        switch(c)
        {
            case 99: //c
                return 6;
            case 105: //i
            case 106: //j
            case 108: //l
                return 2;
            case 109: //m
            case 118: //v
            case 119: //w
            case 120: //x
            case 122: //z
                return 10;
            default:
                return 8;          
        }
    }
    else if(c >= 48 && c <= 57)   
    {
        switch(c)
        {
            case 49: //1
                return 4;
            default:
                return 10;
        }
    }
    
    //Misc Symbols
    switch(c)
    {
        case 33: //!
            return 2;
        case 63: //?
            return 8;
    }
    
    return -1;
}

function Completed(err)
{
    if(err)   
        throw err;
      
    console.log("File write complete.");
}