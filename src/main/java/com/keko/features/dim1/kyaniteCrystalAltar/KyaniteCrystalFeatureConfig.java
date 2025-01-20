package com.keko.features.dim1.kyaniteCrystalAltar;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record KyaniteCrystalFeatureConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<KyaniteCrystalFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(KyaniteCrystalFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(KyaniteCrystalFeatureConfig::blockId))
                    .apply(instance, KyaniteCrystalFeatureConfig::new));
}
