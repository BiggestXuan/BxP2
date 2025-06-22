package biggestxuan.bxp2.mixin;

import com.github.alexthe666.citadel.CitadelConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/5/29
 */

@Mixin(value = CitadelConstants.class,remap = false)
public class CitadelConstantsMixin {
    @Inject(method = "isAprilFools",at = @At("HEAD"),cancellable = true)
    private static void __inject(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }
}
