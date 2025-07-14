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
import java.util.List;

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
            return cap.getMoney() >= getGoodsPrice(shopGoods,player) && cap.getPhase() >= shopGoods.getPhase();
        }
        return false;
    }

    public static float getGoodsPrice(ShopGoods sg,Player player){
        float discount = 1f;
        if(Config.difficulty == 1){
            discount *= 0.8f;
        }
        if(Config.difficulty == 3){
            discount *= 1.15f;
        }
        if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
            var cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
            if(sg.isCreative()){
                discount *= getCreativeItemRate(cap.getBuyCreativeCount());
            }
        }
        ItemStack stack = getPlayerDiscount(player);
        if(stack != null){
            discount *= ((IDiscountCard) stack.getItem()).getDiscount(stack);
        }
        return sg.getPrice() * discount;
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
            BxP2.LOGGER.error("玩家发送了无效的购买商品数据包！");
        }
        if(canBuyGoods(shopGoods,player)){
            ShopGoods finalShopGoods = shopGoods;
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap ->{
                cap.setMoney(cap.getMoney() - getGoodsPrice(finalShopGoods,player));
                ItemStack discount = getPlayerDiscount(player);
                if(discount != null){
                    discount.shrink(1);
                }
                if(finalShopGoods.isCreative()){
                    cap.setBuyCreativeCount(cap.getBuyCreativeCount() + 1);
                }
                player.addItem(finalShopGoods.getStack());
                PacketHandler.syncPlayerCapability(player);
            });
        }
    }

    @Nullable
    public static ItemStack dropMoney(Player player){
        ItemStack stack = BxPItems.MONEY.get().getDefaultInstance();
        if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
            IBxPCapability cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
            float drop = 0;
            switch (Config.difficulty){
                case 1 -> drop = (float) (Config.EASY_DEATH_DROP_MONEY_BASE + (cap.getPhase() * Config.EASY_DEATH_DROP_MONEY_ADDITION));
                case 2 -> drop = (float) (Config.NORMAL_DEATH_DROP_MONEY_BASE + (cap.getPhase() * Config.NORMAL_DEATH_DROP_MONEY_ADDITION));
                case 3 -> drop = (float) (Config.HARD_DEATH_DROP_MONEY_BASE + (cap.getPhase() * Config.HARD_DEATH_DROP_MONEY_ADDITION));
            }
            //float drop = cap.getMoney() * (Config.difficulty == 2 ? (0.1F + (cap.getPhase() * 0.015F)) : (0.2F + (cap.getPhase() * 0.022F)));
            drop *= cap.getMoney();
            if(drop <= 0.001F){
                return null;
            }
            cap.setMoney(cap.getMoney() - drop);
            CompoundTag tag = stack.getOrCreateTag();
            tag.putFloat("money",drop);
            tag.putBoolean("noLoss",true);
            Utils.sendMessage(player, BxP2.tr("bxp2.message.death",String.format("%.2f",drop)));
        }
        return stack;
    }

    private static float getCreativeItemRate(int count){
        double base = switch (Config.difficulty){
            case 1 -> Config.EASY_BUY_CREATIVE_RATE;
            case 2 -> Config.NORMAL_BUY_CREATIVE_RATE;
            default -> Config.HARD_BUY_CREATIVE_RATE;
        };
        return (float) (Math.pow(1+base,count));
    }
}
