package com.keko.entities.bosses.zombieLeader;

import com.keko.game.SlamAttackpayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.util.Random;

public class ZombieLeaderEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static float distanceToTarget = -1;
    private long lastAttackTime = 0; // Track the last attack time


    public ZombieLeaderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }



    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        for (int i = 0; i < 200; i++){
            java.util.Random random = new java.util.Random();
            this.getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, true, packet.getX()+0.5f, packet.getY()+1.5f, packet.getZ()+0.5f,
                    -1 + random.nextFloat() * 2,  -1 + random.nextFloat() * 2,  -1 + random.nextFloat() * 2);
        }
        super.onSpawnPacket(packet);
    }



    public static DefaultAttributeContainer.Builder setAtributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.50f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.3f);

    }






    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(4, new MeleeAttackGoal(this, (double)1.0F, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        super.initGoals();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        AnimationController<ZombieLeaderEntity> controller = new AnimationController<>(this, "controller", 0, this::predicate);
        controller.triggerableAnim("zombie_leader.attack1", RawAnimation.begin().thenPlay("zombie_leader.attack1"));
        controller.triggerableAnim("zombie_leader.attack1", RawAnimation.begin().thenPlay("zombie_leader.attack_slam"));

        // Add the controller to the registrar
        controllers.add(controller);
    }


    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null && this.squaredDistanceTo(this.getTarget()) < 2.0) {
            if (this.getWorld().getTime() - lastAttackTime >= 20) {
                triggerAnim("controller", "zombie_leader.attack1");
                lastAttackTime = this.getWorld().getTime();
            }
        }


    }



    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("zombie_leader.walk"));
            return PlayState.CONTINUE;
        }

        tAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("zombie_leader.idle"));
        return PlayState.CONTINUE;
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
