package biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.MekaArmorModifiers;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.LimitLevelModifier;
import com.c2h6s.etstlib.register.EtSTLibHooks;
import com.c2h6s.etstlib.tool.hooks.CustomBarDisplayModifierHook;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Iterator;

/**
 * @Author Biggest_Xuan
 * 2025/6/2
 */
public class MekaArmorModifier extends LimitLevelModifier implements ToolDamageModifierHook, CustomBarDisplayModifierHook, InventoryTickModifierHook {
    public MekaArmorModifier(){
        super(1);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_DAMAGE,ModifierHooks.INVENTORY_TICK, EtSTLibHooks.CUSTOM_BAR);
    }

    @Override
    public int onDamageTool(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i, @Nullable LivingEntity livingEntity) {
        return 0;
    }

    @Override
    public String barId(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i) {
        return "bxp2:meka_bar";
    }

    @Override
    public boolean showBar(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i) {
        return iToolStackView.getPersistentData().getFloat(BxP2.RL("bx_max_value")) > 0;
    }

    @Override
    public Vec2 getBarXYSize(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i) {
        var data = iToolStackView.getPersistentData();
        float max = data.getFloat(BxP2.RL("bx_max_value"));
        float bx = data.getFloat(BxP2.RL("bx_value"));
        return max > 0 ? new Vec2(Math.min(13.0F, 13.0F * bx / max), 1.0F) : new Vec2(0.0F, 0.0F);
    }

    @Override
    public int getBarRGB(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i) {
        return 0xFFa11971;
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack itemStack) {
        if(b1){
            livingEntity.getActiveEffects().removeIf(effect -> !effect.getEffect().isBeneficial());
            livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,1200,10));
        }
    }
}
