package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.ProjectE.EMCMap;
import moze_intel.projecte.api.ItemInfo;
import moze_intel.projecte.emc.EMCMappingHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/5/31
 */

@Mixin(value = EMCMappingHandler.class,remap = false)
public class EMCMappingHandlerMixin{
    @Inject(method = "getStoredEmcValue",at = @At("HEAD"),cancellable = true)
    private static void __inject(ItemInfo info, CallbackInfoReturnable<Long> cir){
        cir.setReturnValue(EMCMap.getEMC(info.getItem()));
    }
}
