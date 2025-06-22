package biggestxuan.bxp2.integration.TConstruct.Modifiers;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public class FlyModifier extends Modifier implements InventoryTickModifierHook, EquipmentChangeModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK,ModifierHooks.EQUIPMENT_CHANGE);
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack itemStack) {
        if(livingEntity instanceof Player player && b1){
            if(!player.isCreative() && !player.isSpectator()){
                player.getAbilities().mayfly = true;
                if(player.tickCount % 200 == 0){
                    itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                }
            }
        }
    }

    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        if(context.getEntity() instanceof Player player){
            if(!player.isCreative() && !player.isSpectator()){
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
            }
        }
    }

    public void onEquipmentChange(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context, EquipmentSlot slotType) {
        if(context.getEntity() instanceof Player player){
            if(!player.isCreative() && !player.isSpectator()){
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
            }
        }
    }
}
