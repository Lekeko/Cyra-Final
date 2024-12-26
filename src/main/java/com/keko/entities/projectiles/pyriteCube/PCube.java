package com.keko.entities.projectiles.pyriteCube;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.packet.CubeColorPayload;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class PCube extends PersistentProjectileEntity {

    private BlockPos target;
    private int timeAlive = 0;
    double speed = .6;
    public  Color color;
    ParticleSystemManager manager;
    ParticleEmitter emitter;
    private boolean addedParticle = false;
    private final Light light = new PointLight().setBrightness(0.3f).setColor(1, 1 ,1).setRadius(10);


    public PCube(EntityType<? extends PCube> entityType, World world) {
        super(entityType, world);

    }

    @Override
    protected float getDragInWater() {
        return  0.9f;
    }

    public PCube(World world, LivingEntity entity, @Nullable ItemStack stack, BlockPos target, Color color){
        super(ModProjectileEntities.P_CUBE_ENTITY_TYPE, entity, world, stack, stack);
        this.target = target;
        this.color = color;
    }


    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {

        super.onSpawnPacket(packet);
        if (this.getWorld().isClient){
            VeilRenderSystem.renderer().getLightRenderer().addLight(light);
        }
    }


    @Override
    public boolean hasNoGravity() {
        return true;
    }
    /// /////////////

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {

    }

    @Override
    protected void onBlockCollision(BlockState state) {

    }

    @Override
    public boolean isNoClip() {
        return true;
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return false;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

        if (!this.getWorld().isClient && entityHitResult.getEntity() != this.getOwner())
            entityHitResult.getEntity().damage(this.getWorld().getDamageSources().generic(), 10);
    }

    public void setColor(Color color){
        this.color = color;

    }




    /// /////////////
    @Override
    public void tick() {




        if (!this.getWorld().isClient && target != null && this.color != null){
            ServerPlayNetworking.send((ServerPlayerEntity) this.getOwner(),  new CubeColorPayload(this.getId(), this.color.getRed(), this.color.getGreen(), this.color.getBlue()));
            timeAlive++;



            if (timeAlive > 30)
                this.discard();

            if ((int)target.getX() == (int)this.getX() && (int)target.getY() == (int)this.getY() && (int)target.getZ() == (int)this.getZ()){
                this.discard();
            }

            Vec3d currentPos = this.getPos();
            double distanceX = target.getX() - currentPos.x;
            double distanceY = target.getY() - currentPos.y;
            double distanceZ = target.getZ() - currentPos.z;
            double distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
            if (distanceSquared > 1.0) {

                double scale = speed / Math.sqrt(distanceSquared);
                double velocityX = distanceX * scale;
                double velocityY = distanceY * scale;
                double velocityZ = distanceZ * scale;

                this.addVelocity(velocityX, velocityY, velocityZ);
            }

        }



        if (!this.getWorld().isClient){
            Vec3d vec3d = this.getVelocity();

            Vec3d vec3d3 = this.getPos();
            Vec3d vec3d2 = vec3d3.add(vec3d);
            EntityHitResult entityHitResult = this.getEntityCollision(vec3d3, vec3d2);
            if (entityHitResult != null){
                if (entityHitResult.getEntity() != null && entityHitResult.getEntity() != this.getOwner())
                    entityHitResult.getEntity().damage(this.getWorld().getDamageSources().generic(), 10);
            }


        }else {

            ((PointLight)this.light).setPosition(this.getX(), this.getY(), this.getZ());
            if (color != null){
                light.setColor(this.color.getRed() * 255 * 255 + this.color.getGreen() * 255 + this.color.getBlue());

            }
        }




        super.tick();
    }


    @Override
    public void onRemoved() {
        if (this.getWorld().isClient)
            VeilRenderSystem.renderer().getLightRenderer().removeLight(light);

    }

    public void spawnParticle(Entity entity, Identifier id){
        try {
            manager = VeilRenderSystem.renderer().getParticleManager();
            emitter = manager.createEmitter(id);
            emitter.setAttachedEntity(entity);
            //emitter.setPosition(entity.getX(), entity.getY(), entity.getZ());
            manager.addParticleSystem(emitter);
            this.addedParticle = true;
        } catch (Exception ignored) {

        }
    }

}
