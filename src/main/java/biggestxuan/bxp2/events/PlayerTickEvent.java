package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.BXUtils;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PlayerTickEvent {
    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        var player = event.player;
        //BxP2.LOGGER.info("{}", BXUtils.getPlayerBXValue(player,30000));
    }
}
