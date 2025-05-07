package biggestxuan.bxp2.network;

import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.network.toClient.CapabilityPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public class ClientPacketHandler {
    public static void HandleCapabilityPacket(CapabilityPacket packet, Supplier<NetworkEvent.Context> ctx){
        Player player = Minecraft.getInstance().player;
        if(player != null && !player.isDeadOrDying()){
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                cap.setMoney(packet.getMoney());
                cap.setPhase(packet.getPhase());
                cap.setBuyCreativeCount(packet.getCreativeCount());
            });
        }
    }
}
