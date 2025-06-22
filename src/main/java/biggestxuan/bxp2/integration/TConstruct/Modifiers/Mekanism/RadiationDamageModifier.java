package biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.BaseMeleeDamageModifier;
import mekanism.api.radiation.IRadiationManager;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/14
 */
public class RadiationDamageModifier extends BaseMeleeDamageModifier {
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        var target = toolAttackContext.getTarget();
        if(target instanceof LivingEntity living && !living.level().isClientSide){
            double radiation = IRadiationManager.INSTANCE.getRadiationLevel(living);
            if(radiation > 0){
                double rate = Math.min(0.12 * modifierEntry.getLevel(),radiation / 100);
                return (float) (v1 * (1 + rate));
            }
        }
        return v1;
    }
}
