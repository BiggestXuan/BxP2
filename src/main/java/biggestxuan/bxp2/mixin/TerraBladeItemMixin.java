package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.utils.WorldUtils;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;
import vazkii.botania.network.serverbound.LeftClickPacket;
import vazkii.botania.xplat.ClientXplatAbstractions;

/**
 * @Author Biggest_Xuan
 * 2025/5/6
 */

@Mixin(value = TerraBladeItem.class,remap = false)
public abstract class TerraBladeItemMixin {
    @Shadow
    public static ManaBurstEntity getBurst(Player player, ItemStack stack) {
        return null;
    }

    /**
     * @author Biggest_Xuan
     * @reason Support TConstruct
     */
    @Overwrite
    public static void trySpawnBurst(Player player, float attackStrength) {
        if (!player.isSpectator() && !player.getMainHandItem().isEmpty() && WorldUtils.isTerraWeapon(player.getMainHandItem()) && attackStrength == 1.0F) {
            ManaBurstEntity burst = getBurst(player, player.getMainHandItem());
            player.level().addFreshEntity(burst);
            player.getMainHandItem().hurtAndBreak(2, player, (p) -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

    }

    /**
     * @author Biggest_Xuan
     * @reason Support TConstruct
     */
    @Overwrite
    public static void leftClick(ItemStack stack) {
        if (!stack.isEmpty() && WorldUtils.isTerraWeapon(stack)) {
            ClientXplatAbstractions.INSTANCE.sendToServer(LeftClickPacket.INSTANCE);
        }

    }
}
