package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import biggestxuan.bxp2.utils.Utils;
import de.teamlapen.vampirism.client.gui.overlay.VampirismHUDOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGuiEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/7/4
 */

@Mixin(value = VampirismHUDOverlay.class,remap = false)
public class VampirismHUDOverlayMixin {
    @Inject(method = "onRenderGameOverlay",at = @At("HEAD"),cancellable = true)
    public void __inject(RenderGuiEvent.Pre event, CallbackInfo ci){
        Player player = Minecraft.getInstance().player;
        if(Utils.playerArmorHasTrait(player, BxPModifiers.SunShineProtect.get())){
            ci.cancel();
        }
    }
}
