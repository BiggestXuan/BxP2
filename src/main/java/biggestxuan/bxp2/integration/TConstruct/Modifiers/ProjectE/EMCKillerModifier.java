package biggestxuan.bxp2.integration.TConstruct.Modifiers.ProjectE;

import biggestxuan.bxp2.integration.ProjectE.EMCUtils;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.BaseMeleeDamageModifier;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/6/1
 */
public class EMCKillerModifier extends BaseMeleeDamageModifier {
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        var living = toolAttackContext.getAttacker();
        if(living instanceof Player player){
            long emc = EMCUtils.getPlayerEMC(player);
            long cost = Math.min(1000000L * modifierEntry.getLevel(),emc / 10);
            EMCUtils.addPlayerEMC(player,cost);
            return (float) (v1 * (1 + cost / 3000000d));
        }
        return v1;
    }
}
