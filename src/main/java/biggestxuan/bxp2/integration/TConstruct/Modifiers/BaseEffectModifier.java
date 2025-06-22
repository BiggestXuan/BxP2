package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import biggestxuan.bxp2.utils.Utils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public class BaseEffectModifier extends Modifier implements InventoryTickModifierHook {
    private MobEffectInstance instance;

    public BaseEffectModifier(MobEffectInstance instance){
        this.instance = instance;
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack itemStack) {
        if(livingEntity instanceof Player player && player.tickCount % 10 == 0){
            //Utils.sendMessage(player, String.valueOf(i));
        }
        if(level instanceof ServerLevel sl && sl.getServer().getTickCount() % 20 == 0 && b1){
            livingEntity.addEffect(this.instance);
        }
    }
}
