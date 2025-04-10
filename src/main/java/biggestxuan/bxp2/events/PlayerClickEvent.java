package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PlayerClickEvent {
    @SubscribeEvent
    public static void playerUseItemEvent(PlayerInteractEvent event){

    }
}
