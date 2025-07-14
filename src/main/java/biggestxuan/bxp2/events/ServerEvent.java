package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.blocks.BxPBlocks;
import biggestxuan.bxp2.blocks.TileEntity.ATMTileEntity;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.integration.DraconicEvolution.Explosion.ChaosGuardianEvent;
import biggestxuan.bxp2.utils.Utils;
import com.brandon3055.brandonscore.handlers.ProcessHandler;
import com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

/**
 * @Author Biggest_Xuan
 * 2025/4/30
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class ServerEvent {
    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event){
        MinecraftServer server = event.getServer();
        ServerLevel sl = server.overworld();
        if(event.phase == TickEvent.Phase.END){
            sl = ChaosGuardianEvent.explosionWorld;
            if(sl == null) return;
            if(ChaosGuardianEvent.delayTicks > 0){
                ChaosGuardianEvent.delayTicks --;
            }
            for(Player player : sl.players()){
                BlockPos p = ChaosGuardianEvent.explosionPos;
                if(p == null) return;
                Utils.sendDevMessage(player,BxP2.tr(String.valueOf(ChaosGuardianEvent.delayTicks)));
                //Utils.sendDevMessage(player,BxP2.tr(""+chunkX+","+chunkZ));
                //Utils.sendDevMessage(player,BxP2.tr(String.valueOf(ChaosGuardianEvent.explosionWorld.hasChunk(chunkX,chunkZ))));
            }
            if(ChaosGuardianEvent.delayTicks == 0 && ChaosGuardianEvent.explosionPos != null && ChaosGuardianEvent.explosionWorld != null){
                int radius = 384;
                BlockPos p = ChaosGuardianEvent.explosionPos;
                int chunkX = p.getX() >> 4;
                int chunkZ = p.getZ() >> 4;
                if(!ChaosGuardianEvent.explosionWorld.hasChunk(chunkX,chunkZ)){
                    return;
                }
                BlockPos bp = new BlockPos(p.getX(),p.getY(),p.getZ());
                ProcessExplosion explosion = new ProcessExplosion(
                        bp, radius, ChaosGuardianEvent.explosionWorld, 0
                );
                explosion.enableEffect = true;
                explosion.detonate();
                ProcessHandler.addProcess(explosion);

                ChaosGuardianEvent.explosionPos = null;
                //ChaosGuardianEvent.explosionWorld = null;
            }
        }
        if(server.getTickCount() % 1200 == 0){
            server.getPlayerList().getPlayers().forEach(player -> {
                player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                    float rate = 1;
                    switch (Config.difficulty){
                        case 1 -> rate = (float) Config.EASY_INTEREST_RATE;
                        case 2 -> rate = (float) Config.NORMAL_INTEREST_RATE;
                        case 3 -> rate = (float) Config.HARD_INTEREST_RATE;
                    }
                    if(cap.getMoney() < 0){
                        cap.setMoney(rate * cap.getMoney());
                    }
                });
            });
        }
        if(sl.getServer().getTickCount() % 10 == 0){
            //server.getPlayerList().getPlayers().forEach(PacketHandler::syncPlayerCapability);
        }
        if(server != null){
            server.getAllLevels().forEach(level -> {
                //level.getEntities().
            });
        }
    }
}
