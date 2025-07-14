package biggestxuan.bxp2.blocks;

import biggestxuan.bxp2.blocks.TileEntity.ATMTileEntity;
import biggestxuan.bxp2.blocks.TileEntity.BxPTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

/**
 * @Author Biggest_Xuan
 * 2025/7/13
 */
public class ATMBlock extends BxPBlock implements EntityBlock {
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ATMTileEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            if (level.getBlockEntity(pos) instanceof ATMTileEntity atm) {
                NetworkHooks.openScreen(serverPlayer, atm, pos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == BxPTileEntities.ATM_MACHINE.get() ? ATMTileEntity::tick : null;
    }
}
