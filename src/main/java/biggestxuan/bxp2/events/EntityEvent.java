package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/18
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class EntityEvent {
    @SubscribeEvent
    public static void itemEntityEvent(net.minecraftforge.event.entity.EntityEvent event){

    }
}
