package biggestxuan.bxp2.network.toServer;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.data.DifficultyData;
import biggestxuan.bxp2.utils.DifficultyUtils;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
public class ChangeDifficultyPacket {
    private int difficulty;

    public ChangeDifficultyPacket(int difficulty) {
        this.difficulty = difficulty;
    }

    public static void encode(ChangeDifficultyPacket message, FriendlyByteBuf bf){
        bf.writeInt(message.difficulty);
    }

    public static ChangeDifficultyPacket decode(FriendlyByteBuf bf){
        return new ChangeDifficultyPacket(bf.readInt());
    }

    public static void handle(ChangeDifficultyPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player != null) {
                if(canChangeDifficulty(player)){
                    int difficulty = msg.difficulty;
                    DifficultyData data = DifficultyData.getInstance(player.level());
                    data.setDifficulty(difficulty);
                    Config.DIFFICULTY.set(difficulty);
                    Utils.sendMessage(player,BxP2.tr("bxp2.message.difficulty_change").copy().append(DifficultyUtils.getDifficultyColor(difficulty)));
                }else{
                    Utils.sendMessage(player,"bxp2.message.no_permission_change_difficulty");
                }
            }
        });
        context.get().setPacketHandled(true);
    }

    private static boolean canChangeDifficulty(ServerPlayer player) {
        return player.isCreative() || player.hasPermissions(4);
    }
}
