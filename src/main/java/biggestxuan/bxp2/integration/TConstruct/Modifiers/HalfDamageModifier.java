package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.effects.BxPEffects;
import com.c2h6s.etstlib.register.EtSTLibHooks;
import com.c2h6s.etstlib.tool.hooks.CriticalAttackModifierHook;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/26
 */
public class HalfDamageModifier extends BaseMeleeDamageModifier implements CriticalAttackModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this,EtSTLibHooks.CRITICAL_ATTACK);
    }

    @Override
    public boolean setCritical(IToolStackView iToolStackView, ModifierEntry modifierEntry, LivingEntity attacker, InteractionHand interactionHand, Entity target, EquipmentSlot equipmentSlot, boolean isFullyCharged, boolean isExtraAttack, boolean isCritical) {
        if(isCritical){
            MobEffectInstance instance = attacker.getEffect(BxPEffects.HalfDamage.get());
            if(instance == null){
                attacker.addEffect(new MobEffectInstance(BxPEffects.HalfDamage.get(),120,0));
            }else{
                int level = Math.min(instance.getAmplifier()+1,modifierEntry.getLevel());
                attacker.removeEffect(BxPEffects.HalfDamage.get());
                attacker.addEffect(new MobEffectInstance(BxPEffects.HalfDamage.get(),120,level));
            }
            return false;
        }
        return false;
    }

    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        return 0.35f * v1;
    }
}
