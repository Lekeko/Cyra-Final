package com.keko.entities.projectiles.compulsionAxe;

import com.keko.packet.StarParticlesGeneralPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class CompulsionAxe  extends PersistentProjectileEntity {
    public CompulsionAxe(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.DIAMOND.getDefaultStack();//in your dreams ;3
    }

    @Override
    public boolean isNoClip() {
        return  true;
    }

    @Override
    public void tick() {
        if (!getWorld().isClient){
            if (age > 60) discard();
            damageEnemies();
        }

        super.tick();
    }


    private void damageEnemies() {
        int distance = 6;
        Box box = new Box(getX() + distance, getY()+2, getZ() + distance, getX() - distance, getY() -2, getZ() - distance);
        for (Entity entity : getWorld().getEntitiesByClass(Entity.class, box, Entity::isAlive)){
            if (entity != this.getOwner()) {
                entity.addVelocity(entity.getPos().subtract(this.getPos()).normalize());
                entity.velocityModified = true;
                entity.damage(getWorld().getDamageSources().generic(), 23 - (entity instanceof PlayerEntity ? (float) ((((PlayerEntity) entity).getArmor()) / 5) : 0));
            }
        }
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return false;
    }
}
