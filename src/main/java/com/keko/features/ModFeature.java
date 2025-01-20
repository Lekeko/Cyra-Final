package com.keko.features;

import com.keko.features.dim1.crystalTrees.CrystalTreesFeature;
import com.keko.features.dim1.crystalTrees.CrystalTreesFeatureConfig;
import com.keko.features.dim1.crystalTreesDazed.CrystalTreesDazedFeature;
import com.keko.features.dim1.crystalTreesDazed.CrystalTreesFeatureDazedConfig;
import com.keko.features.dim1.kyaniteCrystalAltar.KyaniteCrystalFeature;
import com.keko.features.dim1.kyaniteCrystalAltar.KyaniteCrystalFeatureConfig;
import com.keko.features.dim1.roseCrystalAltar.RoseCrystalFeature;
import com.keko.features.dim1.roseCrystalAltar.RoseCrystalFeatureConfig;
import com.keko.features.dim1.seaGrass.CrystalSeaGrassFeature;
import com.keko.features.dim1.seaGrass.CrystalSeaGrassFeatureConfig;
import com.keko.features.dim1.seaGrassDazed.CrystalSeaGrassDazedFeature;
import com.keko.features.dim1.seaGrassDazed.CrystalSeaGrassFeatureDazedConfig;
import com.keko.features.dim1.voidPyrite.VoidPyriteConfig;
import com.keko.features.dim1.voidPyrite.VoidPyriteFeature;
import com.keko.features.dim1.voidStuctures.VoidStructuresConfig;
import com.keko.features.dim1.voidStuctures.VoidStructuresFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFeature {
    public static final Identifier CRYSTAL_GRASS_FEATURE_ID = Identifier.of("cyra", "crystal_grass_feature");
    public static final CrystalSeaGrassFeature CRYSTAL_GRASS_FEATURE = new CrystalSeaGrassFeature(CrystalSeaGrassFeatureConfig.CODEC);

    public static final Identifier VOID_ORE_FEATURE_ID = Identifier.of("cyra", "void_ore_feature");
    public static final VoidPyriteFeature VOID_ORE_ID = new VoidPyriteFeature(VoidPyriteConfig.CODEC);

    public static final Identifier VOID_STRUCTURES_FEATURE_ID = Identifier.of("cyra", "void_structures_feature");
    public static final VoidStructuresFeature VOID_STRUCTURES_ID = new VoidStructuresFeature(VoidStructuresConfig.CODEC);

    public static final Identifier CRYSTAL_TREE_FEATURE_ID = Identifier.of("cyra", "crystal_tree_feature");
    public static final CrystalTreesFeature CRYSTAL_TREE_FEATURE = new CrystalTreesFeature(CrystalTreesFeatureConfig.CODEC);

    public static final Identifier CRYSTAL_GRASS_DAZED_FEATURE_ID = Identifier.of("cyra", "crystal_grass_dazed_feature");
    public static final CrystalSeaGrassDazedFeature CRYSTAL_GRASS_DAZED_FEATURE = new CrystalSeaGrassDazedFeature(CrystalSeaGrassFeatureDazedConfig.CODEC);

    public static final Identifier CRYSTAL_TREE_DAZED_FEATURE_ID = Identifier.of("cyra", "crystal_tree_dazed_feature");
    public static final CrystalTreesDazedFeature CRYSTAL_TREE_DAZED_FEATURE = new CrystalTreesDazedFeature(CrystalTreesFeatureDazedConfig.CODEC);

    public static final Identifier ROSE_CRYSTAL_FEATURE_ID = Identifier.of("cyra", "rose_crystal_feature");
    public static final RoseCrystalFeature ROSE_CRYSTAL_FEATURE = new RoseCrystalFeature(RoseCrystalFeatureConfig.CODEC);

    public static final Identifier KYANITE_CRYSTAL_FEATURE_ID = Identifier.of("cyra", "kyanite_crystal_feature");
    public static final KyaniteCrystalFeature KYANITE_CRYSTAL_FEATURE = new KyaniteCrystalFeature(KyaniteCrystalFeatureConfig.CODEC);

    public static void registerFeatures(){
        Registry.register(Registries.FEATURE, CRYSTAL_GRASS_FEATURE_ID, CRYSTAL_GRASS_FEATURE);
        Registry.register(Registries.FEATURE, CRYSTAL_GRASS_DAZED_FEATURE_ID, CRYSTAL_GRASS_DAZED_FEATURE);
        Registry.register(Registries.FEATURE, CRYSTAL_TREE_DAZED_FEATURE_ID, CRYSTAL_TREE_DAZED_FEATURE);
        Registry.register(Registries.FEATURE, CRYSTAL_TREE_FEATURE_ID, CRYSTAL_TREE_FEATURE);
        Registry.register(Registries.FEATURE, ROSE_CRYSTAL_FEATURE_ID, ROSE_CRYSTAL_FEATURE);
        Registry.register(Registries.FEATURE, KYANITE_CRYSTAL_FEATURE_ID, KYANITE_CRYSTAL_FEATURE);
        Registry.register(Registries.FEATURE, VOID_ORE_FEATURE_ID, VOID_ORE_ID);
        Registry.register(Registries.FEATURE, VOID_STRUCTURES_FEATURE_ID, VOID_STRUCTURES_ID);
    }
}
