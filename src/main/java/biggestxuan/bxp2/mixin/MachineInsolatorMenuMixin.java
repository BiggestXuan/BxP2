package biggestxuan.bxp2.mixin;

import cofh.core.common.inventory.BlockEntityCoFHMenu;
import cofh.thermal.expansion.common.inventory.machine.MachineInsolatorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @Author Biggest_Xuan
 * 2025/5/9
 */

@Mixin(MachineInsolatorMenu.class)
public abstract class MachineInsolatorMenuMixin extends BlockEntityCoFHMenu {
    public MachineInsolatorMenuMixin(@Nullable MenuType<?> type, int windowId, Level world, BlockPos pos, Inventory inventory, Player player) {
        super(type, windowId, world, pos, inventory, player);
    }

}
