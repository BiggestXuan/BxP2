package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.BxPApi;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.server.level.ServerPlayer;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public class FpsKillerModifier extends BaseMeleeDamageModifier {
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        var attacker = toolAttackContext.getAttacker();
        if(attacker instanceof ServerPlayer player){
            IBxPCapability cap = BxPApi.getPlayerCap(player);
            int[] data = cap.getClientData();
            if(data[0] != 0){
                if(data[0] >= 360 && Utils.isRandom(0.03d)){
                    player.kill();
                    return v1;
                }
                if(BxP2.devMode){
                    Utils.sendMessage(player, String.valueOf(data[0]));
                }
                int fps = Math.min(120,data[0]);
                if(BxP2.devMode){
                    return v1 * (1 + 0.1f * fps);
                }
                return v1 * (1 + 0.0001f * fps);
            }
        }
        return v1;
    }
}
