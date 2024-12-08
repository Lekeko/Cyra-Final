package com.keko.data;

import com.keko.CyraFinal;
import com.keko.blocks.ModBlocks;
import com.keko.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool seaPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SEA_CRYSTAL_BRICKS);

        blockStateModelGenerator.registerSimpleState(ModBlocks.CORE_OF_THE_SEA);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ENDERITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SUPPORTER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDERITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_CRYSTAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PYRITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PYRITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEP_SEA_LANTERN);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEP_LANTERN);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_MIRIANITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BIOLUMINESCENCE_LEAVES);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_MURIANITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PYRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_STONE_BRICK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.PRISMATIC_LEAVES);

        seaPool.stairs(ModBlocks.SEA_CRYSTAL_BRICKS_STAIRS);
        seaPool.slab(ModBlocks.SEA_CRYSTAL_BRICKS_SLAB);
        seaPool.wall(ModBlocks.SEA_CRYSTAL_BRICKS_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.SEA_CRYSTAL_BRICKS_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.SEA_CRYSTAL_BRICKS_TRAPDOOR);


    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        //HANDHELD

        itemModelGenerator.register(ModItems.ENDERITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDERITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDERITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDERITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDERITE_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SEA_CRYSTAL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SEA_WAND, Models.HANDHELD);

        itemModelGenerator.register(ModItems.PYRITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PYRITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PYRITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PYRITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PYRITE_SWORD, Models.HANDHELD);

        //MISC

        itemModelGenerator.register(ModItems.HEALING_FLASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_BOLT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUFF_FLASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_BOUND, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_DAHY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_FORCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_IMPETUOSITY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_VITALITY, Models.GENERATED);
        itemModelGenerator.register(ModItems.PYRITE_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHACKLE_OF_IMPRISONMENT, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RESTORED_PYRITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_NECKLACE, Models.GENERATED);


        //ARMOUR

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDERITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDERITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDERITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ENDERITE_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SEA_CRYSTAL_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SEA_CRYSTAL_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SEA_CRYSTAL_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SEA_CRYSTAL_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PYRITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PYRITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PYRITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PYRITE_BOOTS));
    }
}
