package biggestxuan.bxp2.network.toServer;

import biggestxuan.bxp2.blocks.TileEntity.ATMTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @Author Biggest_Xuan
 * 2025/7/13
 */
public class WithdrawPacket {
    private BlockPos pos;
    private float amt;

    public WithdrawPacket(BlockPos pos,float amt){
        this.pos = pos;
        this.amt = amt;
    }

    public static void encode(WithdrawPacket message, FriendlyByteBuf bf){
        bf.writeBlockPos(message.pos);
        bf.writeFloat(message.amt);
    }

    public static WithdrawPacket decode(FriendlyByteBuf bf){
        return new WithdrawPacket(bf.readBlockPos(),bf.readFloat());
    }

    public static void handle(WithdrawPacket msg, Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            BlockEntity entity = player.level().getBlockEntity(msg.pos);
            if(entity instanceof ATMTileEntity atm){
                atm.withdrawMoney(player,msg.amt);
                if(atm.ownerId == null){
                    atm.ownerId = player.getUUID();
                    atm.setChanged();
                }
            }
            context.get().setPacketHandled(true);
        });
    }
}
