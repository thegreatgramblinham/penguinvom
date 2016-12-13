package MainGame.Animation.Steps;

import GameObjects.Characters.CharacterBase;
import GeneralHelpers.ConversionHelper;
import GeneralHelpers.PointHelper;
import MainGame.Animation.Events.AbilityAnimationExecutionEvent;
import PhysicsBase.Vectors.DistanceVector;
import PhysicsBase.Vectors.VelocityVector;
import javafx.scene.canvas.GraphicsContext;
import MainGame.Animation.enums.CameraAction;

import java.awt.*;

public class CharacterMoveStep extends ScriptStep<AbilityAnimationExecutionEvent>
{
    //Private Variables
    private VelocityVector _velocityToMaintain;

    //Constructor
    public CharacterMoveStep(int framesAlloted)
    {
        super(CameraAction.SlowZoom, framesAlloted);
    }

    //Public Methods
    @Override
    public void Execute(AbilityAnimationExecutionEvent event, GraphicsContext gc)
    {
        //todo make sure the character maintains their calculated velocity until
        //the move is complete.
        //todo adjust character animation fps as necessary.

        CalculateMoveDistance(event.GetUser(), event.GetTargets().get(0).getLocation());
    }

    //Private Methods
    private DistanceVector CalculateMoveDistance(CharacterBase character, Point moveTo)
    {
        Point characterPoint = character.NGetLocation();
        double distToTravel = PointHelper.DistanceTo(characterPoint, moveTo);

        double distToCoverPerFrame = distToTravel/_framesAlloted; //todo we need to break this down into frame movement.

        double slope = PointHelper.SlopeOf(characterPoint, moveTo);

        //From here we can build a vector
        return new DistanceVector(ConversionHelper.SlopeToRadians(slope), distToTravel);
    }
}
