package biggestxuan.bxp2.network.toServer;

import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @Author Biggest_Xuan
 * 2025/5/11
 */
public class ClientStatePacket {
    private final boolean state;
    private final int[] data;

    public ClientStatePacket(boolean state,int[] data) {
        this.state = state;
        this.data = data;
    }

    public static void encode(ClientStatePacket message, FriendlyByteBuf bf){
        bf.writeBoolean(message.state);
        bf.writeVarIntArray(message.data);
    }

    public static ClientStatePacket decode(FriendlyByteBuf bf){
        return new ClientStatePacket(bf.readBoolean(),bf.readVarIntArray());
    }

    public static void handle(ClientStatePacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap ->{
                cap.setPcl(msg.state);
                cap.setClientData(msg.data);
            });
        });
    }
}
