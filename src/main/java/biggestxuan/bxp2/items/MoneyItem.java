package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public class MoneyItem extends Item {
    public MoneyItem() {
        super(new Properties().rarity(Rarity.UNCOMMON));
    }

    public static float getMoney(ItemStack stack){
        if(stack.getItem() instanceof MoneyItem money){
            CompoundTag tag = stack.getOrCreateTag();
            return tag.getFloat("money");
        }
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(BxP2.tr("desc.bxp2.money"));
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack =  super.getDefaultInstance();
        stack.getOrCreateTag().putInt("money",1);
        return stack;
    }

    @Override
    public Component getName(ItemStack p_41458_) {
        return BxP2.tr("item.bxp2.money",String.format("%.2f",getMoney(p_41458_)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide && BxP2.devMode){
            ItemStack stack = player.getItemInHand(hand);
            costMoney(stack,player);
            return InteractionResultHolder.success(stack);
        }
        return super.use(level,player,hand);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if(!BxP2.devMode){
            costMoney(p_41404_,p_41406_);
        }
    }

    public void costMoney(ItemStack stack, Entity p){
        float money = getMoney(stack);
        if(money > 0 && p instanceof ServerPlayer player){
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                cap.setMoney(cap.getMoney() + money);
                stack.shrink(1);
            });
        }
    }
}
