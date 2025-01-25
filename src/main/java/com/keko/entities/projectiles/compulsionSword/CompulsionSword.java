package com.keko.entities.projectiles.compulsionSword;

import com.keko.CyraFinal;
import com.keko.packet.StarParticlesGeneralPayload;
import com.keko.sounds.ModSounds;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import foundry.veil.api.quasar.data.ParticleSettings;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;


public class CompulsionSword extends PersistentProjectileEntity {
    Light light;
    boolean wasAdded = false;

    public CompulsionSword(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        light = new PointLight().setRadius(50f).setBrightness(3f).setColor((float) 255 / 255, (float) 255 / 255, (float) 200 / 255);

    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }

    @Override
    public void onRemoved() {
        if (this.getWorld().isClient){
            VeilRenderSystem.renderer().getLightRenderer().removeLight(light);

        }
        super.onRemoved();
    }

    @Override
    public boolean isGlowing() {
        return true;
    }



    @Override
    protected float getDragInWater() {
        return 1f;
    }

    private void launchPlayers() {
        int distance = 30;
        Box box = new Box(getX() + distance, getY() + distance, getZ() + distance, getX() - distance, getY() - distance, getZ() - distance);
        for (Entity entity : getEntityWorld().getEntitiesByClass(Entity.class, box, Entity::isAlive)){
            if (!(entity instanceof CompulsionSword)){
                double length = this.distanceTo(entity);
                entity.addVelocity(entity.getPos().subtract(this.getPos()).normalize().multiply((distance - length < 0 ? 0 : distance - length)));
                entity.velocityModified = true;
                if (entity != this.getOwner())
                    entity.damage(getWorld().getDamageSources().generic(), (float) 40 / (entity instanceof PlayerEntity ? (float) ((PlayerEntity) entity).getArmor() / 3 : 1));
            }
        }
    }

    public static void spawnParticle(Entity entity, Identifier id, double x, double y, double z){
        try {
            ParticleSystemManager manager = VeilRenderSystem.renderer().getParticleManager();
            ParticleEmitter emitter = manager.createEmitter(id);
            emitter.setPosition(x,y,z);
            emitter.setParticleSettings(new ParticleSettings(5f, 0.0f, 0.05f,
                    100, 0f,
                    new Vector3f(
                            entity.getWorld().random.nextFloat() - .5f * 5f,
                            entity.getWorld().random.nextFloat() - .5f * 5f,
                            entity.getWorld().random.nextFloat() - .5f * 5f),
                    true,false,false,true,false));
            manager.addParticleSystem(emitter);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

    }

    @Override
    public boolean isNoClip() {
        return true;
    }

    @Override
    protected @Nullable EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient && getOwner()!= null){
            ((PointLight)light).setPosition(this.getX(), this.getY(), this.getZ());
            if (!wasAdded) {
                VeilRenderSystem.renderer().getLightRenderer().addLight(light);
                wasAdded = true;
            }

        }
        if (!this.getWorld().isClient){
            System.out.println(age);
            if (getOwner() == null)
                discard();

            if (age < 50){
                this.setVelocity(this.getVelocity().multiply(0.8f));
                velocityModified = true;
            }else {
                this.setVelocity(this.getVelocity().add(0, -1.1f, 0));
                velocityModified = true;
            }

            if (this.getVelocity().y < -9){
                try {
                    for (PlayerEntity players : getWorld().getPlayers()){
                        ServerPlayNetworking.send((ServerPlayerEntity) players, new StarParticlesGeneralPayload(getX(), getY(), getZ()));
                    }
                } catch (Exception ignored) {
                }
               // spawnParticle(this.getOwner(), Identifier.of(CyraFinal.MOD_ID, "star_burst"), getX(), getY(), getZ());
                getWorld().playSound(this, this.getBlockPos(), ModSounds.COMPULSION_SWORD_BLAST, SoundCategory.PLAYERS, 10, 1);
                launchPlayers();

                this.discard();
            }

        }
    }
}
