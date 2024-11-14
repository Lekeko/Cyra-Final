package com.keko.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

import org.jetbrains.annotations.Nullable;
import org.joml.*;

public class WaterBoltParticle extends SpriteBillboardParticle {
    private float rotationAngle;
    Random random = new Random();

    protected WaterBoltParticle(ClientWorld clientWorld, double x, double y, double z,
                                SpriteProvider spriteProvider, double xv, double yv, double zv) {
        super(clientWorld, x, y, z, zv, yv, zv);

        this.velocityMultiplier = 0.2f;
        this.x = xv;
        this.y = yv;
        this.z = zv;
        this.setSpriteForAge(spriteProvider);

        this.scale = 0.2f;
        this.maxAge = 60;

        this.red = 221f;
        this.green = 1f;
        this.blue = 1f;
        this.alpha = 2255;

        this.rotationAngle = 0.0F; // Initial rotation angle

    }

    @Override
    public void move(double dx, double dy, double dz) {
        super.move(this.velocityX, this.velocityY, this.velocityZ);
    }

    @Override
    public Particle move(float speed) {
        return super.move(2);
    }

    @Override
    protected int getBrightness(float tint) {
        return 15728880;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }



    @Override
    public void tick() {
        super.tick();
        fade();
        rotationAngle += 0.04f;
        this.scale+= 0.01f;
        //this.angle = rotationAngle;
    }

    private void fade() {
        this.alpha = (-(1/(float)maxAge) * age + 1);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType>{
        private final SpriteProvider spriteProvider;

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new WaterBoltParticle(world, x, y, z, this.spriteProvider , velocityX, velocityY, velocityZ);
        }


        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;

        }

    }
}
