package com.keko.features;

import com.keko.CyraFinal;
import com.keko.features.dim1.CrystalSeaGrassFeature;
import com.keko.features.dim1.CrystalSeaGrassFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModFeature {
    public static final Identifier CRYSTAL_GRASS_FEATURE_ID = Identifier.of("cyra", "crystal_grass_feature");
    public static final CrystalSeaGrassFeature CRYSTAL_GRASS_FEATURE = new CrystalSeaGrassFeature(CrystalSeaGrassFeatureConfig.CODEC);


    public static void registerFeatures(){
        Registry.register(Registries.FEATURE, CRYSTAL_GRASS_FEATURE_ID, CRYSTAL_GRASS_FEATURE);
    }
}
