package com.keko.items.tools.orbs;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;

public abstract class Orbs extends Item{

    private static StatusEffectInstance effect;



    public Orbs(Settings settings) {
        super(settings);
    }


    public static StatusEffectInstance getEffect() {
        return effect;
    }
}
