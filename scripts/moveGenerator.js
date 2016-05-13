//Imports
var fs = require('fs');

//Constants
var outputPath = "../src/config/moves/";
var fileExtension = ".xml";

//Global Variables
var xmlFileString = "";

//Main
WriteMove("basicJump", "000001", "Jump", "Jump", 1, "");


//Methods
function WriteMove(fileName, Id, title, moveType, baseDamage,
    animationFile) 
{
    xmlFileString = "";
    
    xmlFileString += "<Move>" + "\n";
    
    xmlFileString += "\t" + "<Id>";
    xmlFileString += Id.toString();
    xmlFileString += "</Id>" + "\n";
    
    xmlFileString += "\t" + "<Title>";
    xmlFileString += title.toString();
    xmlFileString += "</Title>" + "\n";  
    
    xmlFileString += "\t" + "<MoveType>";
    xmlFileString += moveType.toString();
    xmlFileString += "</MoveType>" + "\n"; 
    
    xmlFileString += "\t" + "<BaseDamage>";
    xmlFileString += baseDamage.toString();
    xmlFileString += "</BaseDamage>" + "\n"; 
    
    xmlFileString += "\t" + "<AnimationFile>";
    xmlFileString += animationFile.toString();
    xmlFileString += "</AnimationFile>" + "\n"; 
    
    xmlFileString += "</Move>" + "\n";
    
    var writePath = outputPath + fileName + fileExtension;
    
    fs.writeFileSync(writePath, xmlFileString, "utf8");
    console.log("Completed - <"+ writePath +">");
}

function Completed(err)
{
    if(err)
        throw err
    
    console.log("File write complete.");
}