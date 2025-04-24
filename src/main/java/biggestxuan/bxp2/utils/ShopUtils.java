package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.api.items.IDiscountCard;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.exception.BxPNoGoodsException;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.network.PacketHandler;
import biggestxuan.bxp2.recipes.ShopGoods;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public final class ShopUtils {
    @Nullable
    public static ItemStack getPlayerDiscount(Player player){
        for(ItemStack stack : player.getInventory().items){
            if(stack.getItem() instanceof IDiscountCard card){
                return stack;
            }
        }
        return null;
    }

    public static boolean canBuyGoods(ShopGoods shopGoods, Player player){
        if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
            var cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
            return cap.getMoney() >= shopGoods.getPrice() && cap.getPhase() >= shopGoods.getPhase();
        }
        return false;
    }

    public static float getGoodsPrice(ShopGoods sg,Player player){
        ItemStack stack = getPlayerDiscount(player);
        if(stack == null) return sg.getPrice();
        return sg.getPrice() * ((IDiscountCard) stack.getItem()).getDiscount(stack);
    }

    public static void buyGoods(ItemStack stack, ServerPlayer player){
        ShopGoods shopGoods = null;
        for(ShopGoods sg : ShopGoods.values()){
            if(ItemStack.isSameItemSameTags(sg.getStack(), stack)){
                shopGoods = sg;
                break;
            }
        }

        if(shopGoods == null || player == null){
            throw new BxPNoGoodsException("玩家发送了无效的购买商品数据包！");
        }
        if(canBuyGoods(shopGoods,player)){
            ShopGoods finalShopGoods = shopGoods;
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap ->{
                cap.setMoney(cap.getMoney() - getGoodsPrice(finalShopGoods,player));
                ItemStack discount = getPlayerDiscount(player);
                if(discount != null){
                    discount.shrink(1);
                }
                player.addItem(finalShopGoods.getStack());
                PacketHandler.syncPlayerCapability(player);
            });
        }
    }

    @Nullable
    public static ItemStack dropMoney(Player player){
        if(Config.difficulty == 1){
            return null;
        }
        ItemStack stack = BxPItems.MONEY.get().getDefaultInstance();
        if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
            IBxPCapability cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
            float drop = cap.getMoney() * (Config.difficulty == 2 ? (0.1F + (cap.getPhase() * 0.015F)) : (0.2F + (cap.getPhase() * 0.022F)));
            cap.setMoney(cap.getMoney() - drop);
            CompoundTag tag = stack.getOrCreateTag();
            tag.putFloat("money",drop);
            Utils.sendMessage(player, BxP2.tr("bxp2.message.death",String.format("%.2f",drop)));
        }
        return stack;
    }
}
