package GameObjects.BattleCharacters;

import GameObjects.Base.GameObject;
import CharacterFunctions.Abilities.Ability;
import CharacterFunctions.Abilities.AbilityManagers.BatManager;
import CharacterFunctions.Abilities.AbilityManagers.JumpManager;
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
    public ArrayList<Ability> GetJumpMoves()
    {
        return _jumpManager.GetAbilityList();
    }

    public ArrayList<Ability> GetBatMoves()
    {
        return _batManager.GetAbilityList();
    }

    //Private Methods
    private void Init()
    {
        _jumpManager = new JumpManager();
        _batManager = new BatManager();
    }
}
