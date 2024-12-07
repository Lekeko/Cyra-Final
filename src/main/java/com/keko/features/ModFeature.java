package com.keko.features;

import com.keko.features.dim1.crystalTrees.CrystalTreesFeature;
import com.keko.features.dim1.crystalTrees.CrystalTreesFeatureConfig;
import com.keko.features.dim1.seaGrass.CrystalSeaGrassFeature;
import com.keko.features.dim1.seaGrass.CrystalSeaGrassFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFeature {
    public static final Identifier CRYSTAL_GRASS_FEATURE_ID = Identifier.of("cyra", "crystal_grass_feature");
    public static final CrystalSeaGrassFeature CRYSTAL_GRASS_FEATURE = new CrystalSeaGrassFeature(CrystalSeaGrassFeatureConfig.CODEC);

    public static final Identifier CRYSTAL_TREE_FEATURE_ID = Identifier.of("cyra", "crystal_tree_feature");
    public static final CrystalTreesFeature CRYSTAL_TREE_FEATURE = new CrystalTreesFeature(CrystalTreesFeatureConfig.CODEC);


    public static void registerFeatures(){
        Registry.register(Registries.FEATURE, CRYSTAL_GRASS_FEATURE_ID, CRYSTAL_GRASS_FEATURE);
        Registry.register(Registries.FEATURE, CRYSTAL_TREE_FEATURE_ID, CRYSTAL_TREE_FEATURE);
    }
}
