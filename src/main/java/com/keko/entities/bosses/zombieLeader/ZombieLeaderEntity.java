package com.keko.entities.bosses.zombieLeader;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

public class ZombieLeaderEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private long lastAttackTime = 0;
    private long slamAttackTime = 200;
    private long summonAttackTime = 300;
    private final ServerBossBar bossBar;


    public ZombieLeaderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS));
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
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
        controller.triggerableAnim("zombie_leader.attack_slam", RawAnimation.begin().thenPlay("zombie_leader.attack_slam"));
        controller.triggerableAnim("zombie_leader.summon_army", RawAnimation.begin().thenPlay("zombie_leader.summon_army"));

        // Add the controller to the registrar
        controllers.add(controller);
    }


    @Override
    public void tick() {
        super.tick();
        if (slamAttackTime > 0 && isAttacking())
            slamAttackTime--;

        if (slamAttackTime < 50 && slamAttackTime > 40){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 100, true, false));
        }

        if (slamAttackTime >= 10 && slamAttackTime <= 11){
            triggerAnim("controller", "zombie_leader.attack_slam");

        }


        if (summonAttackTime >  0 &&  isAttacking())
            summonAttackTime--;

        if (summonAttackTime > 19 && summonAttackTime <= 21){
            triggerAnim("controller", "zombie_leader.summon_army");

        }


        if (this.getTarget() != null){
            if (this.squaredDistanceTo(this.getTarget()) < 2.0) {
                if (this.getWorld().getTime() - lastAttackTime >= 20) {
                    triggerAnim("controller", "zombie_leader.attack1");
                    lastAttackTime = this.getWorld().getTime();
                }
            }


        }
        if (slamAttackTime == 0){
            damageAllOnGround();
        }

        if (summonAttackTime == 0){
            summonArmy();
        }
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

    }

    private void summonArmy() {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        int minions = 3;

        for (int i = 0; i < minions; i++){
            ZombieEntity zombieEntity = new ZombieEntity(getWorld());
            double xx = x + random.nextBetween(-3, 3);
            double yy = y;
            double zz = z + random.nextBetween(-3, 3);
            zombieEntity.setPos(xx, yy, zz);
            for (int j = 0; j < 30; j++){
                java.util.Random random = new java.util.Random();
                this.getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, true, xx, yy, zz,
                        -1 + random.nextFloat() * 2,  -1 + random.nextFloat() * 2,  -1 + random.nextFloat() * 2);
            }
            getWorld().spawnEntity(zombieEntity);
        }
        summonAttackTime = 300 + random.nextBetween(50, 100);


    }

    private void damageAllOnGround() {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        double distanceToDamage = 10;

        Box box = new Box(x + distanceToDamage, y + distanceToDamage / 3, z + distanceToDamage
        ,                 x - distanceToDamage,y - distanceToDamage / 3, z - distanceToDamage );
        if (!getWorld().isClient) {
            for (PlayerEntity player : this.getWorld().getEntitiesByClass(PlayerEntity.class, box, Entity::isAlive)) {

                if (player.isOnGround()) {
                    player.damage(getWorld().getDamageSources().thrown(this, this), 9);
                    player.setVelocity(0f, 1f, 0f);
                    player.velocityModified = true;
                }
            }
        }else for (double i = -distanceToDamage; i < distanceToDamage; i+=.90)
                for (double j = -distanceToDamage; j < distanceToDamage; j+=.90){
                    double absi = Math.abs(i);
                    double absj = Math.abs(j);
                    if (Math.pow(absi, 2) + Math.pow(absj, 2) <= distanceToDamage*distanceToDamage)
                        getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, true, x + i, y, z + j, random.nextFloat() - .5 , random.nextFloat() , random.nextFloat() - .5);
                }
        slamAttackTime = 200 + random.nextBetween(50, 100);
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
