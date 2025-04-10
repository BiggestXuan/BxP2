package biggestxuan.bxp2.items;

import biggestxuan.bxp2.client.DifficultyScreen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
public class DifficultyChangeItem extends BxPItem{
    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if(p_41432_.isClientSide){
            DifficultyScreen.open();
            return InteractionResultHolder.success(p_41433_.getItemInHand(p_41434_));
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }
}
