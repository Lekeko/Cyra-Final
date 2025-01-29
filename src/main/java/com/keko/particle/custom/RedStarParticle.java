//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.keko.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class RedStarParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    protected RedStarParticle(ClientWorld clientWorld, double x, double y, double z,
                              SpriteProvider spriteProvider, double xv, double yv, double zv) {
        super(clientWorld, x, y, z, xv, yv, zv);

        this.spriteProvider = spriteProvider;
        this.maxAge = 38;
        float f = 1;
        this.red = f;
        this.green = f;
        this.blue = f;
        this.scale = 0.2F;
        this.setSpriteForAge(spriteProvider);
    }

    public int getBrightness(float tint) {
        return 15728880;
    }

    @Override
    public void tick() {
        super.tick();
        fade();
        this.scale-= 0.006f;
    }
    private void fade() {
        this.alpha = (-(1/(float)maxAge) * age + 1);
    }



    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType>{
        private final SpriteProvider spriteProvider;

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new RedStarParticle(world, x, y, z, this.spriteProvider , velocityX, velocityY, velocityZ);
        }


        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;

        }

    }
}
