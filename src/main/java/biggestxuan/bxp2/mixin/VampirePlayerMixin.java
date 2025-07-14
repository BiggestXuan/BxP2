package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import biggestxuan.bxp2.utils.Utils;
import de.teamlapen.vampirism.api.entity.player.vampire.IVampirePlayer;
import de.teamlapen.vampirism.entity.player.FactionBasePlayer;
import de.teamlapen.vampirism.entity.player.vampire.VampirePlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/7/4
 */

@Mixin(value = VampirePlayer.class,remap = false)
public abstract class VampirePlayerMixin extends FactionBasePlayer<IVampirePlayer> implements IVampirePlayer{
    public VampirePlayerMixin(Player player) {
        super(player);
    }

    @Inject(method = "isIgnoringSundamage",at = @At("HEAD"),cancellable = true)
    public void __inject(CallbackInfoReturnable<Boolean> cir){
        Player player = this.player;
        if(Utils.playerArmorHasTrait(player, BxPModifiers.SunShineProtect.get())){
            cir.setReturnValue(true);
        }
    }
}
