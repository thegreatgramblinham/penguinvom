package GameObjects;

import GameObjects.enums.ObjectCategory;

public final class ImageConstants
{
    private ImageConstants() {}
    //Public Static Constants
    public static String IMAGE_FILE_TYPE = ".png";
    public static String SCENERY_ROOT = "src/imageAssets/backgrounds/";
    public static String ENEMY_ROOT = "src/imageAssets/enemies/";
    public static String PLAYER_ROOT = "src/imageAssets/player/";
    public static String PROP_ROOT = "src/imageAssets/props/";
    public static String PROJECTILE_ROOT = "src/imageAssets/projectiles/";

    //Public Methods
    public static String GetImageFilePathFromId(String Id, ObjectCategory category)
    {
        switch(category)
        {
            case Backdrop:
            case Floor:
            case Wall:
                return SCENERY_ROOT + Id + IMAGE_FILE_TYPE;
            case Prop:
                break;
            case Enemy:
                break;
        }

        return null;
    }

    //Private Methods

}
