package biggestxuan.bxp2.client.event;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.client.PCLWarningScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT,modid = BxP2.MODID)
public class ClientScreenEvent {
    public static boolean isShowPCL = false;

    @SubscribeEvent
    public static void clientScreenEvent(ScreenEvent.Init event){
        Screen screen = event.getScreen();
        if(screen instanceof TitleScreen){
            Screen PCLScreen = new PCLWarningScreen(screen);
            if(isPCL() && !isShowPCL){
                Minecraft.getInstance().setScreen(new PCLWarningScreen(screen));
                isShowPCL = true;
            }
        }
    }

    private static boolean isPCL(){
        String args = System.getProperties().getProperty("minecraft.launcher.brand");
        return args != null && args.contains("PCL");
    }
}
