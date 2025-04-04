package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.BXUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PlayerAttackEvent {
    @SubscribeEvent
    public static void PlayerAttackEvent(LivingHurtEvent event){
        var source = event.getEntity();


    }
}
