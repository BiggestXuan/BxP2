package biggestxuan.bxp2.integration.DraconicEvolution.Explosion;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.Utils;
import com.brandon3055.draconicevolution.entity.guardian.DraconicGuardianEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/5/29
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class ChaosGuardianEvent {
    public static int delayTicks = 0;
    public static BlockPos explosionPos = null;
    public static ServerLevel explosionWorld = null;

    @SubscribeEvent
    public static void guardianDeath(LivingDeathEvent event){
        LivingEntity entity = event.getEntity();
        if(entity instanceof DraconicGuardianEntity chaos && !chaos.level().isClientSide){
            ServerLevel world = (ServerLevel) entity.level();
            explosionWorld = (ServerLevel) entity.level();
            BlockPos pos = entity.blockPosition();
            explosionPos = new BlockPos(pos.getX(),64,pos.getZ());
            delayTicks = 900;
            for(Player player : world.players()){
                Utils.sendMessage(player,BxP2.tr("bxp2.chaos_island.warning",pos.getX(),pos.getZ()).withStyle(ChatFormatting.RED));
            }
            /*world.getServer().execute(() -> {
                int radius = 200;

                ProcessExplosion explosion = new ProcessExplosion(deathPos, radius, world, 600);
                explosion.enableEffect = true;

                ProcessHandler.getInstance().addProcess(explosion);
            });*/
        }
    }
}
