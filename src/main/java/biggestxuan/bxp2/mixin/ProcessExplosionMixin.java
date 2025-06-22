package biggestxuan.bxp2.mixin;

import com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/5/29
 */

@Mixin(value = ProcessExplosion.class,remap = false)
public class ProcessExplosionMixin {
    @Inject(method = "updateCalculation",at = @At("HEAD"))
    public void __inject(CallbackInfo ci){

    }

    @Inject(method = "updateProcess",at = @At("HEAD"))
    public void _inject(CallbackInfo ci){

    }
}
