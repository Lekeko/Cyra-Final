package com.keko.screen;

import com.keko.blocks.environment.AlchemyTableEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class AlchemyTableScreenhandler extends ScreenHandler {

    private final Inventory inventory;
    public final AlchemyTableEntity alchemyTableEntity;

    public AlchemyTableScreenhandler (int syncID, PlayerInventory playerInventory, BlockPos buf){
        this(syncID, playerInventory, playerInventory.player.getWorld().getBlockEntity(buf));
    }


    public AlchemyTableScreenhandler(int syncID, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlers.ALCHEMY_TABLE_SCREENHANDLER_SCREEN_HANDLER_TYPE, syncID);
        checkSize((Inventory) blockEntity, 3);
        this.inventory = ((Inventory) blockEntity);
        this.alchemyTableEntity = ((AlchemyTableEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0 , 26, 10));
        this.addSlot(new Slot(inventory, 1 , 26, 59));
        this.addSlot(new Slot(inventory, 2 , 134, 34));



        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

    }



    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }



    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
