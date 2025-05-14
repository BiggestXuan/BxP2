package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.Config;
import mekanism.common.capabilities.heat.VariableHeatCapacitor;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.generators.common.content.fission.FissionReactorMultiblockData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/5/13
 */

@Mixin(value = FissionReactorMultiblockData.class,remap = false)
public abstract class FissionReactorMultiblockDataMixin extends MultiblockData {

    @Shadow public double reactorDamage;

    @Shadow public VariableHeatCapacitor heatCapacitor;

    public FissionReactorMultiblockDataMixin(BlockEntity tile) {
        super(tile);
    }

    @Shadow abstract void setForceDisable(boolean forceDisable);

    @Inject(method = "handleDamage",at = @At("HEAD"),cancellable = true)
    public void __inject(Level world, CallbackInfo ci){
        double lastDamage = this.reactorDamage;
        double temp = this.heatCapacitor.getTemperature();
        if(lastDamage >= 100 && temp >= 1200 && Config.difficulty == 1){
            this.setForceDisable(true);
            this.markDirty();
            ci.cancel();
        }
    }
}
