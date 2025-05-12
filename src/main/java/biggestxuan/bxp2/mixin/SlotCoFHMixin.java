package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.items.BxPItem;
import cofh.lib.common.inventory.SlotCoFH;
import cofh.lib.common.inventory.SlotRemoveOnly;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * @Author Biggest_Xuan
 * 2025/5/9
 */

@Mixin(SlotCoFH.class)
public abstract class SlotCoFHMixin extends Slot {
    public SlotCoFHMixin(Container p_40223_, int p_40224_, int p_40225_, int p_40226_) {
        super(p_40223_, p_40224_, p_40225_, p_40226_);
    }

    /**
     * @author Biggest_Xuan
     * @reason no
     */
    @Overwrite
    public boolean mayPlace(ItemStack stack) {
        SlotCoFH slot = (SlotCoFH) (Object) this;
        if(slot instanceof SlotRemoveOnly r){
            return r.mayPlace(stack);
        }
        if(stack.getItem() instanceof BxPItem){
            return true;
        }
        return this.container.canPlaceItem(this.getContainerSlot(), stack);
    }
}
