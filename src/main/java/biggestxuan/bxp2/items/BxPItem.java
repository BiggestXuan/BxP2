package biggestxuan.bxp2.items;

import biggestxuan.bxp2.api.items.IBXItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

/**
 * @Author Biggest_Xuan
 * 2025/4/1
 */
public class BxPItem extends Item implements IBXItem {
    private int value = 0;
    public BxPItem(Properties p_41383_) {
        super(p_41383_);
    }

    public BxPItem(){
        super(new Properties());
    }

    public BxPItem(int value){
        super(new Properties());
        this.value = value;
    }

    public BxPItem(Rarity rarity){
        super(new Properties().rarity(rarity));
    }

    @Override
    public int getBXValue(ItemStack stack) {
        return this.value;
    }

    public static class BxPFoodItem extends BxPItem{
        public BxPFoodItem(int hunger,float saturation) {
            super(new Properties().food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build()));
        }
    }
}
