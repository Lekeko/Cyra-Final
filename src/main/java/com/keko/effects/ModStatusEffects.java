package com.keko.effects;

import com.keko.CyraFinal;
import com.keko.effects.custom.CompulsionStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect COMPULSION;

    static {
        COMPULSION = Registry.register(Registries.STATUS_EFFECT, Identifier.of(CyraFinal.MOD_ID, "compulsion_of_the_old_lord"), new CompulsionStatusEffect());
    }

    public static void registerStatusEffects(){}
}
