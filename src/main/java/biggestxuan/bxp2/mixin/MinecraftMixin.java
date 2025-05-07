package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/4/25
 */

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    /**
     * @author Biggest_Xuan
     * @reason Modify Title
     */
    @Overwrite
    private String createTitle() {
        return BxP2.TITLE;
    }

    @Shadow
    public abstract Window getWindow();

    @Shadow private static Minecraft instance;

    @Inject(method = "updateTitle", at = @At("HEAD"), cancellable = true)
    private void onUpdateTitle(CallbackInfo ci) {
        instance.getWindow().setTitle(BxP2.TITLE);
        ci.cancel();
    }
}
