package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.network.PacketHandler;
import biggestxuan.bxp2.network.toServer.ClientStatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

/**
 * @Author Biggest_Xuan
 * 2025/5/11
 */
public final class ClientUtils {
    public static boolean isPCLByClient(){
        String args = System.getProperties().getProperty("minecraft.launcher.brand");
        return args != null && args.contains("PCL");
    }

    public static boolean isPCLByServer(Player player){
        var cap = player.getCapability(BxPCapabilityProvider.CAPABILITY);
        if(cap.isPresent()){
            IBxPCapability c = cap.orElseThrow(NullPointerException::new);
            return c.isPcl();
        }
        return false;
    }

    public static void syncClientData(){
        Minecraft.getInstance().player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
            PacketHandler.sendToServer(new ClientStatePacket(isPCLByClient(),cap.getClientData()));
        });
    }
}
