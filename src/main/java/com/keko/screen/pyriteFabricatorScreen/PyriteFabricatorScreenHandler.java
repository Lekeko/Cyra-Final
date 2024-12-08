package com.keko.screen.pyriteFabricatorScreen;

import com.keko.blocks.blocksWithInterface.AlchemyTableEntity;
import com.keko.blocks.blocksWithInterface.PyriteFabricatorEntity;
import com.keko.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class PyriteFabricatorScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    public final PyriteFabricatorEntity pyriteFabricatorEntity;

    public PyriteFabricatorScreenHandler(int syncID, PlayerInventory playerInventory, BlockPos buf) {
        this(syncID, playerInventory, playerInventory.player.getWorld().getBlockEntity(buf));
    }

    public PyriteFabricatorScreenHandler(int syncID, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlers.PYRITE_FABRICATOR_SCREENHANDLER_SCREEN_HANDLER_TYPE, syncID);
        checkSize((Inventory) blockEntity, 3);
        this.inventory = ((Inventory) blockEntity);
        this.pyriteFabricatorEntity = ((PyriteFabricatorEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0 , 26, 48));
        this.addSlot(new Slot(inventory, 1 , 134, 48));
        this.addSlot(new Slot(inventory, 2 , 80, 30));



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


    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
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
}
