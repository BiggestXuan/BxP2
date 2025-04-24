package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.items.IBXItem;
import biggestxuan.bxp2.client.DifficultyScreen;
import biggestxuan.bxp2.client.shop.ShopScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

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

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack stack = p_41433_.getItemInHand(p_41434_);
        if(getBXValue(stack) > 0 && p_41432_.isClientSide){
            Minecraft.getInstance().setScreen(new ShopScreen());
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }
}
