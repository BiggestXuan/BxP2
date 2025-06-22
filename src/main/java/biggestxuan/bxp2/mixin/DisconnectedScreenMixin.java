package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/6/15
 */

@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin extends Screen {
    @Shadow @Final private Component reason;
    private static final int bh = 20;
    private static final int lh = 10;
    private static final int ss = 15;

    protected DisconnectedScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "render",at = @At("TAIL"))
    private void __inject(GuiGraphics gui, int p_282952_, int p_283158_, float p_282166_, CallbackInfo ci){
        Component reason = this.reason;
        String sr = reason.getString();
        if(sr.contains("ItemStackIngredients")){
            addReasonText(gui, BxP2.tr("bxp2.crash.h_solve"));
        }
        //BxP2.LOGGER.info(reason.getString());
    }

    private void addReasonText(GuiGraphics gui,Component solve){
        int y = this.height / 2 + 40 + bh + ss;
        gui.drawCenteredString(Minecraft.getInstance().font,BxP2.tr("bxp2.crash.solve"),this.width / 2,y,0xFFFFFF);
        y += lh;
        gui.drawCenteredString(Minecraft.getInstance().font,solve,this.width / 2,y,0xFFFFFF);
        y += lh;
        gui.drawCenteredString(Minecraft.getInstance().font,BxP2.tr("bxp2.crash.o_do").append(BxP2.QQGroup),this.width / 2,y,0xFFFFFF);
    }
}
