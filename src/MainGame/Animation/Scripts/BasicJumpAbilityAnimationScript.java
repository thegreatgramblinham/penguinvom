package MainGame.Animation.Scripts;

import CharacterFunctions.Abilities.Ability;
import GameObjects.BattleCharacters.BattleCharacterBase;
import MainGame.Animation.Events.BasicJumpAbilityAnimationExecutionEvent;
import MainGame.Animation.Steps.CharacterMoveStep;
import MainGame.Animation.Steps.ScriptStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Animation of a basic jump attack.
 */
public class BasicJumpAbilityAnimationScript extends AbilityAnimationScript<BasicJumpAbilityAnimationExecutionEvent>
{
    //Variables

    //Constructor
    public BasicJumpAbilityAnimationScript(Ability ability, BattleCharacterBase user,
           List<BattleCharacterBase> targets, int totalFramesAllotted)
    {
        super(ability, user, targets, totalFramesAllotted);
    }

    //Private Methods
    @Override
    protected ArrayList<ScriptStep> GenerateScript()
    {
        return new ArrayList<ScriptStep>()
        {{
            add(new CharacterMoveStep(GetTotalFramesAllotted()));
        }};
    }
}
