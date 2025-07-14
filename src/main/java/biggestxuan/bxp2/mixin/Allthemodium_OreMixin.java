package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import com.thevortex.allthemodium.blocks.Allthemodium_Ore;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RedStoneOreBlock;
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

@Mixin(value = Allthemodium_Ore.class,remap = false)
public abstract class Allthemodium_OreMixin extends RedStoneOreBlock {
    public Allthemodium_OreMixin(Properties p_55453_) {
        super(p_55453_);
    }

    @Inject(method = "getDestroyProgress",at = @At("HEAD"),cancellable = true)
    public void _inject(BlockState state, Player player, BlockGetter getter, BlockPos blockPos, CallbackInfoReturnable<Float> cir){
        int i = ForgeHooks.isCorrectToolForDrops(state, player) ? 250 : 1500;
        cir.setReturnValue(player.getDigSpeed(state, blockPos) / 2.0F / (float)i);
    }

    @Inject(method = "canEntityDestroy",at = @At("HEAD"),cancellable = true)
    public void __inject(BlockState state, BlockGetter world, BlockPos pos, Entity player, CallbackInfoReturnable<Boolean> cir){
        BxP2.LOGGER.info("aaa");
        cir.setReturnValue(true);
    }
}
