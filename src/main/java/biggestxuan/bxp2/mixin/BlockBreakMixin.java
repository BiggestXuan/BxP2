package biggestxuan.bxp2.mixin;

import com.thevortex.allthemodium.events.BlockBreak;
import net.minecraftforge.event.level.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/7/6
 */

@Mixin(value = BlockBreak.class,remap = false)
public class BlockBreakMixin {
    @Inject(method = "on(Lnet/minecraftforge/event/level/BlockEvent$BreakEvent;)V",at = @At("HEAD"),cancellable = true)
    private static void __inject(BlockEvent.BreakEvent event, CallbackInfo ci){
        ci.cancel();
    }

    @Inject(method = "on(Lnet/minecraftforge/event/level/BlockEvent$BreakEvent;)V",at = @At("TAIL"),cancellable = true)
    private static void _inject(BlockEvent.BreakEvent event, CallbackInfo ci){
        event.setCanceled(false);
        ci.cancel();
    }
}
