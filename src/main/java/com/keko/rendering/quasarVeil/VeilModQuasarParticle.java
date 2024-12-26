package com.keko.rendering.quasarVeil;

import foundry.veil.Veil;
import foundry.veil.api.quasar.data.ParticleEmitterData;
import foundry.veil.api.quasar.data.ParticleModuleTypeRegistry;
import foundry.veil.api.quasar.data.QuasarParticles;
import foundry.veil.api.quasar.data.module.ParticleModuleData;
import foundry.veil.api.quasar.emitters.module.ParticleModule;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.QuasarParticle;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class VeilModQuasarParticle implements ParticleModule {

    public static void registerParticles(){
        registerStar();
    }

    private static void registerStar() {
    }
}
