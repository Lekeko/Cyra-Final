package com.keko.world;

import com.keko.CyraFinal;
import com.keko.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.rmi.registry.Registry;
import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ENDERITE_ORE_KEY = registerKey("enderite_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> LANTERN_ORE_KEY = registerKey("dim_sea_lantern_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> PRISMATIC_TREE_KEY =registerKey("prismatic_tree");

    public static void boostrap (Registerable<ConfiguredFeature<?, ?>> context){
        RuleTest endStoneReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);
        RuleTest prismarineReplaceables = new BlockMatchRuleTest(Blocks.PRISMARINE);

        List<OreFeatureConfig.Target> endEnderiteOres =
                List.of(OreFeatureConfig.createTarget(endStoneReplaceables, ModBlocks.ENDERITE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> prismarine =
                List.of(OreFeatureConfig.createTarget(prismarineReplaceables, Blocks.SEA_LANTERN.getDefaultState()));

        register(context, ENDERITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(endEnderiteOres, 4));
        register(context, LANTERN_ORE_KEY, Feature.SCATTERED_ORE, new OreFeatureConfig(prismarine, 50));
        register(context, PRISMATIC_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                SimpleBlockStateProvider.of(Blocks.AMETHYST_BLOCK),
                //new CherryTrunkPlacer(7, 1, 0, ConstantIntProvider.create(3), UniformIntProvider.create(2, 4), UniformIntProvider.create(-4, -3), UniformIntProvider.create(-1, 0)),
                new StraightTrunkPlacer(6, 6, 6),
                SimpleBlockStateProvider.of(ModBlocks.PRISMATIC_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(2), 4),
                new TwoLayersFeatureSize(1, 0 ,2)).
                dirtProvider(SimpleBlockStateProvider.of(Blocks.PRISMARINE)).
                build()
        );
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(CyraFinal.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}