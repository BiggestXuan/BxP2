package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import com.brandon3055.brandonscore.BrandonsCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/5/29
 */

@Mixin(value = BrandonsCore.class,remap = false)
public class BrandonsCoreMixin {
    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void __inject(CallbackInfo ci){
        BrandonsCore.inDev = BxP2.devMode;
    }
}
