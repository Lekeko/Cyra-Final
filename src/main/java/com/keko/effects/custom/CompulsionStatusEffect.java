package com.keko.effects.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class CompulsionStatusEffect extends StatusEffect {
    public CompulsionStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xf9ffab);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; //TODO check armour
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        return super.applyUpdateEffect(entity, amplifier);
    }
}
