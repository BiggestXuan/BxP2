package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.LOL.BaseMeleeDamageModifier;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public class LuckyAttackModifier extends BaseMeleeDamageModifier {
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        var attacker = toolAttackContext.getAttacker();
        var target = toolAttackContext.getLivingTarget();
        if(attacker instanceof Player player){
            if(Utils.isRandom(0.02 * modifierEntry.getLevel()) && target != null){
                if(target.getHealth() < player.getHealth()){
                    target.kill();
                }
            }
        }
        return v1;
    }
}
