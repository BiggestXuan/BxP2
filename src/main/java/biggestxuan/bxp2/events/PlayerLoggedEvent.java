package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.ModUtils;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/4
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PlayerLoggedEvent {
    @SubscribeEvent
    public static void playerLoggedEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if(BxP2.devMode){
            BxP2.LOGGER.info(ModUtils.getAllMods());
        }
    }
}
