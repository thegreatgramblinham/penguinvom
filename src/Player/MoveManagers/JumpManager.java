package Player.MoveManagers;

import Player.Moves.Move;
import Player.Moves.MoveBuilder;

import java.util.ArrayList;

public class JumpManager extends MoveManager
{
    //Variables

    //Constructor

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public ArrayList<Move> GetMoveList() throws Exception
    {
        ArrayList<Move> moveList = new ArrayList<>();

        Move m1 = MoveBuilder.BuildMove("src/config/moves/BasicJump.xml");
        moveList.add(m1);

        return moveList;
    }

    //Private Methods

}
