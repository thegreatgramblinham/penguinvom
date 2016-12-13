package MainGame.Animation.Steps;

import GameObjects.Characters.CharacterBase;
import GeneralHelpers.PointHelper;
import MainGame.Animation.Events.AbilityAnimationExecutionEvent;
import PhysicsBase.Vectors.VelocityVector;
import javafx.scene.canvas.GraphicsContext;
import MainGame.Animation.enums.CameraAction;

import java.awt.*;

public class CharacterMoveStep extends ScriptStep<AbilityAnimationExecutionEvent>
{
    //Private Variables
    private CharacterBase _character;
    private Point _moveTo;
    private VelocityVector _velocityToMaintain;

    //Constructor
    public CharacterMoveStep(CharacterBase character, Point moveTo,
                             int framesAlloted)
    {
        super(CameraAction.SlowZoom, framesAlloted);
        _character = character;
        _moveTo = moveTo;
        InitMove();
    }

    //Public Methods
    @Override
    public void Execute(AbilityAnimationExecutionEvent event, GraphicsContext gc)
    {
        //todo make sure the character maintains their calculated velocity until
        //the move is complete.
        //todo adjust character animation fps as necessary.
    }

    //Protected Methods
    @Override
    protected void InitMove()
    {
        //todo need to calc how fast we need to be moving in order to make
        //the destination in time for our frame limit. (calc veloToMaintain)
        Point characterPoint = _character.NGetLocation();
        double distToTravel = PointHelper.DistanceTo(characterPoint, _moveTo);

        double distToCoverPerFrame = distToTravel/_framesAlloted;

        double vertDist = PointHelper.SlopeRiseOf(characterPoint, _moveTo);
        double horiDist = PointHelper.SlopeRunOf(characterPoint, _moveTo);

        //From here we can build a vector
    }
}
