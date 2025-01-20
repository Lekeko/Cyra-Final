package com.keko.world;

import com.keko.entities.ModEntities;
import com.keko.entities.normal.jellyfish.JellyFishEntity;
import com.keko.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.util.PlacedFeatureIndexer;

import static com.keko.features.ModFeature.*;

public class ModBiomeModification {
    public static void load(){

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.MURIEL_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, CRYSTAL_GRASS_FEATURE_ID)
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.MURIEL_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, CRYSTAL_TREE_FEATURE_ID)
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.VOID_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, VOID_ORE_FEATURE_ID)
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.VOID_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, VOID_STRUCTURES_FEATURE_ID)
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.MURIEL_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, ROSE_CRYSTAL_FEATURE_ID)
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.DAZED_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, KYANITE_CRYSTAL_FEATURE_ID)
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.DAZED_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, CRYSTAL_GRASS_DAZED_FEATURE_ID)
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.DAZED_WATERS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, CRYSTAL_TREE_DAZED_FEATURE_ID)
        );



        //ENTITY

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(ModBiomes.DAZED_WATERS, ModBiomes.MURIEL_WATERS), SpawnGroup.CREATURE,
                ModEntities.JELLYFISH, 7, 1, 2);

        SpawnRestriction.register(ModEntities.JELLYFISH, SpawnLocationTypes.UNRESTRICTED, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> {
            if (world.getBlockState(pos).isOf(Blocks.WATER) && areThereJellyfishesQuestionMark(world, pos))
                return true;
            else return false;
        });

    }

    private static boolean areThereJellyfishesQuestionMark(ServerWorldAccess world, BlockPos pos) {
        return world.getEntitiesByClass(JellyFishEntity.class, new Box(pos.getX() + 50, pos.getY() + 50, pos.getZ() + 50, pos.getX() - 50, pos.getY() - 50, pos.getZ() - 50), Entity::isAlive).isEmpty();
    }
}
