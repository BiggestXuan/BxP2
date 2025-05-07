package biggestxuan.bxp2.mixin;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import owmii.powah.block.reactor.ReactorBlock;
import owmii.powah.item.ReactorItem;
import thetadev.constructionwand.wand.WandJob;
import thetadev.constructionwand.wand.undo.ISnapshot;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/27
 */

@Mixin(value = WandJob.class,remap = false)
public class WandJobMixin {
    @Shadow private List<ISnapshot> placeSnapshots;

    @Inject(method = "getTargetItem",at = @At("HEAD"),cancellable = true)
    private static void __inject(Level world, BlockHitResult rayTraceResult, CallbackInfoReturnable<BlockItem> cir){
        Item tgitem = world.getBlockState(rayTraceResult.getBlockPos()).getBlock().asItem();
        if(tgitem instanceof ReactorItem){
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "doIt",at = @At("HEAD"),cancellable = true)
    public void __inject(CallbackInfoReturnable<Boolean> cir){
        for(ISnapshot snapshot : this.placeSnapshots) {
            if(snapshot.getBlockState().getBlock() instanceof ReactorBlock){
                cir.setReturnValue(false);
            }
        }
    }
}
