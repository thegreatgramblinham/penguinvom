package Player.Moves.MoveManagers;

import Player.Moves.Move;
import Player.Moves.MoveBuilder;
import Player.Moves.MoveConstants;

import java.util.ArrayList;

public class JumpManager extends MoveManager
{
    //Variables

    //Constructor

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public ArrayList<Move> GetMoveList()
    {
        try
        {
            ArrayList<Move> moveList = new ArrayList<>();

            Move m1 = MoveBuilder.BuildMove(MoveConstants.BASIC_JUMP);
            moveList.add(m1);

            return moveList;
        }
        catch(Exception e)
        {
            System.out.println("<FAILING TO LOAD MOVES> - "+e.getMessage());

        }

        return null;
    }

    //Private Methods

}
