package GameObjects.BattleCharacters;

import GameObjects.Base.GameObject;
import Player.Moves.Move;
import Player.Moves.MoveManagers.BatManager;
import Player.Moves.MoveManagers.JumpManager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;

public abstract class BattleCharacterBase extends GameObject
{
    //Variables
    private JumpManager _jumpManager;
    private BatManager _batManager;

    //Constructor
    public BattleCharacterBase(Rectangle size, Rectangle hitBox)
    {
        super(size, hitBox, false, 1.0F);
        Init();
    }

    //Get Methods

    //Set Methods

    //Abstract Methods
    public abstract void DrawRestingAnimation(GraphicsContext gc);

    //Public Methods
    public ArrayList<Move> GetJumpMoves()
    {
        return _jumpManager.GetMoveList();
    }

    public ArrayList<Move> GetBatMoves()
    {
        return _batManager.GetMoveList();
    }

    //Private Methods
    private void Init()
    {
        _jumpManager = new JumpManager();
        _batManager = new BatManager();
    }
}
