package biggestxuan.bxp2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.botania.common.entity.GaiaGuardianEntity;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
@Mixin(GaiaGuardianEntity.class)
public abstract class GaiaGuardianEntityMixin {

    @Redirect(method = "actuallyHurt",at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(FF)F"))
    public float __redirect(float a, float b){
        return b;
    }

    @Redirect(method = "hurt",at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(FF)F"))
    public float _redirect(float a, float b){
        return b;
    }

    @Redirect(method = "getDamageAfterArmorAbsorb",at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(FF)F"))
    public float ___redirect(float a, float b){
        return b;
    }
}
