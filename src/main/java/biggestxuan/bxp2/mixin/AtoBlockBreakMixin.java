package biggestxuan.bxp2.mixin;

import net.allthemods.alltheores.events.BlockBreak;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/7/6
 */

@Mixin(value = BlockBreak.class,remap = false)
public class AtoBlockBreakMixin {
    @Inject(method = "BreakEvent",at = @At("HEAD"),cancellable = true)
    private static void BreakEvent(BlockEvent.BreakEvent event, CallbackInfo ci) {
        ci.cancel();
    }
}
