package GameObjects;

import GameObjects.Characters.Enemies.*;
import GameObjects.Environmental.Props.Bush;
import GameObjects.Environmental.Props.Fountain;
import GameObjects.Environmental.Props.PropBase;
import GameObjects.enums.ObjectCategory;
import Stages.StageObjectRectProperties;

import java.awt.*;
import java.io.File;

public final class GameObjectConstants
{
    private GameObjectConstants() {}
    //Public Static Constants
    public static String IMAGE_FILE_TYPE = ".png";
    public static String SCENERY_ROOT = "src/imageAssets/backgrounds/";
    public static String ENEMY_ROOT = "src/imageAssets/enemies/";
    public static String PLAYER_ROOT = "src/imageAssets/player/";
    public static String PROP_ROOT = "src/imageAssets/props/";
    public static String PROJECTILE_ROOT = "src/imageAssets/projectiles/";

    //Public Methods
    public static String GetImageFilePathFromId(String id, ObjectCategory category)
    {
        switch(category)
        {
            case Backdrop:
            case Floor:
            case Wall:
                return SCENERY_ROOT + id + IMAGE_FILE_TYPE;
            case Prop:
                break;
            case Enemy:
                break;
        }

        return null;
    }

    public static PropBase GetPropInstanceFromId(String id, StageObjectRectProperties props)
    {
        String lowerId = id.toLowerCase();

        switch(lowerId)
        {
            case "fountain1":
                return new Fountain(new Point(props.GetXLoc(), props.GetYLoc()));
            case "bush1":
                return new Bush(new Point(props.GetXLoc(), props.GetYLoc()));
        }

        return null;
    }

    public static EnemyBase GetEnemyInstanceFromId(String id, StageObjectRectProperties props)
    {
        String lowerId = id.toLowerCase();
        Point location = new Point(props.GetXLoc(), props.GetYLoc());

        switch(lowerId)
        {
            case "slim1":
                return new Slim(location, 1.0f, 10);
            case "dagron1":
                return new Dagron(location, 1.0f, 10);
            case "skilleatin1":
                return new Skilleatin(location, 1.0f, 10);
            case "miniBlob1":
                return new MiniSlim(location, 1.0f, 10);
        }

        return null;
    }


}
