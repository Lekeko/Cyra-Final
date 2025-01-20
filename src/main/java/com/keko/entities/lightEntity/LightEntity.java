package com.keko.entities.lightEntity;

import com.keko.entities.ModEntities;
import com.keko.items.ModItems;
import foundry.veil.Veil;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import foundry.veil.api.quasar.data.ParticleSettings;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class LightEntity extends PersistentProjectileEntity {

    PlayerEntity owner;
    Light light;
    boolean wasAdded = false;

    public LightEntity(EntityType<? extends LightEntity> entityType, World world) {
        super(entityType, world);
        light = new PointLight().setRadius(30f).setBrightness(0.7f).setColor((float) 122 / 255, (float) 255 / 255, (float) 255 / 255);

    }


    public void setOwner(PlayerEntity owner) {
        this.owner = owner;
    }
    double speed = 0.1f;

    @Override
    public boolean isSubmergedInWater() {
        return super.isSubmergedInWater();
    }

    @Override
    protected void onSwimmingStart() {

    }

    @Override
    public void tick() {
        if (this.getWorld().isClient && owner!= null){
            ((PointLight)light).setPosition(this.getX(), this.getY(), this.getZ());
            if (!wasAdded) {
                VeilRenderSystem.renderer().getLightRenderer().addLight(light);
                wasAdded = true;
            }
            spawnParticle(this, Identifier.of("cyra:star"));

        }
        if (!this.getWorld().isClient  && owner== null)
            discard();

        if (!this.getWorld().isClient  && owner!= null){


            Vec3d currentPos = this.getPos();
            double distanceX = owner.getX() - currentPos.x;
            double distanceY = owner.getY() - currentPos.y + 1.6f;
            double distanceZ = owner.getZ() - currentPos.z;
            double distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
            if (distanceSquared > 15) {

                double scale = speed / Math.sqrt(distanceSquared);
                double velocityX = distanceX * scale;
                double velocityY = distanceY * scale;
                double velocityZ = distanceZ * scale;

                this.addVelocity(velocityX, velocityY, velocityZ);
            }
        }


        super.tick();
    }

    @Override
    protected void checkBlockCollision() {

    }

    @Override
    public boolean isNoClip() {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    public void spawnParticle(Entity entity, Identifier id){
        try {
            ParticleSystemManager manager = VeilRenderSystem.renderer().getParticleManager();
            ParticleEmitter emitter = manager.createEmitter(id);
            emitter.setAttachedEntity(entity);
            emitter.setParticleSettings(new ParticleSettings(0.1f, 0.1f, 0.05f,
                    20, 0f,
                    new Vector3f(
                            this.getWorld().random.nextFloat() - .5f,
                            this.getWorld().random.nextFloat() - .5f  * 5f,
                            this.getWorld().random.nextFloat() - .5f),
                    false,false,false,false,false));
            manager.addParticleSystem(emitter);
        } catch (Exception ignored) {
        }
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {

    }

    @Override
    protected void onCollision(HitResult hitResult) {

    }

    @Override
    protected void onBlockCollision(BlockState state) {

    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return ModItems.FLASHLIGHT.getDefaultStack();
    }

    @Override
    public void onRemoved() {
        if (this.getWorld().isClient){
            VeilRenderSystem.renderer().getLightRenderer().removeLight(light);

        }
        super.onRemoved();
    }

}
