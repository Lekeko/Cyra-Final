package com.keko.world;

import com.keko.CyraFinal;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> MURIEL_KAIA_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(CyraFinal.MOD_ID, "murielkaiadim"));
    public static final RegistryKey<World> MURIEL_KAIA_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(CyraFinal.MOD_ID, "murielkaiadim"));
    public static final RegistryKey<DimensionType> MURIEL_KAIA_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "murielkaiadim"));

    public static void boostrapType(Registerable<DimensionType> context) {
        context.register(MURIEL_KAIA_DIM_TYPE, new DimensionType(
           OptionalLong.of(12000),
           false,
           false,
           false,
           true,
           1.0,
           true,
           false,
           0,
           336,
           336,
                BlockTags.INFINIBURN_OVERWORLD,
                DimensionTypes.OVERWORLD_ID,
                1.0f,
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)
        ));
    }

    public static void register() {
    }
}
