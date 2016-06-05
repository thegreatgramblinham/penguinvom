package MainGame;

import javafx.scene.input.KeyCode;

import java.awt.*;
import java.util.HashMap;

public class GameConstants
{
    //Collision Group Constants
    public static final String PLAYER_COLLISION_GROUP = "PlayerGroup";
    public static final String PLAYER_PROJECTILE_COLLISION_GROUP = "PlayerProjectileGroup";
    public static final String ENEMY_COLLISION_GROUP = "EnemyGroup";
    public static final String ENEMY_PROJECTILE_COLLISION_GROUP = "EnemyProjectileGroup";
    public static final String BACKGROUND_COLLISION_GROUP = "BackgroundGroup";
    public static final String PLAYER_GAMEBOUNDS_COLLISION_GROUP = "PlayerGameBoundsGroup";
    public static final String PROP_COLLISION_GROUP = "PropGroup";
    public static final String TRIGGER_COLLISION_GROUP = "TriggerGroup";
    public static final String NO_COLLISION_GROUP = "NoCollision";


    //region Render Group Constants
    public static final int SKY_RENDER_GROUP = 0;
    public static final int ROOM_RENDER_GROUP = 1;
    public static final int PLAYER_RENDER_GROUP = 3;
    public static final int PLAYER_PROJECTILE_RENDER_GROUP = 3;
    public static final int ENEMY_RENDER_GROUP = 3;
    public static final int ENEMY_PROJECTILE_RENDER_GROUP = 3;
    public static final int PROP_RENDER_GROUP_BACK = 2;
    public static final int PROP_RENDER_GROUP_MID = 4;
    public static final int PROP_RENDER_GROUP_MIDFORWARD = 5;
    public static final int PROP_RENDER_GROUP_FORWARD = 6;
    public static final int TRIGGER_RENDER_GROUP = 10; //needs to be the highest.

    public static final int CURTAIN_RENDER_GROUP_1 = 3;
    public static final int CURTAIN_RENDER_GROUP_2 = 4;
    public static final int CURTAIN_RENDER_GROUP_3 = 5;
    public static final int CURTAIN_RENDER_GROUP_4 = 6;
    public static final int CURTAIN_RENDER_GROUP_5 = 7;

    //endregion


    //Engine Run Constants
    public static final int ENGINE_FPS = 60;
    public static final Point GAME_STARTING_POINT = new Point(128,300);

    //Sector Constants
    public static final int DEFAULT_SECTOR_WIDTH = 3500;
    public static final int DEFAULT_SECTOR_HEIGHT = 1050;
    public static final int DEFAULT_SECTOR_GRID_UNIT_SIZE = 30;
    public static final float GRAVITY = 0.5F;

    //Private Static Fields
    private static HashMap<KeyCode, Boolean> keyPressed = new HashMap<>();

    //Private Constructor
    private GameConstants(){}

    //Public Static Methods
    public static boolean IsKeyPressed(KeyCode key)
    {
        if(keyPressed.containsKey(key))
            return keyPressed.get(key);

        return false;
    }

    public static void SetKeyPressed(KeyCode key)
    {
        if(keyPressed.containsKey(key))
            keyPressed.remove(key);

        keyPressed.put(key, true);
    }

    public static void SetKeyReleased(KeyCode key)
    {
        if(keyPressed.containsKey(key))
            keyPressed.remove(key);

        keyPressed.put(key, false);
    }

    //Private Methods

}
