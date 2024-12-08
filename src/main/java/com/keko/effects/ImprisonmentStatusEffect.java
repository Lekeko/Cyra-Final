package com.keko.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class ImprisonmentStatusEffect extends StatusEffect {
    public ImprisonmentStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xe9b8b3);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        World world = entity.getWorld();
        int x = entity.getBlockX();
        int y = entity.getBlockX();
        int z = entity.getBlockX();
        world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x,y,z,0, -0.2, 0);

        return super.applyUpdateEffect(entity, amplifier);
    }


}
