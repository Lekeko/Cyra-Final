package com.keko.data;

import com.keko.CyraFinal;
import com.keko.blocks.ModBlocks;
import com.keko.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool seaPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SEA_CRYSTAL_BRICKS);
        BlockStateModelGenerator.BlockTexturePool pyritePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PYRITE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool bioluminescencePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BIOLUMINESCENCE_PLANKS);
        BlockStateModelGenerator.BlockTexturePool dazedBioluminescencePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS);

        blockStateModelGenerator.registerSimpleState(ModBlocks.CORE_OF_THE_SEA);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ENDERITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VOID_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DAZED_SEA_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SUPPORTER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENDERITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_CRYSTAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PYRITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEP_SEA_LANTERN);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEP_LANTERN);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROSE_CRYSTAL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.KYANITE_CRYSTAL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_MIRIANITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BIOLUMINESCENCE_LEAVES);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DAZED_LEAVES);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_MURIANITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PYRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VOID_PYRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ANCIENT_PYRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SEA_STONE_BRICK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_EYE_PYRITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_PYRITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_PYRITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TILE_PYRITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VOID_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_VOID_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_VOID_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PILLAR_PYRITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_PYRITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TILES_PYRITE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PYRITE_BLOCK_LAMP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DAZED_SEA_STONE_BRICK);

        seaPool.stairs(ModBlocks.SEA_CRYSTAL_BRICKS_STAIRS);
        seaPool.slab(ModBlocks.SEA_CRYSTAL_BRICKS_SLAB);
        seaPool.wall(ModBlocks.SEA_CRYSTAL_BRICKS_WALL);

        pyritePool.stairs(ModBlocks.PYRITE_BRICK_STAIR);
        pyritePool.slab(ModBlocks.PYRITE_BRICK_SLAB);
        pyritePool.wall(ModBlocks.PYRITE_BRICK_WALLS);


        bioluminescencePool.stairs(ModBlocks.BIOLUMINESCENCE_STAIRS);
        bioluminescencePool.slab(ModBlocks.BIOLUMINESCENCE_SLAB);
        bioluminescencePool.family(new BlockFamily.Builder(ModBlocks.BIOLUMINESCENCE_PLANKS)
                .fence(ModBlocks.BIOLUMINESCENCE_FENCE)
                .trapdoor(ModBlocks.BIOLUMINESCENCE_TRAPDOOR)
                .door(ModBlocks.BIOLUMINESCENCE_DOOR)
                .fenceGate(ModBlocks.BIOLUMINESCENCE_FENCE_GATE)
                .pressurePlate(ModBlocks.BIOLUMINESCENCE_PRESSURE_PLATE)
                .button(ModBlocks.BIOLUMINESCENCE_BUTTON)
                .build());

        dazedBioluminescencePool.stairs(ModBlocks.DAZED_BIOLUMINESCENCE_STAIRS);
        dazedBioluminescencePool.slab(ModBlocks.DAZED_BIOLUMINESCENCE_SLAB);
        dazedBioluminescencePool.family(new BlockFamily.Builder(ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .fence(ModBlocks.DAZED_BIOLUMINESCENCE_FENCE)
                .trapdoor(ModBlocks.DAZED_BIOLUMINESCENCE_TRAPDOOR)
                .door(ModBlocks.DAZED_BIOLUMINESCENCE_DOOR)
                .fenceGate(ModBlocks.DAZED_BIOLUMINESCENCE_FENCE_GATE)
                .pressurePlate(ModBlocks.DAZED_BIOLUMINESCENCE_PRESSURE_PLATE)
                .button(ModBlocks.DAZED_BIOLUMINESCENCE_BUTTON)
                .build());


        blockStateModelGenerator.registerDoor(ModBlocks.SEA_CRYSTAL_BRICKS_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.SEA_CRYSTAL_BRICKS_TRAPDOOR);

        blockStateModelGenerator.registerDoor(ModBlocks.PYRITE_BRICK_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.PYRITE_BRICK_TRAPDOOR);


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
        itemModelGenerator.register(ModItems.PYRITE_PICKAXE, Models.HANDHELD);

        //MISC

        itemModelGenerator.register(ModItems.HEALING_FLASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_BOLT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_OXYGEN_TANK, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_CRYSTAL_OXYGEN_TANK, Models.GENERATED);
        itemModelGenerator.register(ModItems.PYRITE_OXYGEN_TANK, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUFF_FLASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_BOUND, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_DAHY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_FORCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_IMPETUOSITY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_ZEPHYR, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRANGE_LOOKING_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BROKEN_PYRITE_PRIMORDIAL_CUBE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORB_OF_VITALITY, Models.GENERATED);
        itemModelGenerator.register(ModItems.JELLY_PASTA, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEPTH_CHARM_TIER_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEPTH_CHARM_TIER_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEPTH_CHARM_TIER_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.PYRITE_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ModItems.OLD_LORDS_FLAME, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHACKLE_OF_IMPRISONMENT, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OLD_LORDS_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RESTORED_PYRITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_NECKLACE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GILDED_SPINE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANCIENT_TIT_FER, Models.GENERATED);
        itemModelGenerator.register(ModItems.JELLY_CROWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.JELLY_TENTACLES, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_RODENT_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEEP_SALMON_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRIDELY_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GROXION_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_RODENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEEP_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.GROXION, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRIDELY, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_GROXION, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SEA_RODENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_DEEP_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_STRIDELY, Models.GENERATED);


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
