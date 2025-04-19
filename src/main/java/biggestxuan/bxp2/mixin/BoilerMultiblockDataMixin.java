package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.Config;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.common.content.boiler.BoilerMultiblockData;
import mekanism.common.lib.multiblock.MultiblockData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/4/19
 */

@Mixin(BoilerMultiblockData.class)
public abstract class BoilerMultiblockDataMixin extends MultiblockData {

    @Shadow(remap = false) public IGasTank superheatedCoolantTank;

    public BoilerMultiblockDataMixin(BlockEntity tile) {
        super(tile);
    }

    @Inject(method = "tick",at = @At("HEAD"),cancellable = true,remap = false)
    public void __inject(Level world, CallbackInfoReturnable<Boolean> cir){
        if(Config.difficulty > 1 && (this.superheatedCoolantTank.isEmpty() || this.superheatedCoolantTank.getStack().getAmount() < 10)){
            cir.setReturnValue(super.tick(world));
        }
    }
}
