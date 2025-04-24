package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.utils.WorldUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/20
 */

@Mixin(WitherBoss.class)
public abstract class WitherMixin extends Monster {
    protected WitherMixin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    @Override
    public void die(DamageSource p_21014_) {
        super.die(p_21014_);
        if(!level().isClientSide){
            if(p_21014_.getDirectEntity() instanceof Player player){
                player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(IBxPCapability::addWitherCount);
            }
            //List<Player> list = WorldUtils.getNearPlayer(this,64);
            //list.forEach(player -> player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(IBxPCapability::addWitherCount));
        }
    }

    @Inject(method = "dropCustomDeathLoot",at = @At("RETURN"))
    public void __inject(DamageSource p_31464_, int p_31465_, boolean p_31466_, CallbackInfo ci){
        if(!level().isClientSide){
            List<Player> list = WorldUtils.getNearPlayer(this,64);
            int total = 0;
            for(Player player : list){
                if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
                    IBxPCapability cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
                    total += cap.getWitherCount();
                }
            }
            total = Math.round(total * 1.5F);
            ItemStack stack = BxP2.getStack("mysticalagriculture:nether_star_essence");

            int count = total * 3;
            if(count <= 64){
                stack.setCount(count);
                ItemEntity entity = new ItemEntity(level(),getX(),getY(),getZ(),stack.copy());
                level().addFreshEntity(entity);
            }
            while (count > 0){
                stack.setCount(Math.min(count,64));
                ItemEntity entity = new ItemEntity(level(),getX(),getY(),getZ(),stack.copy());
                level().addFreshEntity(entity);
                count -= 64;
            }
        }
    }

    @Inject(method = "customServerAiStep",at = @At("HEAD"))
    public void _inject(CallbackInfo ci){
        CompoundTag tag = getPersistentData();
        if(tag.getBoolean("bxp_init")){
            return;
        }
        Level level = level();
        float base = super.getMaxHealth();
        List<Player> list = WorldUtils.getNearPlayer(this,64);
        for(Player player : list){
            if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
                IBxPCapability cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
                float grow = Config.difficulty == 3 ? 37.5f : Config.difficulty == 2 ? 20f : 0f;
                base += cap.getWitherCount() * grow;
            }
        }
        float day = 1F * level.getDayTime() / 24000;
        if(Config.difficulty == 3) day *= 2.5F;
        float health = base * (1 + 0.01F * day);
        AttributeInstance maxHealth = getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.setBaseValue(health);
            tag.putBoolean("bxp_init",true);
        }
    }

    @Redirect(method = "customServerAiStep",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/wither/WitherBoss;heal(F)V",ordinal = 0))
    public void __redirect(WitherBoss instance, float v){
        instance.heal(instance.getMaxHealth() * 0.1F);
    }

    @Redirect(method = "customServerAiStep",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/wither/WitherBoss;heal(F)V",ordinal = 1))
    public void _redirect(WitherBoss instance, float v){
        float rate = Config.difficulty == 3 ? 0.005F : Config.difficulty == 2 ? 0.003F : 0;
        instance.heal(this.getMaxHealth() * rate);
    }
}
