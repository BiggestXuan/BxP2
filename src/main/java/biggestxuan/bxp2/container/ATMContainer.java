package biggestxuan.bxp2.container;

import biggestxuan.bxp2.blocks.TileEntity.ATMTileEntity;
import biggestxuan.bxp2.items.BxPItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

/**
 * @Author Biggest_Xuan
 * 2025/7/13
 */
public class ATMContainer extends AbstractContainerMenu{
    private final ATMTileEntity tileEntity;
    private final ContainerData data;

    public ATMContainer(int windowId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(windowId,playerInventory,(ATMTileEntity) playerInventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public ATMContainer(int id, Inventory inv, ATMTileEntity te) {
        super(BxPContainers.ATM_CONTAINER.get(), id);
        this.tileEntity = te;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new SlotItemHandler(tileEntity.getItemHandler(), col + row * 9, 8 + col * 18, 28 + row * 18) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return stack.getItem().equals(BxPItems.MONEY.get());
                    }
                });
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 142 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inv, col, 8 + col * 18, 200));
        }
        this.data = new SimpleContainerData(1);
        addDataSlots(data);
    }

    public void handleButtonClick(int buttonId, Player player) {
        if (buttonId == 0) {
            tileEntity.storeMoney(player);
        } else if (buttonId == 1) {
            float amount = data.get(0) / 100f;
            tileEntity.withdrawMoney(player, amount);
        }
    }

    public ATMTileEntity getTile(){
        return tileEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (i >= 27 && i < 63) {
                if (itemstack1.getItem() == BxPItems.MONEY.get()) {
                    if (!this.moveItemStackTo(itemstack1, 0, 27, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return tileEntity.stillValid(player);
    }
}
