package MainGame.Random;

import java.util.Random;

public class Randomizer extends Random
{
    //Constructor
    public Randomizer()
    {
    }

    //Public Methods
    public int nextInt(int min, int max)
    {
        int r1 = this.nextInt(max - min);
        return min + r1;
    }

    //Private Methods

}
