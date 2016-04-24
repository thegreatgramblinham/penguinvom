package GameObjects.BattleCharacters;

import Animation.enums.AnimationOrientation;
import GameObjects.Characters.Player.PlayerObject;
import javafx.scene.canvas.GraphicsContext;

public class PlayerBattleCharacter extends BattleCharacterBase
{
    //Variables
    private PlayerObject _player;

    //Constructor
    public PlayerBattleCharacter(PlayerObject player)
    {
        super(player.GetBounds(), player.GetHitBox());

        _player = player;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public void DrawRestingAnimation(GraphicsContext gc)
    {
        _player.DrawRestingAnimation(gc, this.GetGameDrawPoint(), AnimationOrientation.Default);
    }

    //Private Methods

}
