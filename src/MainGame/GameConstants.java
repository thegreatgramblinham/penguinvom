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

    //Private Static Fields
    private static HashMap<KeyCode, Boolean> keyPressed = new HashMap<>();

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
