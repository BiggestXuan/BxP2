package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.Config;
import dev.shadowsoffire.hostilenetworks.data.DataModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/4/9
 */
@Mixin(value = DataModel.class,remap = false)
public class DataModelMixin {
    @Shadow
    @Final
    private int simCost;

    @Inject(method = "simCost",at = @At("HEAD"),cancellable = true)
    public void __inject(CallbackInfoReturnable<Integer> cir){
        if(Config.difficulty >= 2){
            double cost = this.simCost;
            cost = Math.pow(cost,1.75);
            if(Config.difficulty == 3){
                cost *= 1.15;
            }
            cir.setReturnValue((int) cost);
        }
    }
}
