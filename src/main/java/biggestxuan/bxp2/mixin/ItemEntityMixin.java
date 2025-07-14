package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.fluids.BxPFluids;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.utils.WorldUtils;
import com.fxd927.mekanismscience.common.registries.MSFluids;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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
                world.setBlock(getOnPos(), MSFluids.POTASSIUM_HYDROXIDE.getBlock().defaultBlockState(),1);
                remove(RemovalReason.KILLED);
            }
        }
        if(getItem().getItem().equals(Items.GOLD_INGOT)){
            CompoundTag tag = getPersistentData();
            int nowTime = tag.getInt("bx_gold_time");
            tag.putInt("bx_gold_time",nowTime+1);
            level().addParticle(ParticleTypes.PORTAL,
                    getX(),
                    getY() - 0.1,
                    getZ(),
                    (random.nextDouble() - 0.5) * 0.1,
                    random.nextDouble() * 0.5,
                    (random.nextDouble() - 0.5) * 0.1
            );
            if(nowTime >= 100){
                int count = getItem().getCount();
                WorldUtils.replaceItemEntityStack((ItemEntity) (Object) this,new ItemStack(BxPItems.BX_GOLD_INGOT.get(),count));
            }
        }
    }
}
