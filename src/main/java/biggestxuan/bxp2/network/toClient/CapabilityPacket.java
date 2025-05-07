package biggestxuan.bxp2.network.toClient;

import biggestxuan.bxp2.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public class CapabilityPacket {
    private final int phase;
    private final float money;
    private final int creativeCount;

    public CapabilityPacket(int phase,float money,int creativeCount){
        this.money = money;
        this.phase = phase;
        this.creativeCount = creativeCount;
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(phase);
        buf.writeFloat(money);
        buf.writeInt(creativeCount);
    }

    public void handle(Supplier<NetworkEvent.Context> context){
        NetworkEvent.Context c = context.get();
        c.enqueueWork(()-> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()-> ClientPacketHandler.HandleCapabilityPacket(this,context)));
        c.setPacketHandled(true);
    }

    public CapabilityPacket(FriendlyByteBuf buf){
        this.phase = buf.readInt();
        this.money = buf.readFloat();
        this.creativeCount = buf.readInt();
    }

    public float getMoney() {
        return money;
    }

    public int getPhase() {
        return phase;
    }

    public int getCreativeCount(){
        return creativeCount;
    }
}
