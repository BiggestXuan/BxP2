package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.BxPApi;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.LOL.BaseMeleeDamageModifier;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public class PxKiller extends BaseMeleeDamageModifier {
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        var attacker = toolAttackContext.getAttacker();
        if(attacker instanceof ServerPlayer player){
            IBxPCapability cap = BxPApi.INSTANCE.getPlayerCap(player);
            int[] data = cap.getClientData();
            if(data[1] != 0){
                if(data[1] > 1920 * 1080 && Utils.isRandom(0.01d)){
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,1,200));
                    return v1;
                }
                if(BxP2.devMode){
                    Utils.sendMessage(player, String.valueOf(data[1]));
                }
                int fps = Math.min(120,data[1]);
                if(BxP2.devMode){
                    return v1 * (1 + 0.1f * fps);
                }
                double rate = Math.min(0.000000005d * fps,0.025d);
                return (float) (v1 * (1 + rate));
            }
        }
        return v1;
    }
}
