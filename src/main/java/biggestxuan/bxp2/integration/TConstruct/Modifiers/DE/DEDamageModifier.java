package biggestxuan.bxp2.integration.TConstruct.Modifiers.DE;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.LimitLevelModifier;
import com.brandon3055.brandonscore.api.TechLevel;
import com.brandon3055.draconicevolution.entity.GuardianCrystalEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ValidateModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.UUID;
import java.util.function.BiConsumer;

/**
 * @Author Biggest_Xuan
 * 2025/5/7
 */
public class DEDamageModifier extends Modifier implements ToolStatsModifierHook, ValidateModifierHook {
    private float damageBoost = 0;
    private final int maxLevel = 4;
    private TechLevel tier;
    private final Lazy<UUID> uuid = Lazy.of(() -> UUID.nameUUIDFromBytes((damageBoost+this.getId().toString()).getBytes()));
    private final Lazy<String> attributeName = Lazy.of(() -> {
        ResourceLocation id = this.getId();
        String path = id.getPath();
        return path + "." + id.getNamespace() + ".attack_damage_"+damageBoost;
    });

    public DEDamageModifier(float damageBoost,TechLevel tier){
        this.damageBoost = damageBoost;
        this.tier = tier;
    }

    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
        hookBuilder.addHook(this,ModifierHooks.VALIDATE);
    }

    @Override
    public @Nullable Component validate(IToolStackView tool, ModifierEntry modifierEntry) {
        if(modifierEntry.getLevel() >= maxLevel){
            return BxP2.tr("tooltip.tconstruct.max_level");
        }
        int level = tool.getModifierLevel(BxPModifiers.Evolution.get());
        if(level > 0){
            return null;
        }
        return BxP2.tr("tooltip.tconstruct.evolution");
    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifierEntry, ModifierStatsBuilder builder) {
        double boost = damageBoost * modifierEntry.getLevel();
        ToolStats.ATTACK_DAMAGE.add(builder,boost);
    }

    public static class ChaoticDamageModifier extends DEDamageModifier implements MeleeHitModifierHook {

        @Override
        protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
            super.registerHooks(hookBuilder);
            hookBuilder.addHook(this,ModifierHooks.MELEE_HIT);
        }

        public ChaoticDamageModifier(float damageBoost, TechLevel tier) {
            super(damageBoost, tier);
        }

        @Override
        public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
            Entity entity = context.getTarget();
            if(entity instanceof GuardianCrystalEntity guardianCrystal){
                guardianCrystal.destabilize();
            }
            return MeleeHitModifierHook.super.beforeMeleeHit(tool, modifier, context, damage, baseKnockback, knockback);
        }

        @Override
        public void failedMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageAttempted) {
            MeleeHitModifierHook.super.failedMeleeHit(tool, modifier, context, damageAttempted);
            Entity entity = context.getTarget();
            if(entity instanceof GuardianCrystalEntity guardianCrystal){
                guardianCrystal.destabilize();
            }
        }
    }
}
