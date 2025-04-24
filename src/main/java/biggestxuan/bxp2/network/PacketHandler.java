package biggestxuan.bxp2.network;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.network.toClient.CapabilityPacket;
import biggestxuan.bxp2.network.toServer.BuyGoodsPacket;
import biggestxuan.bxp2.network.toServer.ChangeDifficultyPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
public class PacketHandler {
    private static final String PROTOCOL = "packet";
    public static SimpleChannel HANDLER;

    public static void init(){
        HANDLER = NetworkRegistry.newSimpleChannel(
                BxP2.RL("packet_handler"),
                () -> PROTOCOL,
                (version) -> version.equals(PROTOCOL),
                (version) -> version.equals(PROTOCOL));

        int id = 0;
        HANDLER.registerMessage(
                ++id,
                ChangeDifficultyPacket.class,
                ChangeDifficultyPacket::encode,
                ChangeDifficultyPacket::decode,
                ChangeDifficultyPacket::handle
        );

        HANDLER.registerMessage(
                ++id,
                BuyGoodsPacket.class,
                BuyGoodsPacket::encode,
                BuyGoodsPacket::decode,
                BuyGoodsPacket::handle
        );

        HANDLER.messageBuilder(CapabilityPacket.class,++id)
                .encoder(CapabilityPacket::encode)
                .decoder(CapabilityPacket::new)
                .consumerNetworkThread(CapabilityPacket::handle)
                .add();

    }

    public static <T> void sendToServer(T msg){
        HANDLER.sendToServer(msg);
    }

    public static <T> void sendToClient(T msg, ServerPlayer player){
        HANDLER.send(PacketDistributor.PLAYER.with(()->player),msg);
    }

    public static void syncPlayerCapability(ServerPlayer player){
        player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
            CapabilityPacket packet = new CapabilityPacket(cap.getPhase(),cap.getMoney());
            PacketHandler.sendToClient(packet,player);
        });
    }
}
