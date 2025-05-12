package biggestxuan.bxp2.integration.TConstruct.Modifiers.Botania;

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
import vazkii.botania.api.mana.ManaItemHandler;

/**
 * @Author Biggest_Xuan
 * 2025/5/6
 */
public class ManaRepairModifier extends Modifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack stack) {
        if(!level.isClientSide){
            int cost = (int) (45 * (1 - 0.1f * modifierEntry.getLevel()));
            if(livingEntity instanceof Player player){
                if(stack.isDamaged() && ManaItemHandler.instance().requestManaExact(stack,player,cost,true)){
                    stack.setDamageValue(stack.getDamageValue() - 1);
                }
            }
        }
    }
}
