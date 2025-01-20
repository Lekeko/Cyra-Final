package com.keko.world.biome;

import com.keko.CyraFinal;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomes {
    public static final RegistryKey<Biome> MURIEL_WATERS = register("muriel_waters");
    public static final RegistryKey<Biome> DAZED_WATERS = register("dazed_waters");
    public static final RegistryKey<Biome> VOID_WATERS = register("void_waters");


    public static void registerBiomes(){

    }


    private static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(CyraFinal.MOD_ID, name));
    }
}
