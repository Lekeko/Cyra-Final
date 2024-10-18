package com.keko.world.gen;

import com.keko.CyraFinal;
import com.keko.world.ModPlacedFeature;
import com.keko.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.MURIEL_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeature.PRISMATIC_TREE_PLACED);
    }



}
