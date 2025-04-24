package biggestxuan.bxp2.client.event;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.client.shop.ShopScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event){
        LocalPlayer player = Minecraft.getInstance().player;
        //BxP2.LOGGER.info("{}",player == null);
        if(player == null) return;
       // BxP2.LOGGER.info("{}",player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent());
        player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
           // BxP2.LOGGER.info("{}",cap.getMoney());
           // BxP2.LOGGER.info("{}",cap.getPhase());
        });
    }

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {

    }
}
