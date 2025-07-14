package biggestxuan.bxp2.blocks.TileEntity;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.BxPApi;
import biggestxuan.bxp2.container.ATMContainer;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.items.MoneyItem;
import biggestxuan.bxp2.network.PacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * @Author Biggest_Xuan
 * 2025/7/13
 */
public class ATMTileEntity extends BlockEntity implements MenuProvider {
    public ATMTileEntity(BlockPos pos, BlockState state) {
        super(BxPTileEntities.ATM_MACHINE.get(), pos, state);
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(27) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot,ItemStack stack) {
            return stack.getItem() == BxPItems.MONEY.get();
        }
    };

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr(
                    (double)this.worldPosition.getX() + 0.5D,
                    (double)this.worldPosition.getY() + 0.5D,
                    (double)this.worldPosition.getZ() + 0.5D
            ) <= 64.0D;
        }
    }

    public ItemStackHandler getItemHandler(){
        return itemHandler;
    }

    private final LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.of(() -> itemHandler);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Nullable
    public Player getPlayer(Level level){
        if(ownerId == null){
            return null;
        }
        if(level.isClientSide){
            return level.getPlayerByUUID(this.ownerId);
        }
        ServerLevel sl = (ServerLevel) level;
        MinecraftServer server = sl.getServer();
        return server.getPlayerList().getPlayer(this.ownerId);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inventory", itemHandler.serializeNBT());
        if (ownerId != null) {
            tag.putUUID("Owner", ownerId);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        if (tag.hasUUID("Owner")) {
            ownerId = tag.getUUID("Owner");
        }
    }

    public UUID ownerId;

    @Override
    public Component getDisplayName() {
        return BxP2.tr("block.bxp2.atm");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ATMContainer(i, inventory, this);
    }

    public void storeMoney(Player player){
        if(level == null || level.isClientSide) return;
        float total = 0;
        for (int i = 0; i < 27; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if(!stack.isEmpty() && stack.getItem().equals(BxPItems.MONEY.get())){
                total += MoneyItem.getMoney(stack) * stack.getCount();
                itemHandler.setStackInSlot(i,ItemStack.EMPTY);
            }
        }
        if(total > 0){
            BxPApi.getPlayerCap(player).setMoney(BxPApi.getPlayerCap(player).getMoney() + total);
            if(player instanceof ServerPlayer sp){
                PacketHandler.syncPlayerCapability(sp);
            }
        }
    }

    public void withdrawMoney(Player player,float amt){
        if(level == null || level.isClientSide) return;
        try{
            var cap = BxPApi.getPlayerCap(player);
            if(cap.getPhase() >= 0){
                if(cap.getMoney() >= amt){
                    cap.setMoney(cap.getMoney() - amt);
                    MoneyItem.dropMoney(player,amt,true);
                }
            }
        }catch (Exception e){
            BxP2.LOGGER.debug("玩家取款时出现一些问题！");
            BxP2.LOGGER.debug(e.toString());
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity be){
        if(be instanceof ATMTileEntity tile){
            if(level == null || level.isClientSide) return;
            if(tile.ownerId == null) return;
            Player player = tile.getPlayer(level);
            if(player != null){
                tile.storeMoney(player);
            }
        }
    }
}
