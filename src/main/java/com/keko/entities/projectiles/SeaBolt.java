package com.keko.entities.projectiles;

import com.keko.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class SeaBolt extends ThrownItemEntity {


    public SeaBolt(EntityType<? extends SeaBolt> entityType, World world) {
        super(entityType, world);
    }

    public SeaBolt(World world, LivingEntity owner) {
        super(ModProjectileEntities.SEA_BOLT_ENTITY_TYPE, owner, world);
    }

    public SeaBolt(World world, double x, double y, double z) {
        super(ModProjectileEntities.SEA_BOLT_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient){
            this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_WATER, SoundCategory.NEUTRAL, 0.5F, 0.4F / (this.getWorld().getRandom().nextFloat() * 0.4F + 0.8F));

            entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 4f);
        }
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onBlockCollision(BlockState state) {

        super.onBlockCollision(state);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_WATER, SoundCategory.NEUTRAL, 0.5F, 0.4F / (this.getWorld().getRandom().nextFloat() * 0.4F + 0.8F));

        super.onBlockHit(blockHitResult);
    }

    @Override
    public void tick() {
        World world = this.getWorld();


        if (this.getWorld().isClient){
            spawnPartilce(world);
        }else {
            if (this.isTouchingWater()) {
                this.setVelocity(this.getVelocity().multiply(1.23f));

            }
        }
        super.tick();
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }


    private void spawnPartilce(World world) {
        world.addParticle(ParticleTypes.SOUL_FIRE_FLAME,
                this.getX(),
                this.getY(),
                this.getZ(),0,0,0);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SEA_BOLT;
    }
}
