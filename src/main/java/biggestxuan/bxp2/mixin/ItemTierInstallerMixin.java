package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import mekanism.api.tier.BaseTier;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeUpgradeable;
import mekanism.common.item.ItemTierInstaller;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.interfaces.ITierUpgradable;
import mekanism.common.tile.interfaces.ITileDirectional;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

/**
 * @Author Biggest_Xuan
 * 2025/4/20
 */

@Mixin(ItemTierInstaller.class)
public class ItemTierInstallerMixin {

    @Shadow(remap = false) @Final private @Nullable BaseTier fromTier;

    @Shadow(remap = false) @Final private @NotNull BaseTier toTier;

    /**
     * @author Biggest_Xuan
     * @reason Pass Upgrade
     */
    @Overwrite
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (!world.isClientSide && player != null) {
            BlockPos pos = context.getClickedPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            AttributeUpgradeable upgradeableBlock = (AttributeUpgradeable)Attribute.get(block, AttributeUpgradeable.class);
            if (upgradeableBlock != null) {
                BaseTier baseTier = Attribute.getBaseTier(block);
                if (canUse(baseTier,toTier) && baseTier != this.toTier) {
                    BlockState upgradeState = upgradeableBlock.upgradeResult(state, this.toTier);
                    if (state == upgradeState) {
                        return InteractionResult.PASS;
                    }

                    BlockEntity tile = WorldUtils.getTileEntity(world, pos);
                    if (tile instanceof ITierUpgradable) {
                        ITierUpgradable tierUpgradable = (ITierUpgradable)tile;
                        if (tile instanceof TileEntityMekanism) {
                            TileEntityMekanism tileMek = (TileEntityMekanism)tile;
                            if (!tileMek.playersUsing.isEmpty()) {
                                return InteractionResult.FAIL;
                            }
                        }

                        IUpgradeData upgradeData = tierUpgradable.getUpgradeData();
                        if (upgradeData != null) {
                            world.setBlockAndUpdate(pos, upgradeState);
                            TileEntityMekanism upgradedTile = (TileEntityMekanism)WorldUtils.getTileEntity(TileEntityMekanism.class, world, pos);
                            if (upgradedTile == null) {
                                Mekanism.logger.warn("Error upgrading block at position: {} in {}.", pos, world);
                                return InteractionResult.FAIL;
                            }

                            if (tile instanceof ITileDirectional) {
                                ITileDirectional directional = (ITileDirectional)tile;
                                if (directional.isDirectional()) {
                                    upgradedTile.setFacing(directional.getDirection());
                                }
                            }

                            upgradedTile.parseUpgradeData(upgradeData);
                            upgradedTile.sendUpdatePacket();
                            upgradedTile.setChanged();
                            if (!player.isCreative()) {
                                //context.getItemInHand().shrink(1);
                            }

                            return InteractionResult.sidedSuccess(world.isClientSide);
                        }

                        if (tierUpgradable.canBeUpgraded()) {
                            Mekanism.logger.warn("Got no upgrade data for block {} at position: {} in {} but it said it would be able to provide some.", new Object[]{block, pos, world});
                            return InteractionResult.FAIL;
                        }
                    }
                }
            }

            return InteractionResult.PASS;
        } else {
            return InteractionResult.PASS;
        }
    }

    @Unique
    private static boolean canUse(BaseTier base,BaseTier to){
        if(to == BaseTier.ULTIMATE) return true;
        if(to == BaseTier.ELITE){
            return base != BaseTier.ULTIMATE && base != BaseTier.ELITE;
        }
        if(to == BaseTier.ADVANCED){
            return base == null || base == BaseTier.BASIC;
        }
        return base == null;
    }
}
