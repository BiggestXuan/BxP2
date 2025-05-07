package biggestxuan.bxp2.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wayoftime.bloodmagic.common.item.ItemDaggerOfSacrifice;

/**
 * @Author Biggest_Xuan
 * 2025/4/25
 */

@Mixin(ItemDaggerOfSacrifice.class)
public abstract class ItemDaggerOfSacrificeMixin extends Item {
    public ItemDaggerOfSacrificeMixin(Properties p_41383_) {
        super(p_41383_);
    }

    @Inject(method = "hurtEnemy",at = @At("HEAD"),cancellable = true)
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
