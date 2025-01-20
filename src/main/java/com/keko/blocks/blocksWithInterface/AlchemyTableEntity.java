package com.keko.blocks.blocksWithInterface;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.blocks.ImplementedInventory;
import com.keko.blocks.ModBlockEntity;
import com.keko.items.ModItems;
import com.keko.items.tools.BuffFlask;
import com.keko.screen.alchemyTableScreen.AlchemyTableScreenhandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class AlchemyTableEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    public static boolean wantsToCraft = false;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;

     HashMap<Item, Integer> orb = new HashMap<>();
    private int time = 0;

    public static boolean canCraft = false;

    protected final PropertyDelegate propertyDelegate;
    private int effect1;
    private int effect2;


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
            orb.put(ModItems.ORB_OF_IMPETUOSITY, 1);
            orb.put(ModItems.ORB_OF_DAHY,2);
            orb.put(ModItems.ORB_OF_BOUND, 3);
            orb.put(ModItems.ORB_OF_VITALITY, 4);
            orb.put(ModItems.ORB_OF_FORCE, 5);

            if (firstSlotHasOrb() && secondSlotHasOrb()){
                time++;

                if (time > 100){
                    this.getStack(INPUT_SLOT_3).set(ModDataComponentTypes.EFFECT_BUFF_1, effect1);
                    this.getStack(INPUT_SLOT_3).set(ModDataComponentTypes.EFFECT_BUFF_2, effect2);
                    wantsToCraft = false;
                    this.markDirty();
                }
            }else time = 0;
        }
    }

    private boolean secondSlotHasOrb() {
        if (orb.containsKey(this.getStack(INPUT_SLOT_1).getItem())){
            effect1 = orb.get(this.getStack(INPUT_SLOT_1).getItem());
            return true;
        }
        return false;

    }

    private boolean firstSlotHasOrb() {
        if (orb.containsKey(this.getStack(INPUT_SLOT_2).getItem())){
            effect2 = orb.get(this.getStack(INPUT_SLOT_2).getItem());
            return true;
        }
        return false;
    }


    private boolean outputHaveBuffPotion() {
        return this.getStack(INPUT_SLOT_3).getItem() == ModItems.BUFF_FLASK;
    }
}