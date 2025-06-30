package biggestxuan.bxp2.mixin;

import mekanism.generators.common.content.fusion.FusionReactorMultiblockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/6/24
 */

@Mixin(value = FusionReactorMultiblockData.class,remap = false)
public class FusionReactorMultiblockDataMixin {
    @Shadow private double lastPlasmaTemperature;

    @Shadow private double lastCaseTemperature;

    @Inject(method = "updateTemperatures",at = @At("TAIL"))
    public void __inject(CallbackInfo ci){
        lastPlasmaTemperature = Math.min(lastPlasmaTemperature,300000000000L);
        lastCaseTemperature = Math.min(lastPlasmaTemperature,200000000000L);
    }
}
