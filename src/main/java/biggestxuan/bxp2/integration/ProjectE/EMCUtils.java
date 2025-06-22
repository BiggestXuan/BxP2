package biggestxuan.bxp2.integration.ProjectE;

import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.capabilities.PECapabilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.math.BigInteger;

/**
 * @Author Biggest_Xuan
 * 2025/6/2
 */
public final class EMCUtils {
    public static long getPlayerEMC(Player player){
        var cap = getCap(player);
        return cap == null ? 0L : cap.getEmc().longValue();
    }

    public static void addPlayerEMC(Player player,long emc){
        var cap = getCap(player);
        if(cap != null){
            cap.setEmc(cap.getEmc().add(BigInteger.valueOf(emc)));
            if(player instanceof ServerPlayer sp){
                cap.syncEmc(sp);
            }
        }
    }

    @Nullable
    public static IKnowledgeProvider getCap(Player player){
        var cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY);
        if(cap.isPresent()){
            return cap.orElseThrow(NullPointerException::new);
        }
        return null;
    }
}
