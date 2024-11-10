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

import static com.keko.world.ModConfiguredFeatures.PRISMATIC_TREE_KEY;
import static com.keko.world.ModConfiguredFeatures.SEA_CRYSTAL_CLUSTER_KEY;

public class ModPlacedFeature {
    public static final RegistryKey<PlacedFeature> ENDERITE_ORE_PLACED_KEY = registerKey("enderite_ore_placed");
    public static final RegistryKey<PlacedFeature> SEA_CRYSTAL_CLUSTER_PLACED_KEY = registerKey("sea_crystal_cluster_key");
    public static final RegistryKey<PlacedFeature> LANTERN_ORE_PLACED_KEY = registerKey("dim_sea_lantern_placed");
    public static final RegistryKey<PlacedFeature> PRISMATIC_TREE_PLACED = registerKey("prismatic_tree_placed");
    public static final RegistryKey<PlacedFeature> MINIARITE_FORMATION = registerKey("miniarite_formation_placed");
    public static final RegistryKey<PlacedFeature> MURIANITE_FORMATION = registerKey("murianite_formation_placed");
    public static final RegistryKey<PlacedFeature> PYRITE_ORE = registerKey("pyrite_ore_placed");

    public static void boostrap(Registerable<PlacedFeature> context){
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, ENDERITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ENDERITE_ORE_KEY),
                ModorePlacement.modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));


        int offset = 10;
        register(context, SEA_CRYSTAL_CLUSTER_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SEA_CRYSTAL_CLUSTER_KEY),
                ModorePlacement.modifiersWithCount(1, HeightRangePlacementModifier.uniform(YOffset.fixed(200 - offset), YOffset.fixed(300 - offset))));

        register(context, LANTERN_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LANTERN_ORE_KEY),
                ModorePlacement.modifiersWithCount(15, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(300))));

        register(context, MINIARITE_FORMATION, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MINIARITE_ORE),
                ModorePlacement.modifiersWithCount(20, HeightRangePlacementModifier.uniform(YOffset.fixed(80 - offset), YOffset.fixed(200- offset))));

        register(context, MURIANITE_FORMATION, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MURIANITE_ORE),
                ModorePlacement.modifiersWithCount(20, HeightRangePlacementModifier.uniform(YOffset.fixed(200 - offset), YOffset.fixed(300 - offset))));

        register(context, PYRITE_ORE, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PYRITE_ORE_KEY),
                ModorePlacement.modifiersWithCount(3, HeightRangePlacementModifier.uniform(YOffset.fixed(80 - offset), YOffset.fixed(200- offset))));

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
