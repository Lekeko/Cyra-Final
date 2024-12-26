package com.keko.entities.bosses.skeletonLeader;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.system.linux.Stat;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.util.List;

public class SkeletonLeaderEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int attackCooldown = 0;

    public SkeletonLeaderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        for (int i = 0; i < 200; i++){
            java.util.Random random = new java.util.Random();
            this.getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, true, packet.getX()+0.5f, packet.getY()+1.5f, packet.getZ()+0.5f,
                    -1 + random.nextFloat() * 2,  -1 + random.nextFloat() * 2,  -1 + random.nextFloat() * 2);
        }
        if (!this.getWorld().isClient){
            attackCooldown= this.getWorld().random.nextBetween(20, 40);
        }
        super.onSpawnPacket(packet);
    }

    public static DefaultAttributeContainer.Builder setAtributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20f);

    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(5, new WanderAroundGoal(this, 0.8));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        super.initGoals();
    }


    @Override
    public void tick() {
        super.tick();
        attackCooldown--;
        if (!getWorld().isClient && attackCooldown <= 0){
            prepareNextAttack();
        }
    }

    private void prepareNextAttack() {
        int nextAttack = random.nextBetween(1, 20);

        switch (nextAttack){
            case 1, 16 -> prepareNormalAttack();
            case 17 -> prepareSummonAttack();
            case 18, 20 -> premareAirAttack();
        }
    }

    private void premareAirAttack() {
        triggerAnim("controller", ("skeleton_leader.attack_rain"));

        for (int i = 0; i < 100; i++){
            PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(7);
            float x = random.nextFloat() * 2 - 1;
            int y = 1;
            float z = random.nextFloat() * 2 - 1;
            persistentProjectileEntity.setVelocity(x / 4, y, z / 4);
            this.getWorld().spawnEntity(persistentProjectileEntity);
        }

        attackCooldown = this.getWorld().getRandom().nextBetween(30, 40);
    }

    private void prepareSummonAttack() {
        triggerAnim("controller", ("skeleton_leader.summon_army"));

        for (int i = 0; i < 3; i++){
            double x = this.getX() +  getWorld().random.nextBetween(1,3);
            double z = this.getZ() +  getWorld().random.nextBetween(1,3);
            SkeletonEntity entity = new SkeletonEntity(EntityType.SKELETON, getWorld());
            entity.setStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1000, 10), entity);
            entity.setStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1000, 2), entity);
            entity.setPosition(x, this.getY(), z);
            getWorld().spawnEntity(entity);
        }

        attackCooldown = this.getWorld().getRandom().nextBetween(30, 40);

    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    private void prepareNormalAttack() {
        int area_size = 20;

        Box areaOfSearch = new Box(this.getX() + area_size, this.getY() + area_size, this.getZ() + area_size, this.getX() - area_size, this.getY() - area_size, this.getZ() - area_size);
        int x = this.getWorld().random.nextBetween(-6, 6);
        int z = this.getWorld().random.nextBetween(-6, 6);

        List<PlayerEntity> players = (this.getWorld().getEntitiesByClass(PlayerEntity.class, areaOfSearch, Entity::isAlive));


        BlockPos pos = null;
        if (!players.isEmpty()) {
            PlayerEntity player = players.getFirst();
            pos = new BlockPos(player.getBlockX() + x, player.getBlockY(), player.getBlockZ() + z);
            this.setPosition(Vec3d.of(pos));
            int animation = random.nextBetween(1, 2);
            this.setPitch(player.getPitch());
            this.setYaw(player.getYaw());
            triggerAnim("controller", ("skeleton_leader.attack" + animation));
            spawnArrow(player);

        }
        attackCooldown = this.getWorld().getRandom().nextBetween(10, 20);

    }

    private void spawnArrow(PlayerEntity player) {
        PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(7);
        double d = player.getX() - this.getX();
        double e = player.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
        double f = player.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        persistentProjectileEntity.setVelocity(d, e + g * (double)0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
        this.getWorld().spawnEntity(persistentProjectileEntity);
    }

    protected PersistentProjectileEntity createArrowProjectile(float damageModifier) {
        return ProjectileUtil.createArrowProjectile(this, Items.ARROW.getDefaultStack(), damageModifier, null);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        AnimationController<SkeletonLeaderEntity> controller = new AnimationController<>(this, "controller", 0, this::predicate);
        controller.triggerableAnim("skeleton_leader.attack1", RawAnimation.begin().thenPlay("skeleton_leader.attack1"));
        controller.triggerableAnim("skeleton_leader.attack2", RawAnimation.begin().thenPlay("skeleton_leader.attack2"));
        controller.triggerableAnim("skeleton_leader.summon_army", RawAnimation.begin().thenPlay("skeleton_leader.summon_army"));
        controller.triggerableAnim("skeleton_leader.attack_rain", RawAnimation.begin().thenPlay("skeleton_leader.attack_rain"));

        // Add the controller to the registrar
        controllers.add(controller);
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) { 
            tAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("skeleton_leader.walk"));
            return PlayState.CONTINUE;
        }

        tAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("skeleton_leader.idle"));
        return PlayState.CONTINUE;
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
