package CharacterFunctions.Abilities.AbilityManagers;

import CharacterFunctions.Abilities.Ability;
import CharacterFunctions.Abilities.AbilityBuilder;
import CharacterFunctions.Abilities.AbilityConstants;

import java.util.ArrayList;

public class JumpManager extends AbilityManager
{
    //Variables

    //Constructor

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public ArrayList<Ability> GetAbilityList()
    {
        try
        {
            ArrayList<Ability> abliList = new ArrayList<>();

            Ability m1 = AbilityBuilder.BuildAbility(AbilityConstants.BASIC_JUMP);
            abliList.add(m1);

            return abliList;
        }
        catch(Exception e)
        {
            System.out.println("<FAILING TO LOAD MOVES> - "+e.getMessage());
        }

        return null;
    }

    //Private Methods

}
