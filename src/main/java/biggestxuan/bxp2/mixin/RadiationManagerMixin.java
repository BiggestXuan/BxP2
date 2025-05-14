package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import biggestxuan.bxp2.utils.Utils;
import mekanism.common.lib.radiation.RadiationManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/5/14
 */

@Mixin(value = RadiationManager.class,remap = false)
public class RadiationManagerMixin {
    @Inject(method = "getRadiationResistance",at = @At("HEAD"),cancellable = true)
    public void __inject(LivingEntity entity, CallbackInfoReturnable<Double> cir){
        if(entity instanceof Player player){
            if(Utils.playerArmorHasTrait(player, BxPModifiers.Radiation_protect.get())){
                cir.setReturnValue(1D);
            }
        }
    }
}
