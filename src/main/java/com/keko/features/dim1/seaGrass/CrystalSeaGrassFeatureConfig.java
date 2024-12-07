package com.keko.features.dim1.seaGrass;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record CrystalSeaGrassFeatureConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<CrystalSeaGrassFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            // you can add as many of these as you want, one for each parameter
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(CrystalSeaGrassFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(CrystalSeaGrassFeatureConfig::blockId))
                    .apply(instance, CrystalSeaGrassFeatureConfig::new));
}
