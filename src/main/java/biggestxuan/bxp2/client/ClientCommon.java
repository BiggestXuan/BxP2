package biggestxuan.bxp2.client;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.CalendarUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * @Author Biggest_Xuan
 * 2025/6/29
 */

@OnlyIn(Dist.CLIENT)
public class ClientCommon {
    public static String TITLE = "BxP2 - " + BxP2.VERSION + " - " + CalendarUtils.INSTANCE.getNowTimeWelcome() + (BxP2.devMode ? "-DevMode" : "");

    @Nullable
    public static Player getPlayer(){
        return Minecraft.getInstance().player;
    }
}
