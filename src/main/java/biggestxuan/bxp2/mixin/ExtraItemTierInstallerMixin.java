package biggestxuan.bxp2.mixin;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.item.ExtraItemTierInstaller;
import mekanism.api.tier.BaseTier;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeUpgradeable;
import mekanism.common.registries.MekanismItems;
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
 * 2025/4/21
 */

@Mixin(ExtraItemTierInstaller.class)
public class ExtraItemTierInstallerMixin {

    @Shadow(remap = false) @Final private @Nullable AdvancedTier fromTier;

    @Shadow(remap = false) @Final private @NotNull AdvancedTier toTier;

    /**
     * @author Biggest_Xuan
     * @reason Only BxP2
     */
    @Overwrite
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        ExtraAttributeUpgradeable upgradeableBlock = (ExtraAttributeUpgradeable) Attribute.get(block, ExtraAttributeUpgradeable.class);
        if (!world.isClientSide && player != null) {
            if (upgradeableBlock != null) {
                BaseTier mekTier = Attribute.getBaseTier(block);
                AdvancedTier baseTier = ExtraAttribute.getAdvanceTier(block);
                if (canUse(baseTier,toTier,mekTier) && baseTier != this.toTier) {
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
            }else{
                MekanismItems.ULTIMATE_TIER_INSTALLER.asItem().useOn(context);
            }
        }
        return InteractionResult.PASS;
    }

    @Unique
    private static boolean canUse(AdvancedTier base,AdvancedTier to,BaseTier mekTier){
        if(mekTier != BaseTier.ULTIMATE){
            return true;
        }
        if(to == AdvancedTier.INFINITE) return true;
        if(to == AdvancedTier.COSMIC){
            return base != AdvancedTier.SUPREME && base != AdvancedTier.COSMIC;
        }
        if(to == AdvancedTier.SUPREME){
            return base == null || base == AdvancedTier.ABSOLUTE;
        }
        return base == null;
    }
}
