package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.api.BxPApi;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.utils.BXUtils;
import moze_intel.projecte.events.ToolTipEvent;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @Author Biggest_Xuan
 * 2025/5/31
 */

@Mixin(value = ToolTipEvent.class,remap = false)
public class ToolTipEventMixin {
    @Redirect(method = "tTipEvent",at = @At(value = "INVOKE", target = "Lmoze_intel/projecte/utils/EMCHelper;getEmcValue(Lnet/minecraft/world/item/ItemStack;)J"))
    private static long __redirect(ItemStack stack){
        Player player = Minecraft.getInstance().player;
        if(player != null){
            if(BXUtils.canUseProjecte(player)){
                return EMCHelper.getEmcValue(stack);
            }
            return 0L;
        }
        return EMCHelper.getEmcValue(stack);
    }
}
