package com.keko.features.dim1.roseCrystalAltar;

import com.keko.features.dim1.seaGrass.CrystalSeaGrassFeatureConfig;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record RoseCrystalFeatureConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<RoseCrystalFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(RoseCrystalFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(RoseCrystalFeatureConfig::blockId))
                    .apply(instance, RoseCrystalFeatureConfig::new));
}
