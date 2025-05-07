package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/30
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class ServerTickEvent {
    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event){
        MinecraftServer server = event.getServer();
        ServerLevel sl = server.overworld();
        if(sl.getDayTime() % 1200 == 0){
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
        if(server != null){
            server.getAllLevels().forEach(level -> {
                //level.getEntities().
            });
        }
    }
}
