package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.config.MekanismConfig;
import mekanism.generators.common.tile.TileEntityGasGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 *  @Author Biggest_Xuan
 *  2025/4/16
 */

@Mixin(TileEntityGasGenerator.class)
public class TileEntityGasMixin {

    @Shadow(remap = false)
    private FloatingLong generationRate;

    @Redirect(method = "onUpdateServer",at = @At(value = "INVOKE", target = "Lmekanism/common/capabilities/energy/BasicEnergyContainer;insert(Lmekanism/api/math/FloatingLong;Lmekanism/api/Action;Lmekanism/api/AutomationType;)Lmekanism/api/math/FloatingLong;",ordinal = 1),remap = false)
    public FloatingLong __redirect(BasicEnergyContainer instance, FloatingLong amount, Action action, AutomationType automationType){
        double amt = amount.getValue();
        //BxP2.LOGGER.info("{}","ccc");
        //BxP2.LOGGER.info("{}",generationRate.getValue());
        if(generationRate.getValue() != MekanismConfig.general.FROM_H2.get().getValue()){
            switch (Config.difficulty){
                case 2 -> {
                    amt *= 1.75;
                }
                case 3 -> {
                    amt *= 1.5;
                }
            }
        }
        return instance.insert(FloatingLong.create(amt),action,automationType);
    }
}
