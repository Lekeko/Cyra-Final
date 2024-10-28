package com.keko.world.gen;

import com.keko.world.ModPlacedFeature;
import com.keko.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeature.ENDERITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.MURIEL_WATERS),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeature.SEA_CRYSTAL_CLUSTER_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.all(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeature.LANTERN_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.MURIEL_WATERS),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeature.MINIARITE_FORMATION);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.MURIEL_WATERS),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeature.MURIANITE_FORMATION);
    }
}
