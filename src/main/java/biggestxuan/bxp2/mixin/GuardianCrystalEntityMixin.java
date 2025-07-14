package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import com.brandon3055.draconicevolution.entity.GuardianCrystalEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

/**
 * @Author Biggest_Xuan
 * 2025/7/8
 */

@Mixin(value = GuardianCrystalEntity.class,remap = false)
public abstract class GuardianCrystalEntityMixin {
    @Shadow public abstract void destabilize();

    @Inject(method = "hurt",at = @At("HEAD"))
    public void __inject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if(source.getDirectEntity() instanceof Player player){
            ItemStack stack = player.getMainHandItem();
            if(stack.getItem() instanceof ModifiableItem){
                if(ModifierUtil.getModifierLevel(stack, BxPModifiers.ChaoticDamageModifier.getId()) > 0){
                    destabilize();
                }
            }
        }
    }
}
