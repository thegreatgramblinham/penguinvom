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
    
    WriteCapitals();
    WriteLowercase();
    WriteNumericAndSymbols();
        
    //Close Font tag
    xmlFileString += "</Font>";
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
    //Open Letter
    xmlFileString += "\t" + "<Letter>"+ "\n";
    
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
    
    //Close Letter
    xmlFileString += "\t" + "</Letter>"+ "\n";
}

function Completed(err)
{
    if(err)   
        throw err;
      
    console.log("File write complete.");
}