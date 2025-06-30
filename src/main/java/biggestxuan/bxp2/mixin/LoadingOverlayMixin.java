package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.client.ClientCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/4/25
 */

@Mixin(LoadingOverlay.class)
public class LoadingOverlayMixin {
    private static final ResourceLocation BG = BxP2.RL("textures/gui/loading_bg.png");

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        Minecraft.getInstance().getWindow().setTitle(ClientCommon.TITLE);
    }
}
