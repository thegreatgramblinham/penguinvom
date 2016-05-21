package MainGame.Battle.MoveScripting.Steps;

import GameObjects.Characters.CharacterBase;
import MainGame.Battle.MoveScripting.Steps.enums.CameraAction;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class CharacterMoveStep extends BattleScriptStep
{
    //Private Variables
    private CharacterBase _character;
    private Point _moveTo;

    //Constructor
    public CharacterMoveStep(CharacterBase character, Point moveTo,
                             int framesAlloted)
    {
        super(CameraAction.SlowZoom, framesAlloted);
    }

    //Public Methods
    @Override
    public void Execute(GraphicsContext gc)
    {

    }

    //Protected Methods
    @Override
    protected void InitMove()
    {
        //todo need to calc how fast we need to be moving in order to make
        //the destination in time for our frame limit.
    }
}
