package com.keko.particle;

import com.keko.particle.custom.PyriteBoomParticle;
import com.keko.particle.custom.PyriteSlashParticle;
import com.keko.particle.custom.PyriteStarParticle;
import com.keko.particle.custom.WaterBoltParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class ParticleRegisterMod {
    public static void register() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.WATER_BOLT_PARTICLE_TYPE, WaterBoltParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.PYRITE_SLASH, PyriteSlashParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.PYRITE_STAR, PyriteStarParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.PYRITE_BOOM, PyriteBoomParticle.Factory::new);

    }
}
