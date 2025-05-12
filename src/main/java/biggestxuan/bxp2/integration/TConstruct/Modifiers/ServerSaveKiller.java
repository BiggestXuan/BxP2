package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.LOL.BaseMeleeDamageModifier;
import biggestxuan.bxp2.utils.ServerUtils;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public class ServerSaveKiller extends BaseMeleeDamageModifier {
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        var attacker = toolAttackContext.getAttacker();
        if(attacker.level() instanceof ServerLevel serverLevel && attacker instanceof Player player){
            double sizeMB = ServerUtils.getWorldSize(serverLevel) / (1024d * 1024) * modifierEntry.getLevel() / 3d;
            if(BxP2.devMode){
                Utils.sendMessage(player, String.valueOf(sizeMB));
            }
            double rate = Math.min(0.3d,sizeMB/1000);
            return (float) (v * (1 + rate));
        }
        return v;
    }
}
