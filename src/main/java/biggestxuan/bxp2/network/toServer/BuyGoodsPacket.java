package biggestxuan.bxp2.network.toServer;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.exception.BxPNoGoodsException;
import biggestxuan.bxp2.network.PacketHandler;
import biggestxuan.bxp2.recipes.ShopGoods;
import biggestxuan.bxp2.utils.ShopUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static biggestxuan.bxp2.BxP2.LOGGER;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public class BuyGoodsPacket {
    private ItemStack stack;

    public BuyGoodsPacket(ItemStack stack){
        this.stack = stack;
    }

    public static void encode(BuyGoodsPacket message, FriendlyByteBuf bf){
        bf.writeItem(message.stack);
    }

    public static BuyGoodsPacket decode(FriendlyByteBuf bf){
        return new BuyGoodsPacket(bf.readItem());
    }

    public static void handle(BuyGoodsPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            ItemStack stack = msg.stack.copy();
            ShopUtils.buyGoods(stack,player);
            context.get().setPacketHandled(true);
        });
    }


}
