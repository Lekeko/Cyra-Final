package com.keko.particle;

import com.keko.CyraFinal;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType WATER_BOLT_PARTICLE_TYPE = FabricParticleTypes.simple();
    public static final SimpleParticleType PYRITE_SLASH = FabricParticleTypes.simple();
    public static final SimpleParticleType PYRITE_STAR = FabricParticleTypes.simple();

    public static void register(){
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CyraFinal.MOD_ID, "water_bolt_particle"),
                WATER_BOLT_PARTICLE_TYPE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CyraFinal.MOD_ID, "pyrite_slash_particle"),
                PYRITE_SLASH);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CyraFinal.MOD_ID, "pyrite_star_particle"),
                PYRITE_STAR);
    }

}
