package com.keko.data;

import com.keko.blocks.ModBlocks;
import com.keko.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.data.server.tag.ItemTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipegenerator  extends FabricRecipeProvider {
    public ModRecipegenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALCHEMY_TABLE)
                .pattern("121")
                .pattern("232")
                .pattern("121")
                .input('1', ModItems.ENDERITE_INGOT)
                .input('2', Items.DIAMOND)
                .input('3', Items.BREWING_STAND)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.RESTORED_PYRITE)
                .pattern(" 2 ")
                .pattern("212")
                .pattern(" 2 ")
                .input('1', ModBlocks.ANCIENT_PYRITE_ORE)
                .input('2', ModItems.PYRITE_CHUNK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.JELLY_CROWN)
                .pattern("111")
                .pattern("121")
                .pattern("111")
                .input('1', ModItems.JELLY_TENTACLES)
                .input('2', Items.GOLDEN_HELMET)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_DOOR, 3)
                .pattern("11")
                .pattern("11")
                .pattern("11")
                .input('1',ModBlocks.BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_FENCE, 3)
                .pattern("121")
                .pattern("121")
                .input('1',ModBlocks.BIOLUMINESCENCE_PLANKS)
                .input('2',Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_FENCE_GATE)
                .pattern("121")
                .pattern("121")
                .input('2',ModBlocks.BIOLUMINESCENCE_PLANKS)
                .input('1',Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_PRESSURE_PLATE)
                .pattern("22")
                .input('2',ModBlocks.BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_SLAB, 6)
                .pattern("222")
                .input('2',ModBlocks.BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_STAIRS, 4)
                .pattern("2  ")
                .pattern("22 ")
                .pattern("222")
                .input('2',ModBlocks.BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_TRAPDOOR, 2)
                .pattern("222")
                .pattern("222")
                .input('2',ModBlocks.BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.CRAFTING_TABLE, 1)
                .pattern("22")
                .pattern("22")
                .input('2',ModBlocks.BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BRICKS, 16)
                .pattern("222")
                .pattern("212")
                .pattern("222")
                .input('2',Blocks.STONE_BRICKS)
                .input('1',ModItems.PYRITE_CHUNK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_EYE_PYRITE_BRICKS, 4)
                .pattern(" 2 ")
                .pattern("222")
                .pattern(" 2 ")
                .input('2', ModBlocks.PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PYRITE_BRICKS, 4)
                .pattern("22")
                .pattern("22")
                .input('2', ModBlocks.TILES_PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PILLAR_PYRITE_BRICKS, 3)
                .pattern("2")
                .pattern("2")
                .pattern("2")
                .input('2', ModBlocks.PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_PYRITE_BRICKS, 4)
                .pattern("22")
                .pattern("22")
                .input('2', ModBlocks.PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BRICK_DOOR, 3)
                .pattern("22")
                .pattern("22")
                .pattern("22")
                .input('2', ModBlocks.PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BRICK_SLAB, 6)
                .pattern("222")
                .input('2', ModBlocks.PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BRICK_WALLS, 16)
                .pattern("2 2")
                .pattern("222")
                .pattern("222")
                .input('2', ModBlocks.PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BRICK_STAIR, 4)
                .pattern("2  ")
                .pattern("22 ")
                .pattern("222")
                .input('2', ModBlocks.PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BRICK_TRAPDOOR, 2)
                .pattern("222")
                .pattern("222")
                .input('2', ModBlocks.PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TILES_PYRITE_BRICKS, 4)
                .pattern("22")
                .pattern("22")
                .input('2', ModBlocks.SMOOTH_PYRITE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BLOCK, 1)
                .pattern("222")
                .pattern("222")
                .pattern("222")
                .input('2', ModItems.RESTORED_PYRITE)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_OF_THE_SEA, 1)
                .pattern("222")
                .pattern("212")
                .pattern("222")
                .input('1', Items.HEART_OF_THE_SEA)
                .input('2', Blocks.OBSIDIAN)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_BLOCK_LAMP, 8)
                .pattern("222")
                .pattern("212")
                .pattern("222")
                .input('1', ModItems.PYRITE_CHUNK)
                .input('2', ModBlocks.DEEP_LANTERN)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_BRICKS, 8)
                .pattern("22")
                .pattern("22")
                .input('2', ModBlocks.VOID_STONE)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_VOID_BRICKS, 8)
                .pattern("22")
                .pattern("22")
                .input('2', ModBlocks.VOID_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_VOID_BRICKS, 8)
                .pattern("22")
                .pattern("22")
                .input('2', ModBlocks.SMOOTH_VOID_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_DOOR, 3)
                .pattern("11")
                .pattern("11")
                .pattern("11")
                .input('1',ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_FENCE, 3)
                .pattern("121")
                .pattern("121")
                .input('1',ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .input('2',Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_FENCE_GATE)
                .pattern("121")
                .pattern("121")
                .input('2',ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .input('1',Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_PRESSURE_PLATE)
                .pattern("22")
                .input('2',ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_SLAB, 6)
                .pattern("222")
                .input('2',ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_STAIRS, 4)
                .pattern("2  ")
                .pattern("22 ")
                .pattern("222")
                .input('2',ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_TRAPDOOR, 2)
                .pattern("222")
                .pattern("222")
                .input('2',ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_SEA_STONE_BRICK, 4)
                .pattern("22")
                .pattern("22")
                .input('2',ModBlocks.DAZED_SEA_STONE)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_STONE_BRICK, 4)
                .pattern("22")
                .pattern("22")
                .input('2',ModBlocks.SEA_STONE)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLASHLIGHT, 1)
                .pattern("121")
                .pattern("232")
                .pattern("121")
                .input('1',Blocks.OBSIDIAN)
                .input('2',ModItems.SEA_CRYSTAL)
                .input('3',ModBlocks.DEEP_LANTERN)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_SHOOTER, 1)
                .pattern("111")
                .pattern("12 ")
                .pattern("111")
                .input('2',ModBlocks.SEA_CRYSTAL_FORMATION)
                .input('1',ModItems.SEA_CRYSTAL)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEP_LANTERN, 4)
                .pattern("22")
                .pattern("22")
                .input('2',ModBlocks.DEEP_SEA_LANTERN)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERITE_BLOCK, 1)
                .pattern("222")
                .pattern("222")
                .pattern("222")
                .input('2',ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_CRYSTAL_BLOCK, 1)
                .pattern("222")
                .pattern("222")
                .pattern("222")
                .input('2',ModItems.SEA_CRYSTAL)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_CRYSTAL_BRICKS, 8)
                .pattern("222")
                .pattern("212")
                .pattern("222")
                .input('1',ModItems.SEA_CRYSTAL_FRAGMENT)
                .input('2',Blocks.STONE_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.SEA_CRYSTAL, 1)
                .pattern(" 2 ")
                .pattern("222")
                .pattern(" 2 ")
                .input('2',ModItems.SEA_CRYSTAL_FRAGMENT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_CRYSTAL_BRICKS_DOOR, 3)
                .pattern("22")
                .pattern("22")
                .pattern("22")
                .input('2',ModBlocks.SEA_CRYSTAL_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_CRYSTAL_BRICKS_SLAB, 6)
                .pattern("222")
                .input('2',ModBlocks.SEA_CRYSTAL_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_CRYSTAL_BRICKS_STAIRS, 4)
                .pattern("2  ")
                .pattern("22 ")
                .pattern("222")
                .input('2',ModBlocks.SEA_CRYSTAL_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_CRYSTAL_BRICKS_TRAPDOOR, 2)
                .pattern("22")
                .pattern("22")
                .input('2',ModBlocks.SEA_CRYSTAL_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_CRYSTAL_BRICKS_WALL, 6)
                .pattern("222")
                .pattern("222")
                .input('2',ModBlocks.SEA_CRYSTAL_BRICKS)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PYRITE_LAMP, 4)
                .pattern("2")
                .pattern("1")
                .input('2',ModItems.PYRITE_CHUNK)
                .input('1', Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.JELLY_PASTA, 4)
                .pattern("2")
                .pattern("1")
                .input('2',ModItems.JELLY_TENTACLES)
                .input('1', Items.BOWL)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_PYRITE, 4)
                .pattern("22")
                .pattern("22")
                .input('2', ModItems.PYRITE_CHUNK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TILE_PYRITE, 4)
                .pattern("22")
                .pattern("22")
                .input('2', ModBlocks.SMOOTH_PYRITE)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BUFF_FLASK, 1)
                .pattern("222")
                .pattern("212")
                .pattern("222")
                .input('2', Blocks.GLASS)
                .input('1', Blocks.DIAMOND_BLOCK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HEALING_FLASK, 1)
                .pattern("222")
                .pattern("212")
                .pattern("222")
                .input('2', Blocks.GLASS)
                .input('1', Items.NETHER_STAR)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DEPTH_CHARM_TIER_1, 1)
                .pattern(" 2 ")
                .pattern("212")
                .pattern(" 2 ")
                .input('2', ModItems.ENDERITE_INGOT)
                .input('1', Items.NETHERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DEPTH_CHARM_TIER_2, 1)
                .pattern(" 2 ")
                .pattern("212")
                .pattern(" 2 ")
                .input('2', ModItems.SEA_CRYSTAL)
                .input('1', ModItems.DEPTH_CHARM_TIER_1)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ELECTRO_CHARGE, 2)
                .pattern(" 2 ")
                .pattern("212")
                .pattern(" 2 ")
                .input('2', Items.IRON_INGOT)
                .input('1', ModItems.JELLY_TENTACLES)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_OXYGEN_TANK, 1)
                .pattern(" 2 ")
                .pattern("212")
                .pattern(" 2 ")
                .input('2', ModItems.ENDERITE_INGOT)
                .input('1', ModItems.ORB_OF_ZEPHYR)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_OXYGEN_TANK, 1)
                .pattern(" 2 ")
                .pattern("212")
                .pattern(" 2 ")
                .input('2', ModItems.SEA_CRYSTAL)
                .input('1', ModItems.ENDERITE_OXYGEN_TANK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_SWORD, 1)
                .pattern(" 2 ")
                .pattern(" 2 ")
                .pattern(" 1 ")
                .input('1', Items.STICK)
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_AXE, 1)
                .pattern("22 ")
                .pattern("21 ")
                .pattern(" 1 ")
                .input('1', Items.STICK)
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_PICKAXE, 1)
                .pattern("222")
                .pattern(" 1 ")
                .pattern(" 1 ")
                .input('1', Items.STICK)
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_SHOVEL, 1)
                .pattern(" 2 ")
                .pattern(" 1 ")
                .pattern(" 1 ")
                .input('1', Items.STICK)
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_HOE, 1)
                .pattern("22 ")
                .pattern(" 1 ")
                .pattern(" 1 ")
                .input('1', Items.STICK)
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_SWORD, 1)
                .pattern(" 2 ")
                .pattern(" 2 ")
                .pattern(" 1 ")
                .input('1', Items.STICK)
                .input('2', ModItems.SEA_CRYSTAL)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_AXE, 1)
                .pattern("22 ")
                .pattern("21 ")
                .pattern(" 1 ")
                .input('2', ModItems.SEA_CRYSTAL)
                .input('1', Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_PICKAXE, 1)
                .pattern("222")
                .pattern(" 1 ")
                .pattern(" 1 ")
                .input('2', ModItems.SEA_CRYSTAL)
                .input('1', Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_SHOVEL, 1)
                .pattern(" 2 ")
                .pattern(" 1 ")
                .pattern(" 1 ")
                .input('2', ModItems.SEA_CRYSTAL)
                .input('1', Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_HOE, 1)
                .pattern("22 ")
                .pattern(" 1 ")
                .pattern(" 1 ")
                .input('2', ModItems.SEA_CRYSTAL)
                .input('1', Items.STICK)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_BOOTS, 1)
                .pattern("   ")
                .pattern("2 2")
                .pattern("2 2")
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_LEGGINGS, 1)
                .pattern("222")
                .pattern("2 2")
                .pattern("2 2")
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_CHESTPLATE, 1)
                .pattern("2 2")
                .pattern("222")
                .pattern("222")
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ENDERITE_HELMET, 1)
                .pattern("222")
                .pattern("2 2")
                .input('2', ModItems.ENDERITE_INGOT)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_BOOTS, 1)
                .pattern("   ")
                .pattern("2 2")
                .pattern("2 2")
                .input('2', ModItems.SEA_CRYSTAL)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_LEGGINGS, 1)
                .pattern("222")
                .pattern("2 2")
                .pattern("2 2")
                .input('2', ModItems.SEA_CRYSTAL)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_CHESTPLATE, 1)
                .pattern("2 2")
                .pattern("222")
                .pattern("222")
                .input('2', ModItems.SEA_CRYSTAL)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_CRYSTAL_HELMET, 1)
                .pattern("222")
                .pattern("2 2")
                .input('2', ModItems.SEA_CRYSTAL)
                .group("multi_bench")
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);


        //shapeless/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_BUTTON)
                .input(ModBlocks.BIOLUMINESCENCE_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_BUTTON)
                .input(ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIOLUMINESCENCE_PLANKS, 4)
                .input(ModBlocks.BIOLUMINESCENCE_WOOD)
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DAZED_BIOLUMINESCENCE_PLANKS, 4)
                .input(ModBlocks.DAZED_WOOD)
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SEA_BOLT, 4)
                .input(ModItems.SEA_CRYSTAL)
                .criterion(FabricRecipeProvider.hasItem(Items.CRAFTING_TABLE), FabricRecipeProvider.conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);



        //frunace

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofStacks(ModItems.ENDERITE_CHUNK.getDefaultStack()), RecipeCategory.COMBAT, ModItems.ENDERITE_INGOT, 20, 100)
                .criterion(FabricRecipeProvider.hasItem(Items.BLAST_FURNACE), FabricRecipeProvider.conditionsFromItem(Items.BLAST_FURNACE))
                .offerTo(exporter);

        FabricRecipeProvider.offerSmelting(exporter,
                List.of(ModItems.DEEP_SALMON),
                RecipeCategory.FOOD,
                ModItems.COOKED_DEEP_SALMON,
                0.5f,
                200,
                "food_to_deep_salmon"
        );
        FabricRecipeProvider.offerSmelting(exporter,
                List.of(ModItems.GROXION),
                RecipeCategory.FOOD,
                ModItems.COOKED_GROXION,
                0.5f,
                200,
                "food_to_groxion"
        );
        FabricRecipeProvider.offerSmelting(exporter,
                List.of(ModItems.SEA_RODENT),
                RecipeCategory.FOOD,
                ModItems.COOKED_SEA_RODENT,
                0.5f,
                200,
                "food_to_sea_rodent"
        );
        FabricRecipeProvider.offerSmelting(exporter,
                List.of(ModItems.STRIDELY),
                RecipeCategory.FOOD,
                ModItems.FRIED_STRIDELY,
                0.5f,
                200,
                "food_to_stridely"
        );
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofStacks(ModItems.DEEP_SALMON.getDefaultStack()), RecipeCategory.FOOD, ModItems.COOKED_DEEP_SALMON, 1, 100)
                .criterion(FabricRecipeProvider.hasItem(Items.SMOKER), FabricRecipeProvider.conditionsFromItem(Items.SMOKER))
                .group("smoker_deep_salmon")
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofStacks(ModItems.GROXION.getDefaultStack()), RecipeCategory.FOOD, ModItems.COOKED_GROXION, 1, 100)
                .criterion(FabricRecipeProvider.hasItem(Items.SMOKER), FabricRecipeProvider.conditionsFromItem(Items.SMOKER))
                .group("smoker_groxion")
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofStacks(ModItems.SEA_RODENT.getDefaultStack()), RecipeCategory.FOOD, ModItems.COOKED_SEA_RODENT, 1, 100)
                .criterion(FabricRecipeProvider.hasItem(Items.SMOKER), FabricRecipeProvider.conditionsFromItem(Items.SMOKER))
                .group("smoker_sea_rodent")
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofStacks(ModItems.STRIDELY.getDefaultStack()), RecipeCategory.FOOD, ModItems.FRIED_STRIDELY, 1, 100)
                .criterion(FabricRecipeProvider.hasItem(Items.SMOKER), FabricRecipeProvider.conditionsFromItem(Items.SMOKER))
                .group("smoker_stridely")
                .offerTo(exporter);


        //stone cutter

    }
}
