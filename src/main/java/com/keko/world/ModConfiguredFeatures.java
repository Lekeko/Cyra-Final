package com.keko.world;

import com.keko.CyraFinal;
import com.keko.blocks.ModBlocks;
import com.keko.features.ModFeature;
import com.keko.features.dim1.CrystalSeaGrassFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.rmi.registry.Registry;
import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ENDERITE_ORE_KEY = registerKey("enderite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SEA_CRYSTAL_CLUSTER_KEY = registerKey("sea_crystal_cluster");
    public static final RegistryKey<ConfiguredFeature<?,?>> LANTERN_ORE_KEY = registerKey("dim_sea_lantern_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> PRISMATIC_TREE_KEY =registerKey("prismatic_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> MINIARITE_ORE = registerKey("miniarite_formation");
    public static final RegistryKey<ConfiguredFeature<?,?>> MURIANITE_ORE = registerKey("murianite_formation");
    public static final RegistryKey<ConfiguredFeature<?,?>> PYRITE_ORE_KEY = registerKey("pyrite_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> CRYSTAL_SEA_GRASS_KEY = registerKey("crystal_sea_grass");

    public static void boostrap (Registerable<ConfiguredFeature<?, ?>> context){
        RuleTest endStoneReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);
        RuleTest seaStoneReplaceables = new BlockMatchRuleTest(ModBlocks.SEA_STONE);
        RuleTest mirianiteReplaceables = new BlockMatchRuleTest(ModBlocks.SEA_MIRIANITE);
        RuleTest waterReplaceables = new BlockMatchRuleTest(Blocks.WATER);

        List<OreFeatureConfig.Target> endEnderiteOres =
                List.of(OreFeatureConfig.createTarget(endStoneReplaceables, ModBlocks.ENDERITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> seaMirianFormation =
                List.of(OreFeatureConfig.createTarget(seaStoneReplaceables, ModBlocks.SEA_MIRIANITE.getDefaultState()));

        List<OreFeatureConfig.Target> seaMurianiteFormation =
                List.of(OreFeatureConfig.createTarget(seaStoneReplaceables, ModBlocks.SEA_MURIANITE.getDefaultState()));

        List<OreFeatureConfig.Target> prismarine =
                List.of(OreFeatureConfig.createTarget(seaStoneReplaceables, ModBlocks.DEEP_SEA_LANTERN.getDefaultState()));

        List<OreFeatureConfig.Target> seaCrystalCluster =
                List.of(OreFeatureConfig.createTarget(seaStoneReplaceables, ModBlocks.SEA_CRYSTAL_CLUSTER.getDefaultState()));

        List<OreFeatureConfig.Target> mirianiteOrePyrite =
                List.of(OreFeatureConfig.createTarget(seaStoneReplaceables, ModBlocks.PYRITE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> CrystalGrass =
                List.of(OreFeatureConfig.createTarget(waterReplaceables, ModBlocks.CRYSTAL_SEA_GRASS.getDefaultState()));



        register(context, ENDERITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(endEnderiteOres, 4));
        register(context, MINIARITE_ORE, Feature.ORE, new OreFeatureConfig(seaMirianFormation, 45));
        register(context, MURIANITE_ORE, Feature.ORE, new OreFeatureConfig(seaMurianiteFormation, 45));
        register(context, PYRITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(mirianiteOrePyrite, 6));
        register(context, CRYSTAL_SEA_GRASS_KEY, ModFeature.CRYSTAL_GRASS_FEATURE, new CrystalSeaGrassFeatureConfig(10, Identifier.of(CyraFinal.MOD_ID, "crystal_sea_grass")));

        register(context, SEA_CRYSTAL_CLUSTER_KEY,Feature.GEODE,
                new GeodeFeatureConfig(new GeodeLayerConfig(BlockStateProvider.of(Blocks.AIR),
                        BlockStateProvider.of(ModBlocks.SEA_CRYSTAL_FORMATION),
                        BlockStateProvider.of(ModBlocks.SEA_CRYSTAL_FORMATION),
                        BlockStateProvider.of(ModBlocks.SEA_CRYSTAL_FORMATION),
                        BlockStateProvider.of(Blocks.DARK_PRISMARINE),
                        List.of(ModBlocks.SEA_CRYSTAL_CLUSTER.getDefaultState()),
                        BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                        new GeodeLayerThicknessConfig(1.7D, 1.2D, 2.5D, 3.5D),
                        new GeodeCrackConfig(0.25D, 1.5D, 1),
                        0.5D, 0.1D,
                        true, UniformIntProvider.create(3, 8),
                        UniformIntProvider.create(2, 6), UniformIntProvider.create(1, 2),
                        -18, 18, 0.075D, 1));

        register(context, LANTERN_ORE_KEY, Feature.SCATTERED_ORE, new OreFeatureConfig(prismarine, 60));
        register(context, PRISMATIC_TREE_KEY, Feature.HUGE_FUNGUS, new HugeFungusFeatureConfig(
                Blocks.AMETHYST_BLOCK.getDefaultState(), // Stem block
                ModBlocks.PRISMATIC_LEAVES.getDefaultState(), // Foliage block
                ModBlocks.SEA_STONE.getDefaultState(), // Nylium or base block
                ModBlocks.SEA_STONE.getDefaultState(), // Nylium or base block
                BlockPredicate.matchingBlocks(ModBlocks.SEA_STONE), // Nylium or base block
                false // Whether the structure generates with nether wart blocks on the ground
        ));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(CyraFinal.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
