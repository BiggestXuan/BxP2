package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import biggestxuan.bxp2.utils.Utils;
import earth.terrarium.adastra.common.systems.TemperatureApiImpl;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */

@Mixin(value = TemperatureApiImpl.class,remap = false)
public class TemperatureApiImplMixin {
    @Inject(method = "freezeEntity",at = @At("HEAD"),cancellable = true)
    public void __inject(LivingEntity entity, ServerLevel level, CallbackInfo ci){
        if(entity instanceof Player player){
            if(Utils.playerArmorHasTrait(player,BxPModifiers.omite.get())){
                ci.cancel();
            }
        }
    }
}
