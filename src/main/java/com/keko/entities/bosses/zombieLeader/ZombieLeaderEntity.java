package com.keko.entities.bosses.zombieLeader;

import com.keko.game.SlamAttackpayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
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

    String attackOrWalk = "walk";
    public int timer = 0;
    int radius = 10;

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    static private boolean shouldSlam = false;
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
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.3f);

    }

    public static void setClientSlam(boolean b, ClientPlayerEntity player) {
         if (player.getWorld().isClient)shouldSlam = true;
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
        updateBox();
            timer--;
        if (!this.getWorld().isClient && this.isAttacking()) {
            if (slamTimer > 0)
                slamTimer--;
            else
                shouldSlam = true;

            if (shouldSlam) {
                if (countdown_for_slam_damage > 0)
                    countdown_for_slam_damage--;
                else
                    damageAllOnGround();
            }
            Random random1 = new Random();
            int min = 1;
            int max = 1000;
            if (random1.nextInt((max - min) + 1) - min < 2){
                summonAllies(random1);
            }

        }
        attackOrWalk = "walk";
        super.tick();
    }

    private void summonAllies(Random random1) {

        BlockPos pos = this.getBlockPos();
        int min = 1;
        int max = 10;
        for (int i = 0; i < 3; i++){
            ZombieEntity entity = new ZombieEntity(this.getWorld());
            entity.setPos(this.getX() + random1.nextInt((max - min) + 1 ) - min, this.getY(), this.getZ() + random1.nextInt((max - min) + 1 ) - min);
            this.getWorld().spawnEntity(entity);
        }


    }

    private void updateBox() {
        area = new Box(
                this.getX() - radius, this.getY() - radius, this.getZ() - radius,
                this.getX() + radius, this.getY() + radius, this.getZ() + radius
        );
    }

    public void damageAllOnGround() {
        if (!this.getWorld().isClient){
            DamageSource damageSource = new DamageSource(
                    this.getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(DamageTypes.THROWN)
            );

            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 15, 30));

            this.getWorld().getEntitiesByClass(Entity.class, area, entity -> true).forEach(entity -> {
                System.out.println(entity);
                if (entity.isOnGround() && entity instanceof PlayerEntity) {
                    entity.damage(new DamageSource(damageSource.getTypeRegistryEntry()), 4);
                    entity.addVelocity(0, 1.0f, 0);

                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeBoolean(true);


                    ServerPlayNetworking.send((ServerPlayerEntity) entity, new SlamAttackpayload(true));
                }

            });

        }
        shouldSlam = false;
        slamTimer = 260;
        countdown_for_slam_damage = 10;
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
            tAnimationState.getController().setAnimation(RawAnimation.begin().then(("zombie_leader.attack_slam"), Animation.LoopType.PLAY_ONCE));
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
