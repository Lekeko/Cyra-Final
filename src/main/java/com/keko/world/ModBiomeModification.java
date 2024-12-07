package com.keko.world;

import com.keko.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.util.PlacedFeatureIndexer;

import static com.keko.features.ModFeature.CRYSTAL_GRASS_FEATURE_ID;
import static com.keko.features.ModFeature.CRYSTAL_TREE_FEATURE_ID;

public class ModBiomeModification {
    public static void load(){

        BiomeModifications.addFeature(
                BiomeSelectors.all(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, CRYSTAL_GRASS_FEATURE_ID)
        );

        BiomeModifications.addFeature(
                BiomeSelectors.all(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, CRYSTAL_TREE_FEATURE_ID)
        );
    }
}
