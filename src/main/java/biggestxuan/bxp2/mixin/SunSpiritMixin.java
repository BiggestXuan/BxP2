package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.Config;
import com.aetherteam.aether.entity.monster.dungeon.boss.SunSpirit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/4/11
 */

@Mixin(SunSpirit.class)
public abstract class SunSpiritMixin extends PathfinderMob {

    protected SunSpiritMixin(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Shadow(remap = false)
    public abstract void setFrozen(boolean frozen);

    @Shadow(remap = false)
    public abstract void setFrozenDuration(int duration);

    @Inject(method = "tick",at = @At("HEAD"))
    public void __tick(CallbackInfo ci){
        if(Config.difficulty == 1){
            setFrozen(true);
            setFrozenDuration(99999);
        }
    }

    @Redirect(method = "hurt",at = @At(value = "INVOKE", target = "Lcom/aetherteam/aether/entity/monster/dungeon/boss/SunSpirit;setFrozenDuration(I)V",remap = false))
    public void __inject(SunSpirit instance, int duration){
        if(Config.difficulty == 2){
            setFrozen(true);
            setFrozenDuration(99999);
        }
    }
}
