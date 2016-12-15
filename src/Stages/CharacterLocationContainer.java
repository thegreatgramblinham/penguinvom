package Stages;

import GameObjects.BattleCharacters.BattleCharacterBase;

import java.awt.*;

/**
 * Container for initialization of character position defaults.
 */
public class CharacterLocationContainer
{
    //Private Variables
    private BattleCharacterBase _character;
    private Point _location;

    //Constructor
    public CharacterLocationContainer(BattleCharacterBase character, Point location)
    {
        _character = character;
        _location = location;
    }

    //Get Methods
    public BattleCharacterBase GetCharacter()
    {
        return _character;
    }

    public Point GetLocation()
    {
        return _location;
    }
}
