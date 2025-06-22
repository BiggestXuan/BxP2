package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import earth.terrarium.adastra.api.systems.OxygenApi;
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
public class OmiteModifier extends Modifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack itemStack) {
        if(livingEntity instanceof Player player && b1){
            if(player.isCreative() || player.isSpectator()) return;
            player.setTicksFrozen(0);
            if(player.tickCount % 80 == 0){
                if(player.getAirSupply() < 300){
                    oxygen(itemStack,player);
                    return;
                }
            }
            if(player.tickCount % 60 == 0){
                if (!OxygenApi.API.hasOxygen(player)) {
                    oxygen(itemStack, player);
                }
            }
        }
    }

    private static void oxygen(ItemStack stack,Player player){
        player.setAirSupply(player.getMaxAirSupply());
        stack.hurtAndBreak(1,player,p -> {});
    }
}
