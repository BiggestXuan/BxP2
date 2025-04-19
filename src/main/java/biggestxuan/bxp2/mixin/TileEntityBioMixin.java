package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;
import mekanism.api.providers.IBlockProvider;
import mekanism.generators.common.config.MekanismGeneratorsConfig;
import mekanism.generators.common.tile.TileEntityBioGenerator;
import mekanism.generators.common.tile.TileEntityGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/4/16
 */

@Mixin(TileEntityBioGenerator.class)
public abstract class TileEntityBioMixin extends TileEntityGenerator {
    public TileEntityBioMixin(IBlockProvider blockProvider, BlockPos pos, BlockState state, @NotNull FloatingLongSupplier maxOutput) {
        super(blockProvider, pos, state, maxOutput);
    }

    @Inject(method = "onUpdateServer",at = @At(
            value = "INVOKE",
            target = "Lmekanism/common/capabilities/energy/BasicEnergyContainer;insert(Lmekanism/api/math/FloatingLong;Lmekanism/api/Action;Lmekanism/api/AutomationType;)Lmekanism/api/math/FloatingLong;",ordinal = 1,remap = false)
    ,remap = false)
    public void __inject(CallbackInfo ci){
        FloatingLong amt = MekanismGeneratorsConfig.generators.bioGeneration.get();
        double value = amt.getValue();
        switch (Config.difficulty){
            case 1 -> value *= 2;
            case 2 -> value *= 1.85;
            case 3 -> value *= 1.65;
        }
        getEnergyContainer().insert(FloatingLong.create(value), Action.EXECUTE, AutomationType.INTERNAL);
    }
}
