package com.keko.entities.bosses.zombieLeader;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.dedicated.gui.PlayerStatsGui;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

public class ZombieLeaderEntity extends HostileEntity implements GeoEntity {

    String attackOrWalk = "walk";
    public static int timer = 0;
    int radius = 10;

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private boolean shouldSlam = false;
    int slamTimer = 260;
    int countdown_for_slam_damage = 10;

    public ZombieLeaderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    Box area = new Box(
            this.getX() - radius, this.getY() - radius, this.getZ() - radius,
            this.getX() + radius, this.getY() + radius, this.getZ() + radius
    );

    public static DefaultAttributeContainer.Builder setAtributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.0f);

    }



    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        super.initGoals();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public void tick() {
        if (this.getWorld().isClient){
            timer--;
            if (slamTimer > 0)
                slamTimer--;
            else
                shouldSlam = true;

            if (shouldSlam) {
                if (countdown_for_slam_damage > 0) {
                    countdown_for_slam_damage--;
                } else damageAllOnGround();
            }
            System.out.println("Timer until slaming : " + slamTimer);
            System.out.println("Can slam = " + shouldSlam);
            System.out.println("Until Damage = " + countdown_for_slam_damage);
        }



        if (timer < 0 )
            attackOrWalk = "walk";
        super.tick();
    }

    public void damageAllOnGround() {
        this.getWorld().getEntitiesByClass(Entity.class, area, entity -> true).forEach(entity -> {
            if (entity.isOnGround())
                entity.damage(new DamageSource((RegistryEntry<DamageType>) DamageTypes.THROWN), 6);
        });
        if (this.getWorld().isClient){
            shouldSlam = false;
            slamTimer = 260;
            countdown_for_slam_damage = 10;
        }

    }


    @Override
    public void onPlayerCollision(PlayerEntity player) {
        this.attackOrWalk = "attack1";
        timer = 20;
        super.onPlayerCollision(player);
    }




    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {

         if (shouldSlam)
        {
            tAnimationState.getController().setAnimation(RawAnimation.begin().thenPlay("zombie_leader.attack_slam"));
            countdown_for_slam_damage = 10;

        }else
            if (this.getVelocity().x != 0 || this.getVelocity().z != 0) {
                tAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("zombie_leader." + attackOrWalk));


            } else {
                tAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("zombie_leader.idle"));

            }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
