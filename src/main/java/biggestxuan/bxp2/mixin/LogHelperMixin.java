package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import com.brandon3055.draconicevolution.utils.LogHelper;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/5/29
 */
@Mixin(value = LogHelper.class,remap = false)
public abstract class LogHelperMixin {
    @Shadow
    public static void log(Level logLevel, Object object) {}

    @Inject(method = "dev(Ljava/lang/Object;)V",at = @At("HEAD"),cancellable = true)
    private static void __inject(Object object, CallbackInfo ci){
        if (BxP2.devMode) {
            log(Level.INFO, "[DEV]: " + object);
            ci.cancel();
        }
    }
}
