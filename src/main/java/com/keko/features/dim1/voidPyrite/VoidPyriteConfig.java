package com.keko.features.dim1.voidPyrite;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record VoidPyriteConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<VoidPyriteConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            // you can add as many of these as you want, one for each parameter
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(VoidPyriteConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(VoidPyriteConfig::blockId))
                    .apply(instance, VoidPyriteConfig::new));
}
