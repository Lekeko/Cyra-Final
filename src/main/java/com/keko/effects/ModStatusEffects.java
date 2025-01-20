package com.keko.effects;

import com.keko.CyraFinal;
import com.keko.effects.custom.CompulsionStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static RegistryEntry<StatusEffect> COMPULSION;

    private static RegistryEntry<StatusEffect> register(String id) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(CyraFinal.MOD_ID, id), new CompulsionStatusEffect());
    }

    public static void registerStatusEffects(){
        COMPULSION = register("compulsion_of_the_old_lord");
    }
}
