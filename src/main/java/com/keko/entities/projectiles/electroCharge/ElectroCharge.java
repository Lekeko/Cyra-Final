package com.keko.entities.projectiles.electroCharge;

import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.items.ModItems;
import com.keko.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class ElectroCharge extends PersistentProjectileEntity {
    public ElectroCharge(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ElectroCharge(World world, LivingEntity entity){
        super(ModProjectileEntities.ELECTRO_CHARGE, entity, world, ModItems.ELECTRO_CHARGE.getDefaultStack(), ModItems.ELECTRO_CHARGE.getDefaultStack());
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return ModItems.ELECTRO_CHARGE.getDefaultStack();
    }

    @Override
    protected float getDragInWater() {
        return 1;
    }

    @Override
    public void tick() {
        super.tick();
        if (!getWorld().isClient){
            if (inGround){
                ElectroDamager electro1 = new ElectroDamager(ModProjectileEntities.ELECTRO, getWorld());
                electro1.setPos(this.getX(), this.getY(), this.getZ());
                getWorld().spawnEntity(electro1);
                for (int i = -1; i <= 1; i+=2)
                    for (int j = -1; j <= 1; j+=2)
                        for (int k = -1; k <= 1; k+=2){
                            Electro electro = new Electro(ModProjectileEntities.ELECTRO, getWorld());
                            electro.setPos(this.getX(), this.getY(), this.getZ());
                            electro.setVelocity((double) i /random.nextBetween(1, 2), (float) j /random.nextBetween(1, 2), (float) k /random.nextBetween(1, 2));
                            getWorld().spawnEntity(electro);
                        }
                if (!getWorld().isClient){
                    getWorld().playSound(this, this.getBlockPos(), ModSounds.ELECTRO_CHARGE, SoundCategory.PLAYERS, 3, 1);
                }
                discard();
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!getWorld().isClient){
            ElectroDamager electro1 = new ElectroDamager(ModProjectileEntities.ELECTRO, getWorld());
            electro1.setPos(this.getX(), this.getY(), this.getZ());
            getWorld().spawnEntity(electro1);

            for (int i = -1; i <= 1; i+=2)
                    for (int j = -1; j <= 1; j+=2)
                        for (int k = -1; k <= 1; k+=2){
                            Electro electro = new Electro(ModProjectileEntities.ELECTRO, getWorld());
                            electro.setPos(this.getX(), this.getY(), this.getZ());
                            electro.setVelocity((double) i /random.nextBetween(1, 2), (float) j /random.nextBetween(1, 2), (float) k /random.nextBetween(1, 2));
                            getWorld().spawnEntity(electro);
                        }
            if (!getWorld().isClient){
                getWorld().playSound(this, this.getBlockPos(), ModSounds.ELECTRO_CHARGE, SoundCategory.PLAYERS, 10, 1);
            }
                discard();
        }
        super.onEntityHit(entityHitResult);
    }
}
