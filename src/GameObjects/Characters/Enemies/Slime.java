package GameObjects.Characters.Enemies;

import GameObjects.Characters.CharacterBase;

import java.awt.*;

public class Slime extends CharacterBase
{
    //Constructor
    public Slime(Rectangle size, float mass, int health)
    {
        super(size, false, mass, health, null);
    }
}
