package biggestxuan.bxp2.items;

import biggestxuan.bxp2.api.items.IBXItem;
import biggestxuan.bxp2.client.shop.ShopScreen;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.Mekanism.MekaArmorModifiers.MekaTinkerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag p_41424_) {
        super.appendHoverText(stack,level,list, p_41424_);
    }

    public static class BxPFoodItem extends BxPItem{
        public BxPFoodItem(int hunger,float saturation) {
            super(new Properties().food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build()));
        }
    }

    public static class BXShopItem extends BxPItem{
        @Override
        public @NotNull InteractionResultHolder<ItemStack> use(Level p_41432_, Player player, InteractionHand p_41434_) {
            if(p_41432_.isClientSide){
                DistExecutor.unsafeCallWhenOn(Dist.CLIENT,() -> ShopScreen.open::new);
            }
            return super.use(p_41432_, player, p_41434_);
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level p_41432_, Player player, InteractionHand p_41434_) {
        ItemStack stack = player.getItemInHand(p_41434_);
        if(MekaTinkerUtils.isAllMekaArmor(player) && !p_41432_.isClientSide && getBXValue(stack) > 0){
            if(player.isShiftKeyDown()){
                while (MekaTinkerUtils.addBXEnergy(player,getBXValue(stack)) && stack.getCount() > 0){
                    stack.shrink(1);
                    InteractionResultHolder.consume(stack);
                }
            } else if(MekaTinkerUtils.addBXEnergy(player,getBXValue(stack))){
                stack.shrink(1);
                InteractionResultHolder.consume(stack);
            } else{
                return InteractionResultHolder.fail(stack);
            }
        }
        return super.use(p_41432_, player, p_41434_);
    }
}
