package com.keko.world;

import com.keko.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeatures;

public class ModBiomeModification {
    public static void load(){
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.MURIEL_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeature.PRISMATIC_TREE_PLACED
        );
    }
}
