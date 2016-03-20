package GameObjects.Characters.Enemies.AI;

import GameObjects.Characters.Enemies.AI.enums.AiAction;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Projectiles.Bullet;
import GameObjects.Projectiles.EnemyBullet;
import MainGame.GameManager;
import PhysicsBase.Vectors.VelocityVector;

import java.awt.*;

public class SkilleatinAi extends EnemyAiBase
{
    //Constants
    private final int BULLET_SPEED = 4;

    //Constructor
    public SkilleatinAi(EnemyBase body)
    {
        super(body, 90);
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void Advance()
    {
        super.AdvanceForward();
    }

    @Override
    public void Attack()
    {

        Point center = GetBody().GetCenterPoint();
        //Left
        EnemyBullet b1 = new EnemyBullet(new Point(GetBody().GetLeft() - Bullet.WIDTH - 1 ,
                center.y), GetBody());
        b1.SetVelocity(new VelocityVector(Math.PI, BULLET_SPEED));

        //Right
        EnemyBullet b2 = new EnemyBullet(new Point(GetBody().GetRight() + 1,
                center.y), GetBody());
        b2.SetVelocity(new VelocityVector(0, BULLET_SPEED));

        //Up
        EnemyBullet b3 = new EnemyBullet(new Point(center.x,
                GetBody().GetTop() - Bullet.HEIGHT - 1), GetBody());
        b3.SetVelocity(new VelocityVector(((3*Math.PI)/2), BULLET_SPEED));

        //Down
        EnemyBullet b4 = new EnemyBullet(new Point(center.x,
                GetBody().GetBottom() + 1), GetBody());
        b4.SetVelocity(new VelocityVector((Math.PI/2), BULLET_SPEED));

        GameManager.QueueObjectForAddition(b1, 3);
        GameManager.QueueObjectForAddition(b2, 3);
        GameManager.QueueObjectForAddition(b3, 3);
        GameManager.QueueObjectForAddition(b4, 3);
    }

    @Override
    public void Retreat()
    {

    }

    @Override
    public void Stand()
    {

    }

    //Private Methods
    @Override
    protected AiAction DetermineAction()
    {
        if(_queryCounter == 0)
            return AiAction.Attack;

        return AiAction.Advance;
    }

}
