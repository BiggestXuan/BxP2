package biggestxuan.bxp2.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * @Author Biggest_Xuan
 * 2025/4/21
 */
public class BXCycleBaseItem extends BxPItem{
    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if(!p_41405_.isClientSide){
            if(p_41404_.hasTag()){
                return;
            }
            CompoundTag tag = p_41404_.getOrCreateTag();
            CompoundTag tag1 = new CompoundTag();
            tag1.putInt("f",0);
            tag1.putInt("l",0);
            tag.put("bxp_cycle",tag1);
        }
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack stack =  super.getDefaultInstance();
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag tag1 = new CompoundTag();
        tag1.putInt("f",0);
        tag1.putInt("l",0);
        tag.put("bxp_cycle",tag1);
        return stack;
    }
}
