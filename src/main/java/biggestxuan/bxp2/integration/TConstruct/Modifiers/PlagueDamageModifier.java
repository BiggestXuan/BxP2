package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.LOL.BaseMeleeDamageModifier;
import com.github.alexthe666.rats.registry.RatsEffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/9
 */
public class PlagueDamageModifier extends BaseMeleeDamageModifier {
    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        int level = modifierEntry.getLevel();
        LivingEntity target = toolAttackContext.getLivingTarget();
        if(target != null){
            MobEffectInstance instance = target.getEffect(RatsEffectRegistry.PLAGUE.get());
            if(instance != null){
                return v1 * (1 + 0.15F * level);
            }
        }
        return v1;
    }
}
