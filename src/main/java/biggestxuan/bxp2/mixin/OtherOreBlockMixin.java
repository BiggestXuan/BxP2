package biggestxuan.bxp2.mixin;

import net.allthemods.alltheores.blocks.OtherOreBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
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

@Mixin(value = OtherOreBlock.class,remap = false)
public class OtherOreBlockMixin {
    @Inject(method = "getDestroyProgress",at = @At("HEAD"),cancellable = true)
    public void _inject(BlockState state, Player player, BlockGetter getter, BlockPos blockPos, CallbackInfoReturnable<Float> cir){
        int i = ForgeHooks.isCorrectToolForDrops(state, player) ? 25 : 100;
        cir.setReturnValue(player.getDigSpeed(state, blockPos) / 2.0F / (float)i);
    }

    @Inject(method = "canHarvestBlock",at = @At("HEAD"),cancellable = true)
    public void __inject(BlockState state, BlockGetter world, BlockPos pos, Player player, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
