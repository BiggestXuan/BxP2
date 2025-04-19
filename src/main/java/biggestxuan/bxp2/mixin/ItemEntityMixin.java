package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.items.BxPItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/4/18
 */

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    public ItemEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow public abstract ItemStack getItem();

    @Inject(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V"))
    public void _inject(CallbackInfo ci){
        if(getItem().getItem().equals(BxPItems.POTASSIUM.get()) && isInWater()){
            Level world = level();
            if(!world.isClientSide){
                float level = 10 + 0.1F * this.getItem().getCount();
                world.explode(null,getX(),getY(),getZ(),level, Level.ExplosionInteraction.MOB);
                remove(RemovalReason.KILLED);
            }
        }
    }
}
