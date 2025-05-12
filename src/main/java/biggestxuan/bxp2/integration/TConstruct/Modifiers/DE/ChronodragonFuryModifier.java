package biggestxuan.bxp2.integration.TConstruct.Modifiers.DE;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/3
 */
public class ChronodragonFuryModifier extends Modifier implements InventoryTickModifierHook, MeleeDamageModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK,ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOLTIP);
    }

    @Override
    public float getMeleeDamage(IToolStackView iToolStackView, ModifierEntry modifierEntry, ToolAttackContext toolAttackContext, float v, float v1) {
        Player player = toolAttackContext.getPlayerAttacker();
        if(player == null) return v1;
        if(!toolAttackContext.getLevel().isClientSide) {
            ModDataNBT nbt = iToolStackView.getPersistentData();
            int time = nbt.getInt(BxP2.RL("dragon_time"));
            return (float) ((1 + 0.000033 * time) * v1);
        }
        return v1;
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack itemStack) {
        long maxTime = Math.round(36000 * (1 + 0.2D * modifierEntry.getLevel()));
        ModDataNBT nbt = iToolStackView.getPersistentData();
        if(!level.isClientSide) {
            int time = nbt.getInt(BxP2.RL("dragon_time"));
            if(level.getGameTime() % 20 == 0 && time < maxTime && b){
                nbt.putInt(BxP2.RL("dragon_time"), time + 1);
            }
        }
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        int time = iToolStackView.getPersistentData().getInt(BxP2.RL("dragon_time"));
        list.add(BxP2.tr("tooltip.modifiers.chronodragon.fury").copy().append(Utils.formatPercent(0.000033 * time)));
    }
}
