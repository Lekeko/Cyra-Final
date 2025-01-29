package com.keko.data;

import com.keko.blocks.ModBlocks;
import com.keko.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {

    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.ANCIENT_PYRITE_ORE);
        addDrop(ModBlocks.BIOLUMINESCENCE_LEAVES);
        addDrop(ModBlocks.BIOLUMINESCENCE_PLANKS);
        addDrop(ModBlocks.BIOLUMINESCENCE_BUTTON);
        addDrop(ModBlocks.BIOLUMINESCENCE_FENCE_GATE);
        addDrop(ModBlocks.BIOLUMINESCENCE_FENCE);
        addDrop(ModBlocks.BIOLUMINESCENCE_PRESSURE_PLATE);
        addDrop(ModBlocks.BIOLUMINESCENCE_SLAB);
        addDrop(ModBlocks.BIOLUMINESCENCE_STAIRS);
        addDrop(ModBlocks.BIOLUMINESCENCE_TRAPDOOR);
        addDrop(ModBlocks.BIOLUMINESCENCE_WOOD);
        addDrop(ModBlocks.CHISELED_EYE_PYRITE_BRICKS);
        addDrop(ModBlocks.CHISELED_PYRITE_BRICKS);
        addDrop(ModBlocks.CHISELED_VOID_BRICKS);
        addDrop(ModBlocks.CORE_OF_THE_SEA);
        addDrop(ModBlocks.CRYSTAL_SEA_GRASS);
        addDrop(ModBlocks.CRYSTAL_SEA_GRASS_DAZED);
        addDrop(ModBlocks.CRYSTAL_SEA_WEED);
        addDrop(ModBlocks.DAZED_LEAVES);
        addDrop(ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS);
        addDrop(ModBlocks.DAZED_WOOD);
        addDrop(ModBlocks.DAZED_BIOLUMINESCENCE_BUTTON);
        addDrop(ModBlocks.DAZED_BIOLUMINESCENCE_FENCE_GATE);
        addDrop(ModBlocks.DAZED_BIOLUMINESCENCE_FENCE);
        addDrop(ModBlocks.DAZED_BIOLUMINESCENCE_PRESSURE_PLATE);
        addDrop(ModBlocks.DAZED_BIOLUMINESCENCE_SLAB);
        addDrop(ModBlocks.DAZED_BIOLUMINESCENCE_STAIRS);
        addDrop(ModBlocks.DAZED_BIOLUMINESCENCE_TRAPDOOR);
        addDrop(ModBlocks.DAZED_CRYSTAL_SEA_WEED);
        addDrop(ModBlocks.DAZED_SEA_STONE);
        addDrop(ModBlocks.DAZED_SEA_STONE_BRICK);
        addDrop(ModBlocks.DEEP_LANTERN);
        addDrop(ModBlocks.DEEP_SEA_LANTERN);
        addDrop(ModBlocks.ENDERITE_BLOCK);
        addDrop(ModBlocks.KYANITE_CRYSTAL);
        addDrop(ModBlocks.PILLAR_PYRITE_BRICKS);
        addDrop(ModBlocks.PYRITE_BLOCK);
        addDrop(ModBlocks.PYRITE_BLOCK_LAMP);
        addDrop(ModBlocks.PYRITE_BRICK_SLAB);
        addDrop(ModBlocks.PYRITE_BRICK_STAIR);
        addDrop(ModBlocks.PYRITE_BRICK_TRAPDOOR);
        addDrop(ModBlocks.PYRITE_BRICK_WALLS);
        addDrop(ModBlocks.PYRITE_BRICKS);
        addDrop(ModBlocks.PYRITE_FABRICATOR);
        addDrop(ModBlocks.ROSE_CRYSTAL);
        addDrop(ModBlocks.SEA_CRYSTAL_BLOCK);
        addDrop(ModBlocks.SEA_CRYSTAL_BRICKS_SLAB);
        addDrop(ModBlocks.SEA_CRYSTAL_BRICKS_STAIRS);
        addDrop(ModBlocks.SEA_CRYSTAL_BRICKS_WALL);
        addDrop(ModBlocks.SEA_CRYSTAL_BRICKS_TRAPDOOR);
        addDrop(ModBlocks.SEA_CRYSTAL_FORMATION);
        addDrop(ModBlocks.SEA_CRYSTAL_BRICKS);
        addDrop(ModBlocks.SEA_MIRIANITE);
        addDrop(ModBlocks.SEA_MURIANITE);
        addDrop(ModBlocks.SEA_STONE);
        addDrop(ModBlocks.SEA_STONE_BRICK);
        addDrop(ModBlocks.SEA_STONE_GRASS);
        addDrop(ModBlocks.SEA_STONE_GRASS_DAZED);
        addDrop(ModBlocks.SMOOTH_PYRITE);
        addDrop(ModBlocks.SMOOTH_PYRITE_BRICKS);
        addDrop(ModBlocks.SMOOTH_VOID_BRICKS);
        addDrop(ModBlocks.TILE_PYRITE);
        addDrop(ModBlocks.VOID_BRICKS);
        addDrop(ModBlocks.VOID_STONE);
        addDrop(ModBlocks.TILES_PYRITE_BRICKS);
        addDrop(ModBlocks.ANCIENT_PYRITE_ORE);
        addDrop(ModBlocks.VOID_PYRITE_ORE, specialOreDrops(ModBlocks.VOID_PYRITE_ORE, ModItems.PYRITE_CHUNK, 1f, 1f));
        addDrop(ModBlocks.PYRITE_ORE, specialOreDrops(ModBlocks.PYRITE_ORE, ModItems.PYRITE_CHUNK, 1f, 1f));

        addDrop(ModBlocks.SEA_CRYSTAL_CLUSTER, specialOreDrops(ModBlocks.SEA_CRYSTAL_CLUSTER, ModItems.SEA_CRYSTAL_FRAGMENT, 3f, 5f));
        addDrop(ModBlocks.SEA_CRYSTAL_FORMATION, ModBlocks.SEA_CRYSTAL_FORMATION);
    }

    public LootTable.Builder specialOreDrops(Block drop, Item item, float min, float max){
        return BlockLootTableGenerator.drops(drop , super.createSilkTouchCondition()  ,(LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(min, max))))
                      ));
    }
}
