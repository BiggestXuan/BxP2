package biggestxuan.bxp2.mixin;

import com.thevortex.allthemodium.blocks.Unobtainium_Ore;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/7/6
 */

@Mixin(value = Unobtainium_Ore.class,remap = false)
public abstract class Unobtainium_OreMixin extends DropExperienceBlock {
    public Unobtainium_OreMixin(Properties p_221081_) {
        super(p_221081_);
    }

    @Inject(method = "getDestroyProgress",at = @At("HEAD"),cancellable = true)
    public void _inject(BlockState state, Player player, BlockGetter getter, BlockPos blockPos, CallbackInfoReturnable<Float> cir){
        int i = ForgeHooks.isCorrectToolForDrops(state, player) ? 250 : 1500;
        cir.setReturnValue(player.getDigSpeed(state, blockPos) / 2.0F / (float)i);
    }

    @Inject(method = "canEntityDestroy",at = @At("HEAD"),cancellable = true)
    public void __inject(BlockState state, BlockGetter world, BlockPos pos, Entity player, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
