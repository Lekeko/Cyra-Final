package com.keko.entities.projectiles.FOTOL;

import com.keko.particle.ModParticles;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FistOfTheOldLord extends PersistentProjectileEntity {
    public FistOfTheOldLord(EntityType<? extends FistOfTheOldLord> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {

        super.onSpawnPacket(packet);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public void tick() {
        if (!getWorld().isClient && age > 5)
            discard();

        if (!getWorld().isClient){
            Vec3d vec3d = this.getVelocity();

            Vec3d vec3d3 = this.getPos();
            Vec3d vec3d2 = vec3d3.add(vec3d);
            EntityHitResult entityHitResult = this.getEntityCollision(vec3d3, vec3d2);
            if (entityHitResult != null){
                if (entityHitResult.getEntity() != null && entityHitResult.getEntity() != this.getOwner())
                    if (entityHitResult.getEntity() instanceof LivingEntity)
                        if (!((LivingEntity)entityHitResult.getEntity()).isBlocking())
                            entityHitResult.getEntity().damage(this.getWorld().getDamageSources().generic(), 10);
                        else ((LivingEntity)entityHitResult.getEntity()).damageShield(4);
                if (entityHitResult.getEntity() != this.getOwner()) {
                        entityHitResult.getEntity().velocityModified = true;
                }
            }
        }else {
            if (age < 2)
                getWorld().addParticle(ModParticles.PYRITE_BOOM, this.getX(), this.getY(), this.getZ(), 0,0,0);
        }
        super.tick();
    }

    @Override
    public boolean isNoClip() {
        return true;
    }


    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }
}
