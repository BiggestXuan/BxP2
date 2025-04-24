package biggestxuan.bxp2.items;

import biggestxuan.bxp2.api.items.IDiscountCard;
import net.minecraft.world.item.ItemStack;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public class DiscountCardItem extends BxPItem implements IDiscountCard {
    private final float discount;

    public DiscountCardItem(float discount){
        this.discount = discount;
    }

    @Override
    public float getDiscount(ItemStack stack) {
        return this.discount;
    }
}
