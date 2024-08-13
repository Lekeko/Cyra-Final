package com.keko.blocks.environment;

import com.keko.blocks.ImplementedInventory;
import com.keko.blocks.ModBlockEntity;
import com.keko.helpers.Directional;
import com.keko.items.ModItems;
import com.keko.screen.AlchemyTableScreenhandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlchemyTableEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;

     ArrayList<Item> orbs = new ArrayList<>() {{
                 add(ModItems.ORB_OF_IMPETOUSITY);
                 add(ModItems.ORB_OF_DAHY);
                 add(ModItems.ORB_OF_BOUND);
                 add(ModItems.ORB_OF_VITALITY);
                 add(ModItems.ORB_OF_FORCE);
     }};


    private static boolean canCraft = false;

    protected final PropertyDelegate propertyDelegate;



    public AlchemyTableEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.ALCHEMY_TABLE_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return 0;
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity player) {
        return new BlockPos(this.getPos());
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Alchemy");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlchemyTableScreenhandler(syncId, playerInventory, this.pos);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void tick(World world1, BlockPos pos, BlockState state1) {
        if (world1.isClient){
            return;
        }

        if (outputHaveBuffPotion()){
            if (firstSlotHasOrb() && secondSlotHasOrb()){
                canCraft = true;
            }
        }
    }

    private boolean secondSlotHasOrb() {
        return orbs.contains(this.getStack(INPUT_SLOT_1).getItem());

    }

    private boolean firstSlotHasOrb() {
        return orbs.contains(this.getStack(INPUT_SLOT_2).getItem());
    }


    private boolean outputHaveBuffPotion() {
        return this.getStack(INPUT_SLOT_3).getItem() == ModItems.BUFF_FLASK;
    }
}