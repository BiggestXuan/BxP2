package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.AsteorBar.BXValueOverlay;
import com.afoxxvi.asteorbar.overlay.ForgeGuiRegistry;
import com.afoxxvi.asteorbar.overlay.Overlays;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/6/10
 */

@Mixin(value = ForgeGuiRegistry.class,remap = false)
public class ForgeGuiRegistryMixin {
    @Shadow private static boolean initialized;

    @Inject(method = "init",at = @At("HEAD"))
    private static void __inject(CallbackInfo ci){
        if (!initialized) {
            Overlays.registerOverlayAtRecommended(new BXValueOverlay(), Overlays.Position.UNSPECIFIED);
        }
    }
}
