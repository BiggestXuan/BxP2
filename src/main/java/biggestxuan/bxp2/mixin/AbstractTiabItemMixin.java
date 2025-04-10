package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.utils.Utils;
import com.haoict.tiab.common.items.AbstractTiabItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */

@Mixin(AbstractTiabItem.class)
public class AbstractTiabItemMixin {

    @Inject(method = "useOn",at = @At("HEAD"),cancellable = true)
    public void __inject(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir){
        Level level = context.getLevel();
        if(!level.isClientSide && Config.difficulty > 1){
            Player player = context.getPlayer();
            BlockPos pos = context.getClickedPos();
            BlockState blockState = level.getBlockState(pos);
            if(blockState.getBlock().equals(BxP2.getBlock("mbd2:bx_furnace")) && player != null && !player.isCreative()){
                cir.setReturnValue(InteractionResult.PASS);
                Utils.sendMessage((ServerPlayer) player,"bxp2.message.tib_pass");
            }
        }
    }
}
