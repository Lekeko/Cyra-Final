package com.keko.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class WaterBoltParticle extends SpriteBillboardParticle {

    protected WaterBoltParticle(ClientWorld clientWorld, double x, double y, double z, double xv, double yv, double zv) {
        super(clientWorld, x, y, z, zv, yv, zv);

        this.velocityMultiplier = 0.2f;
        this.x = xv;
        this.y = yv;
        this.z = zv;

        this.scale *= 0.75F;
        this.maxAge = 60;

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType>{
        private final SpriteProvider spriteProvider;

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new WaterBoltParticle(world, x, y, z, velocityX, velocityY, velocityZ);
        }

        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;

        }

    }
}
