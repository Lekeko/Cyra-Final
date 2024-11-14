package com.keko.entities.projectiles.seaBolt;

import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.items.ModItems;
import com.keko.particle.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SeaBolt extends PersistentProjectileEntity {


    public int ticksExisted = 0;

    public SeaBolt(EntityType<? extends SeaBolt> entityType, World world) {
        super(entityType, world);
    }

    public SeaBolt(World world, LivingEntity entity, @Nullable ItemStack stack){
        super(ModProjectileEntities.SEA_BOLT_ENTITY_TYPE, entity, world, stack, stack);
    }


    @Override
    protected float getDragInWater() {
        return 0.9f;
    }

    @Override
    public boolean isSubmergedInWater() {
        return false;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient){
            this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_WATER, SoundCategory.NEUTRAL, 0.5F, 0.4F / (this.getWorld().getRandom().nextFloat() * 0.4F + 0.8F));

            entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 4f);
            this.discard();
        }
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.discard();
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return ModItems.SEA_BOLT.getDefaultStack();
    }

    @Override
    public void tick() {
        World world = this.getWorld();


        if (this.getWorld().isClient && this.age > 1){
            spawnPartilce(this.getWorld());
        }else {
            this.ticksExisted++;
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
        world.addParticle(ModParticles.WATER_BOLT_PARTICLE_TYPE,
                this.getX(),
                this.getY(),
                this.getZ(),
                -this.getVelocity().x
                ,-this.getVelocity().y
                ,-this.getVelocity().z );
    }

}
