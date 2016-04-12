package MainGame;

import javafx.scene.input.KeyCode;

import java.util.HashMap;

public class GameConstants
{
    //Public Constants
    public static final String PLAYER_GROUP = "PlayerGroup";
    public static final String PLAYER_PROJECTILE_GROUP = "PlayerProjectileGroup";
    public static final String ENEMY_GROUP = "EnemyGroup";
    public static final String ENEMY_PROJECTILE_GROUP = "EnemyProjectileGroup";
    public static final String BACKGROUND_GROUP = "BackgroundGroup";
    public static final String PLAYER_GAMEBOUNDS_GROUP = "PlayerGameBoundsGroup";

    public static final int SKY_RENDER_GROUP = 0;
    public static final int ROOM_RENDER_GROUP = 1;
    public static final int PLAYER_RENDER_GROUP = 3;
    public static final int PLAYER_PROJECTILE_RENDER_GROUP = 3;
    public static final int ENEMY_RENDER_GROUP = 3;
    public static final int ENEMY_PROJECTILE_RENDER_GROUP = 3;

    public static final int ENGINE_FPS = 60;

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
