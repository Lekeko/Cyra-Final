package com.keko.entities.projectiles.oldLordsSpear;

import com.keko.midnightLibConfigs.MidnightConfigCyra;
import com.keko.sounds.ModSounds;
import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
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
    public void tick() {
        if (!getWorld().isClient && age > 200) discard();
        super.tick();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!getWorld().isClient && getOwner() != null){
            launchEntities();
            getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1,1);
        }else {
            for (int i = 0; i < 50; i++)
                getWorld().addParticle(ParticleTypes.END_ROD, getX(), getY(), getZ(),
                    (getWorld().random.nextFloat() - 0.5) * 3,
                    (getWorld().random.nextFloat() - 0.5) * 3,
                    (getWorld().random.nextFloat() - 0.5) * 3);
        }
        super.onBlockHit(blockHitResult);
    }

    private void launchEntities() {
        int area = 5;
        Box box = new Box(getX() + area, getY() + area, getZ() + area, getX() - area, getY() - area, getZ() - area);
        for (Entity entity : getWorld().getEntitiesByClass(Entity.class, box, Entity::isAlive)){
            if (entity instanceof PlayerEntity)
                if (((PlayerEntity)entity).isFallFlying())
                    entity.addVelocity(entity.getPos().subtract(this.getPos()).normalize().multiply(2f).multiply(MidnightConfigCyra.nerf_elytra_boost_spear ? 0.2f : 1));
            if (entity != getOwner())
                entity.damage(getWorld().getDamageSources().generic(), 15 - (entity instanceof PlayerEntity ? (float) ((PlayerEntity) entity).getArmor() / 4 : 0));
            entity.velocityModified = true;
        }
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return false;
    }
}
