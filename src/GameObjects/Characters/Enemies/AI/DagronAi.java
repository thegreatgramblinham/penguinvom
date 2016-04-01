package GameObjects.Characters.Enemies.AI;

import GameObjects.Characters.Enemies.AI.enums.AiAction;
import GameObjects.Characters.Enemies.EnemyBase;
import GameObjects.Projectiles.Bullet;
import GameObjects.Projectiles.EnemyBullet;
import MainGame.GameConstants;
import MainGame.GameManager;
import PhysicsBase.Vectors.VelocityVector;

import java.awt.*;

public class DagronAi extends EnemyAiBase
{
    //Constructor
    public DagronAi(EnemyBase body)
    {
        super(body, 40);
    }

    //Public Methods
    @Override
    public void Advance()
    {
        super.AdvanceForward();
    }

    @Override
    public void Attack()
    {
        EnemyBullet b = null;
        switch(GetBody().GetProjectileDirection())
        {
            case Left:
                b = new EnemyBullet(new Point(GetBody().GetLeft() - Bullet.WIDTH - 1 ,
                        GetBody().GetCenterPoint().y), GetBody());
                b.SetVelocity(new VelocityVector(Math.PI, 7));
                break;
            case Right:
                b = new EnemyBullet(new Point(GetBody().GetRight() + 1,
                        GetBody().GetCenterPoint().y), GetBody());
                b.SetVelocity(new VelocityVector(0, 7));
                break;
        }

        GameManager.QueueObjectForAddition(b,
                GameConstants.ENEMY_PROJECTILE_RENDER_GROUP,
                GameConstants.ENEMY_PROJECTILE_GROUP);
    }

    @Override
    public void Retreat()
    {

    }

    @Override
    public void Stand()
    {

    }

    @Override
    public AiAction DetermineAction()
    {
        if(_queryCounter == 0)
            return AiAction.Attack;

        return AiAction.Advance;
    }
}
