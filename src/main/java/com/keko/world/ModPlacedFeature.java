package com.keko.world;

import com.keko.CyraFinal;
import com.keko.blocks.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.keko.world.ModConfiguredFeatures.*;

public class ModPlacedFeature {
    public static final RegistryKey<PlacedFeature> ENDERITE_ORE_PLACED_KEY = registerKey("enderite_ore_placed");
    public static final RegistryKey<PlacedFeature> VOID_PYRITE_PLACED_KEY = registerKey("void_ore_feature".toLowerCase());
    public static final RegistryKey<PlacedFeature> SEA_CRYSTAL_CLUSTER_PLACED_KEY = registerKey("sea_crystal_cluster_key");
    public static final RegistryKey<PlacedFeature> LANTERN_ORE_PLACED_KEY = registerKey("dim_sea_lantern_placed");
    public static final RegistryKey<PlacedFeature> DAZED_LANTERN_ORE_PLACED_KEY = registerKey("dazed_dim_sea_lantern_placed");
    public static final RegistryKey<PlacedFeature> PRISMATIC_TREE_PLACED = registerKey("crystal_tree_feature");
    public static final RegistryKey<PlacedFeature> PRISMATIC_TREE_DAZED_PLACED = registerKey("crystal_tree_dazed_feature");
    public static final RegistryKey<PlacedFeature> MINIARITE_FORMATION = registerKey("miniarite_formation_placed");
    public static final RegistryKey<PlacedFeature> DAZED_SEA_STONE_FORMATION = registerKey("dazed_sea_stone_formation_placed");
    public static final RegistryKey<PlacedFeature> MURIANITE_FORMATION = registerKey("murianite_formation_placed");
    public static final RegistryKey<PlacedFeature> PYRITE_ORE = registerKey("pyrite_ore_placed");
    public static final RegistryKey<PlacedFeature> VOID_STRUCTURES = registerKey("void_structures_feature");
    public static final RegistryKey<PlacedFeature> CRYSTAL_SEA_GRASS_PLACED = registerKey("crystal_grass_feature");
    public static final RegistryKey<PlacedFeature> CRYSTAL_SEA_GRASS_DAZED_PLACED = registerKey("crystal_grass_dazed_feature");
    public static final RegistryKey<PlacedFeature> ROSE_CRYSTAL_PLACED = registerKey("rose_crystal_feature");
    public static final RegistryKey<PlacedFeature> KYANITE_CRYSTAL_PLACED = registerKey("kyanite_crystal_feature");


    public static final RegistryKey<PlacedFeature> VOID_SURFACE_1 = registerKey("void_surface_1_feature");
    public static final RegistryKey<PlacedFeature> VOID_SURFACE_2 = registerKey("void_surface_2_feature");
    public static final RegistryKey<PlacedFeature> VOID_SURFACE_3 = registerKey("void_surface_3_feature");

    public static void boostrap(Registerable<PlacedFeature> context){
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, ENDERITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ENDERITE_ORE_KEY),
                ModorePlacement.modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));


        register(context, VOID_PYRITE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VOID_PYRITE_ORE_KEY),
                ModorePlacement.modifiersWithCount(3, HeightRangePlacementModifier.uniform(YOffset.fixed(1), YOffset.fixed(290))));


        register(context, VOID_STRUCTURES, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VOID_STRUCTURES_KEY),
                ModorePlacement.modifiersWithCount(1, HeightRangePlacementModifier.uniform(YOffset.fixed(1), YOffset.fixed(290))));


        int offset = 10;

        register(context, SEA_CRYSTAL_CLUSTER_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SEA_CRYSTAL_CLUSTER_KEY),
                ModorePlacement.modifiersWithCount(1, HeightRangePlacementModifier.uniform(YOffset.fixed(200 - offset), YOffset.fixed(300 - offset))));

        register(context, LANTERN_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LANTERN_ORE_KEY),
                ModorePlacement.modifiersWithCount(15, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(300))));

        register(context, DAZED_LANTERN_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DAZED_LANTERN_ORE_KEY),
                ModorePlacement.modifiersWithCount(15, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(300))));



        register(context, VOID_SURFACE_1, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VOID_1),
                ModorePlacement.modifiersWithCount(256, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(300))));
        register(context, VOID_SURFACE_2, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VOID_2),
                ModorePlacement.modifiersWithCount(256, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(300))));
        register(context, VOID_SURFACE_3, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VOID_3),
                ModorePlacement.modifiersWithCount(256, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(300))));






        register(context, CRYSTAL_SEA_GRASS_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CRYSTAL_SEA_GRASS_KEY),
                ModorePlacement.modifiersWithCount(14, HeightRangePlacementModifier.uniform(YOffset.fixed(30 - offset), YOffset.fixed(300 - offset))));

        register(context, CRYSTAL_SEA_GRASS_DAZED_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CRYSTAL_SEA_GRASS_DAZED_KEY),
                ModorePlacement.modifiersWithCount(14, HeightRangePlacementModifier.uniform(YOffset.fixed(30 - offset), YOffset.fixed(300 - offset))));

        register(context, ROSE_CRYSTAL_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ROSE_CRYSTAL_KEY),
                ModorePlacement.modifiersWithCount(1, HeightRangePlacementModifier.uniform(YOffset.fixed(30 - offset), YOffset.fixed(300 - offset))));

        register(context, KYANITE_CRYSTAL_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.KYANITE_CRYSTAL_KEY),
                ModorePlacement.modifiersWithCount(1, HeightRangePlacementModifier.uniform(YOffset.fixed(30 - offset), YOffset.fixed(300 - offset))));

        register(context, PRISMATIC_TREE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(PRISMATIC_TREE_KEY),
                ModorePlacement.modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.fixed(30 - offset), YOffset.fixed(300 - offset))));

        register(context, PRISMATIC_TREE_DAZED_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(PRISMATIC_TREE_DAZED_KEY),
                ModorePlacement.modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.fixed(30 - offset), YOffset.fixed(300 - offset))));

        register(context, MINIARITE_FORMATION, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MINIARITE_ORE),
                ModorePlacement.modifiersWithCount(20, HeightRangePlacementModifier.uniform(YOffset.fixed(80 - offset), YOffset.fixed(200- offset))));

        register(context, MURIANITE_FORMATION, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MURIANITE_ORE),
                ModorePlacement.modifiersWithCount(20, HeightRangePlacementModifier.uniform(YOffset.fixed(200 - offset), YOffset.fixed(300 - offset))));

        register(context, DAZED_SEA_STONE_FORMATION, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DAZED_ORE),
                ModorePlacement.modifiersWithCount(256, HeightRangePlacementModifier.uniform(YOffset.fixed(1), YOffset.fixed(299))));

        register(context, PYRITE_ORE, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PYRITE_ORE_KEY),
                ModorePlacement.modifiersWithCount(6, HeightRangePlacementModifier.uniform(YOffset.fixed(80 - offset), YOffset.fixed(200- offset))));

    }



    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE,Identifier.of(CyraFinal.MOD_ID, name));
    }



    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
