package biggestxuan.bxp2.integration.thinker.Modifiers.DE;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/4/3
 */
public class SorrowModifier extends Modifier implements MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }

    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        int level = modifier.getLevel();
        if(Utils.isRandom((BxP2.devMode ? 0.9 : 0.03) * level)){
            context.getLivingTarget().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,100,10));
        }
        return knockback;
    }
}
