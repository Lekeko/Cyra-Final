package com.keko.entities.projectiles.oldLordsSpear;

import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class OldLordsSpearEntity extends PersistentProjectileEntity {
    public OldLordsSpearEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }


    @Override
    public double getDamage() {
        return 6f;
    }

    @Override
    protected float getDragInWater() {
        return 1f;
    }


    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return false;
    }
}
