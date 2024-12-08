package com.keko.blocks.blocksWithInterface;

import com.keko.blocks.ImplementedInventory;
import com.keko.blocks.ModBlockEntity;
import com.keko.items.ModItems;
import com.keko.particle.ModParticles;
import com.keko.screen.pyriteFabricatorScreen.PyriteFabricatorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
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

import java.util.ArrayList;
import java.util.HashMap;

public class PyriteFabricatorEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    HashMap<Item, Item> list = new HashMap<>();
    Item crystalItem;
    int progress = 0;

    public static boolean wantsToCraft = false;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;

    HashMap<Item, StatusEffectInstance> orb = new HashMap<Item, StatusEffectInstance>();


    protected final PropertyDelegate propertyDelegate;
    private StatusEffectInstance effect1;
    private StatusEffectInstance effect2;

    public PyriteFabricatorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.PYRITE_FABRICATOR_BLOCK_ENTITY_TYPE, pos, state);
        list.put(ModItems.SEA_CRYSTAL_AXE, ModItems.PYRITE_AXE);
        list.put(ModItems.SEA_CRYSTAL_SHOVEL, ModItems.PYRITE_SHOVEL);
        list.put(ModItems.SEA_CRYSTAL_PICKAXE, ModItems.PYRITE_PICKAXE);
        list.put(ModItems.SEA_CRYSTAL_SWORD, ModItems.PYRITE_SWORD);
        list.put(ModItems.SEA_CRYSTAL_HOE, ModItems.PYRITE_HOE);

        list.put(ModItems.SEA_CRYSTAL_HELMET, ModItems.PYRITE_HELMET);
        list.put(ModItems.SEA_CRYSTAL_CHESTPLATE, ModItems.PYRITE_CHESTPLATE);
        list.put(ModItems.SEA_CRYSTAL_LEGGINGS, ModItems.PYRITE_LEGGINGS);
        list.put(ModItems.SEA_CRYSTAL_BOOTS, ModItems.PYRITE_BOOTS);

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
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public Text getDisplayName() {
        return Text.literal("Pyrite Fabricator");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PyriteFabricatorScreenHandler(syncId, playerInventory, this.pos);
    }

    public void tick(World world1, BlockPos pos, BlockState state1) {


        if (checkIfSlotsHaveRequiredRecipe()){
            crystalItem = this.getStack(INPUT_SLOT_1).getItem();
            if (world1.isClient){
                summonParticles();
            }else
                beginTransformation();
        }else  progress = 0;
    }

    private void summonParticles() {
        assert world != null;
        float x = this.getPos().getX() + 0.5f + world.random.nextFloat() * 5 - 2.5f;
        float y = this.getPos().getY() + 0.5f + world.random.nextFloat() * 5 - 2.5f;
        float z = this.getPos().getZ() + 0.5f + world.random.nextFloat() * 5 - 2.5f;

        world.addParticle(ModParticles.PYRITE_STAR, x, y - 1, z, - x + this.getPos().getX() + 0.5f  ,- y + this.getPos().getY() - 1 ,- z + this.getPos().getZ() + 0.5f);

    }

    private void beginTransformation() {
        progress++;
        if (progress > 100){

            this.setStack(INPUT_SLOT_3, list.get(this.getStack(INPUT_SLOT_1).getItem()).getDefaultStack());
            this.setStack(INPUT_SLOT_1, ItemStack.EMPTY);
            this.getStack(INPUT_SLOT_2).decrement(1);
        }
    }

    private boolean checkIfSlotsHaveRequiredRecipe() {

        return list.containsKey(this.getStack(INPUT_SLOT_1).getItem()) && this.getStack(INPUT_SLOT_2).getItem() == ModItems.RESTORED_PYRITE && this.getStack(INPUT_SLOT_3).isOf(ItemStack.EMPTY.getItem());

    }

}
