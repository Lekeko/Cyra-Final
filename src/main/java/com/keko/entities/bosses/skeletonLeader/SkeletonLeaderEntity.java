package com.keko.entities.bosses.skeletonLeader;

import com.keko.packet.SkeletonLeaderTeleportPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.util.List;

public class SkeletonLeaderEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int attackCooldown = 0;
    int attack = 1;
    private BlockPos TeleporPos;
    private  PlayerEntity target;
    private final ServerBossBar bossBar;

    public SkeletonLeaderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.WHITE, BossBar.Style.PROGRESS));

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
        if (!this.getWorld().isClient){
            attackCooldown= this.getWorld().random.nextBetween(20, 40);
        }
        super.onSpawnPacket(packet);
    }

    public static DefaultAttributeContainer.Builder setAtributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 700)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20f);

    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(5, new WanderAroundGoal(this, 0.8));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        super.initGoals();
    }


    @Override
    public void tick() {
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

        super.tick();
        attackCooldown--;
        if (!getWorld().isClient && attackCooldown <= 0){
            prepareNextAttack();
        }
    }

    private void prepareNextAttack() {
        int nextAttack = random.nextBetween(1, 100);
        if (nextAttack >= 81 && nextAttack <= 90 && attack != 3) attack = 2;
        switch (attack){
            case 1 -> prepareNormalAttack();
            case 2 -> premareAirAttack();
            case 3 -> NormalAttack();
        }
    }

    private void NormalAttack() {
        if (TeleporPos != null)
        {
            this.setPosition(Vec3d.of(TeleporPos));
            int animation = random.nextBetween(1, 2);
            this.setPitch(target.getPitch());
            this.setYaw(target.getYaw());
            triggerAnim("controller", ("skeleton_leader.attack" + animation));
            spawnArrow(target);
            attackCooldown = this.getWorld().getRandom().nextBetween(10, 20);
        }
        attack = 1;
        TeleporPos = null;

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
        attack = 1;
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



        List<PlayerEntity> players = (this.getWorld().getEntitiesByClass(PlayerEntity.class, areaOfSearch, Entity::isAlive));


        BlockPos pos = null;
        if (!players.isEmpty()) {
            PlayerEntity player = players.getFirst();

            pos = findTeleportablePos(player);
            for (int i = 0; i < 10; i++){
                java.util.Random random = new java.util.Random();
                ServerPlayNetworking.send((ServerPlayerEntity) player, new SkeletonLeaderTeleportPayload(pos.getX(), pos.getY(), pos.getZ()));
                getWorld().playSound(null, pos, SoundEvents.ENTITY_SKELETON_AMBIENT, SoundCategory.HOSTILE, 0.3421f, 1);
            }

            TeleporPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            target = player;
        }
        attackCooldown = this.getWorld().getRandom().nextBetween(10, 20);
        attack = 3;


    }

    private BlockPos findTeleportablePos(PlayerEntity player) {
        int x,z;
        BlockPos pos = player.getBlockPos();
        int attempts = 0;

        do {
            x = random.nextBetween(-5, 5);
            z= random.nextBetween(-5, 5);
            pos = new BlockPos(player.getBlockX() + x, player.getBlockY(), player.getBlockZ() + z);
            int maxDepth = 3;
            if (getWorld().getBlockState(pos).isOf(Blocks.AIR))
                while (getWorld().getBlockState(pos).isOf(Blocks.AIR) && maxDepth > 0){
                pos= pos.down();
                maxDepth--;
                    if (getWorld().getBlockState(pos).isOf(Blocks.WATER) || getWorld().getBlockState(pos).isOf(Blocks.LAVA))
                        break;
                }

            attempts ++;

        }while (!getWorld().getBlockState(pos).isSolidBlock(getWorld().getChunkAsView(pos.getX() / 16, pos.getZ() / 16), pos) && attempts < 6);


        return pos;


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
