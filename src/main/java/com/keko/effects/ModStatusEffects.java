package com.keko.effects;

import com.keko.CyraFinal;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect IMPRISONMENT;

    static {
        IMPRISONMENT = Registry.register(Registries.STATUS_EFFECT, Identifier.of(CyraFinal.MOD_ID, "imprisonment"), new ImprisonmentStatusEffect());
    }

    public static void registerStatusEffects(){}
}
