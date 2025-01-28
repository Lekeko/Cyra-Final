package com.keko.entities.bosses.oldLord;

import com.keko.blocks.ModBlocks;
import com.keko.entities.ModEntities;
import com.keko.entities.bosses.skeletonLeader.SkeletonLeaderEntity;
import com.keko.entities.projectiles.FOTOL.FistOfTheOldLord;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.entities.projectiles.lordStar.LordStarEntity;
import com.keko.entities.projectiles.oldLordsSpear.OldLordsSpearEntity;
import com.keko.entities.projectiles.pyriteCube.PCube;
import com.keko.packet.ParryOldLordPayload;
import com.keko.particle.ModParticles;
import com.keko.sounds.ModSounds;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
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

import java.awt.*;

public class OldLordEntity extends HostileEntity implements GeoEntity {

    public int attackCanceled = 0;
    Light light = new PointLight().setColor(1,1,1).setRadius(5).setBrightness(1f);
    private int attackTimer = 60;
    protected int comboPhase = 0;
    protected boolean inCombo = false;
    protected boolean fistAttacking = false;
    protected int spearAttack = 0;
    private final int FIST_NUMBER = 80;
    private int fists = 0;
    private BlockPos heartPos = null;
    private final int STARS = 200;
    private int currentStars= 0;
    private final ServerBossBar bossBar;

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private boolean airattacks = false;

    public OldLordEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.YELLOW, BossBar.Style.PROGRESS));
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
        super.onSpawnPacket(packet);
        getWorld().playSound(null, this.getBlockPos(), ModSounds.OLD_LORD_SPAWN, SoundCategory.HOSTILE, 10, 1);

        if (getWorld().isClient) {
            for (int i = 0; i < 200; i++)
                getWorld().addParticle(ParticleTypes.END_ROD, this.getX(), this.getY(), this.getZ(), (random.nextFloat() - 0.5) * 10, (random.nextFloat() - 0.5) * 10, (random.nextFloat() - 0.5) *  10);
            VeilRenderSystem.renderer().getLightRenderer().addLight(light);
        }
    }

    public static DefaultAttributeContainer.Builder setAtributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1500)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 13)
                .add(EntityAttributes.GENERIC_ARMOR, 8)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1);

    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        super.initGoals();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        AnimationController<OldLordEntity> controller = new AnimationController<>(this, "controller", 0, this::predicate);
        controller.triggerableAnim("old_lord.walk", RawAnimation.begin().thenPlay("old_lord.walk"));
        controller.triggerableAnim("old_lord.attack_combo_1", RawAnimation.begin().thenPlayAndHold("old_lord.attack_combo_1"));
        controller.triggerableAnim("old_lord.attack_combo_2", RawAnimation.begin().thenPlayAndHold("old_lord.attack_combo_2"));
        controller.triggerableAnim("old_lord.attack_combo_3", RawAnimation.begin().thenPlayAndHold("old_lord.attack_combo_3"));
        controller.triggerableAnim("old_lord.fists", RawAnimation.begin().thenPlay("old_lord.fists"));
        controller.triggerableAnim("old_lord.air_attack", RawAnimation.begin().thenPlay("old_lord.air_attack"));
        controller.triggerableAnim("old_lord.spear_attack", RawAnimation.begin().thenPlay("old_lord.spear_attack"));

        controllers.add(controller);
    }

    public void setHeartPos(BlockPos heartPos) {
        this.heartPos = heartPos;
    }

    @Override
    public void tick() {
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

        if (!getWorld().isClient){
            if (age % 20 == 0){
                checkPlayers();
            }

            if (this.age < 2)
                getWorld().playSound(null, this.getBlockPos(), ModSounds.OLD_LORD_SPAWN, SoundCategory.HOSTILE, 10, 1);


            if (this.heartPos == null)
                discard();
            if (attackCanceled > 0) {
                attackCanceled--;
            }
            attackTimer--;
            if (attackTimer <= 0) {
                int randomAttack = getWorld().getRandom().nextBetween(0,100);
                if (((randomAttack > 0 && randomAttack < 50) || inCombo) && !fistAttacking && !airattacks) {
                    inCombo = true;
                    comboAttack();
                    randomAttack = 0;
                }

                if (((randomAttack > 50 && randomAttack < 70) || inCombo) && !fistAttacking && !airattacks && !inCombo) {
                    throwSpear(false);
                    randomAttack = 0;
                    spearAttack = 20;

                }

                if ((randomAttack >= 70 && randomAttack < 87) && !airattacks && !inCombo) {

                    fistAttack();
                }

                if ((randomAttack >= 87 && randomAttack < 100) || airattacks){
                    airAttack();
                }

            }
            if (fistAttacking)
                fistAttack();

            if (airattacks)
                airAttack();

            if (spearAttack > 0) {
                spearAttack--;
                if (spearAttack == 7)
                    throwSpear(true);

            }

            if (attackTimer < 30 && attackTimer > 20)
                triggerAnim("controller", "old_lord.walk");

        }else {
            getWorld().addParticle(ModParticles.PYRITE_STAR, this.getX(), this.getY() + 3f, this.getZ(), this.getVelocity().x ,this.getVelocity().y, this.getVelocity().z);
            ((PointLight)light).setPosition(this.getX(), this.getY() + 3.5f, this.getZ());
        }


        super.tick();
    }

    private void throwSpear(boolean actual) {
        int area = 40;
        Box box = new Box(getX() + area, this.getY() + area, this.getZ() + area, getX() - area, this.getY() - area, this.getZ() - area);
        PlayerEntity target = null;
        double distance = 9999;
        for (PlayerEntity player : getWorld().getEntitiesByClass(PlayerEntity.class, box, PlayerEntity::isAlive)){
            double testDistance =  Math.sqrt( Math.pow(player.getX() - getX(), 2) + Math.pow(player.getY() - getY(), 2) + Math.pow(player.getZ() - getZ(), 2));
            if (testDistance < distance){
                target = player;
                distance = testDistance;
            }
        }

        if (target != null){
            if (!actual){
                triggerAnim("controller", "old_lord.spear_attack");
                System.out.println("JAP");            attackTimer = 40;
                this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, target.getPos());
                this.setPos(target.getX() + getWorld().random.nextBetween(-5,5), target.getY() + 3, target.getZ() + getWorld().random.nextBetween(-5,5));


            }else {
                OldLordsSpearEntity spearEntity = new OldLordsSpearEntity(ModProjectileEntities.OLD_LORDS_SPEAR_ENTITY_ENTITY_TYPE, getWorld());
                spearEntity.setPos(this.getX(), this.getY(), this.getZ());
                spearEntity.setVelocity(target.getPos().subtract(this.getPos()).multiply(0.5f).add(0,1,0));
                spearEntity.setOwner(this);
                spearEntity.setDamage(20);
                getWorld().spawnEntity(spearEntity);
            }

        }
    }

    private void checkPlayers() {
        Box box = new Box(this.getX() + 100, this.getY() + 50, this.getZ() + 100, this.getX() - 100, this.getY() - 50, this.getZ() - 100);
        if (getWorld().getEntitiesByClass(PlayerEntity.class, box, PlayerEntity::isAlive).isEmpty()){
            getWorld().setBlockState(heartPos, ModBlocks.OLD_LORD_BLOCK.getDefaultState());
            this.discard();
        }
    }

    @Override
    public void onDamaged(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.IN_WALL) && !getWorld().isClient)
            this.heal(1);
        super.onDamaged(damageSource);
    }

    @Override
    public void onRemoved() {
        if (getWorld().isClient)
            VeilRenderSystem.renderer().getLightRenderer().removeLight(light);
        super.onRemoved();
    }

    private void fistAttack() {
        fistAttacking = true;
        fists++;
        if (fists == 1)
            getWorld().playSound(null, this.getBlockPos(), ModSounds.CHARGE_UP, SoundCategory.HOSTILE ,10, 1.3f);

        triggerAnim("controller", "old_lord.fists");
        FistOfTheOldLord fistOfTheOldLord = new FistOfTheOldLord(ModProjectileEntities.FIST_OF_THE_OLD_LORD, getWorld());


        PlayerEntity target = null;
        double distance = 9999;
        int area = 15;
        Box box = new Box(getX() + area, this.getY() + area, this.getZ() + area, getX() - area, this.getY() - area, this.getZ() - area);

        for (PlayerEntity player : getWorld().getEntitiesByClass(PlayerEntity.class, box, PlayerEntity::isAlive)){
            double testDistance =  Math.sqrt( Math.pow(player.getX() - getX(), 2) + Math.pow(player.getY() - getY(), 2) + Math.pow(player.getZ() - getZ(), 2));
            if (testDistance < distance){
                target = player;
                distance = testDistance;
            }
        }


        double xOffset = random.nextBetween(1,3);
        double zOffset = random.nextBetween(1,3);

        Vec3d playerPos = this.getPos();
        float playerYaw = this.getYaw();

        double leftX = playerPos.x + xOffset * Math.cos(Math.toRadians(playerYaw)) - zOffset * Math.sin(Math.toRadians(playerYaw));
        double leftZ = playerPos.z + xOffset * Math.sin(Math.toRadians(playerYaw)) + zOffset * Math.cos(Math.toRadians(playerYaw));

        double rightX = playerPos.x - xOffset * Math.cos(Math.toRadians(playerYaw)) - zOffset * Math.sin(Math.toRadians(playerYaw));
        double rightZ = playerPos.z - xOffset * Math.sin(Math.toRadians(playerYaw)) + zOffset * Math.cos(Math.toRadians(playerYaw));

        if (getWorld().random.nextBoolean()){
            fistOfTheOldLord.setPos(leftX, this.getY(), leftZ);
            fistOfTheOldLord.setNoGravity(true);
            fistOfTheOldLord.setPos(leftX, this.getY() + 2, leftZ);

        }else {
            fistOfTheOldLord.setPos(rightX, this.getY(), rightZ);
            fistOfTheOldLord.setNoGravity(true);
            fistOfTheOldLord.setPos(rightX, this.getY() + 2, rightZ);
        }

        if (target != null) {
            this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, target.getPos());
            Vec3d direction = target.getRotationVec(1.0f);
            direction = direction.multiply(6f);
            Vec3d pos = new Vec3d(target.getX() + direction.getX(), target.getY(), target.getZ() + direction.getZ());
            this.setPos(pos);
            fistOfTheOldLord.setVelocity(target.getPos().subtract(fistOfTheOldLord.getPos()));
            if (hasSpaceAbove())
                target.setVelocity(0,0.3d, 0);
            this.setVelocity(target.getVelocity().multiply(0.3f));
            this.velocityModified = true;
        }


        fistOfTheOldLord.setVelocity(fistOfTheOldLord.getVelocity().multiply(0.3f));
        attackTimer = 1000;
        if (fists > 15) {
            fistOfTheOldLord.setOwner(this);
            getWorld().spawnEntity(fistOfTheOldLord);
            getWorld().playSound(null, getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_HARP.value(), SoundCategory.HOSTILE, 1,2);
        }

        if (fists > FIST_NUMBER) {
            attackTimer = 40;
            fistAttacking = false;
            fists = 0;
        }
    }

    private boolean hasSpaceAbove() {
        for (int i = 0; i < 7; i++)
            if (!this.getWorld().getBlockState(this.getBlockPos().add(0,i,0)).isOf(Blocks.AIR))
                return false;

        return true;
    }

    private void airAttack() {
        airattacks= true;
        attackTimer = 200;
        spawnFallingStars();
        this.setPos(heartPos.getX(), heartPos.getY() + 2, heartPos.getZ());
        currentStars++;
        if (currentStars > STARS)
        {
            currentStars = 0;
            airattacks = false;
            attackTimer = 40;
        }
    }

    private void spawnFallingStars() {
        int area  = 70;
        Box box = new Box(getX() + area, this.getY() + area, this.getZ() + area, getX() - area, this.getY() - area, this.getZ() - area);
        PlayerEntity target = null;
        double distance = 9999;
        for (PlayerEntity player : getWorld().getEntitiesByClass(PlayerEntity.class, box, PlayerEntity::isAlive)){
            double testDistance =  Math.sqrt( Math.pow(player.getX() - getX(), 2) + Math.pow(player.getY() - getY(), 2) + Math.pow(player.getZ() - getZ(), 2));
            if (testDistance < distance){
                target = player;
                distance = testDistance;
            }
        }
        triggerAnim("controller", "old_lord.air_attack");
        if (target != null){
            LordStarEntity arrowEntity = new LordStarEntity(ModProjectileEntities.LORD_STAR, getWorld());
            arrowEntity.setPos(this.getX(), this.getY() + 3, this.getZ());
            arrowEntity.setVelocity(target.getPos().subtract(arrowEntity.getPos()).normalize().multiply(2));
            arrowEntity.setOwner(this);
            getWorld().spawnEntity(arrowEntity);
            this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, target.getPos());
            double testDistance =  Math.sqrt( Math.pow(target.getX() - getX(), 2) + Math.pow(target.getY() - getY(), 2) + Math.pow(target.getZ() - getZ(), 2));
            if (testDistance < 5){
                target.setVelocity(target.getPos().subtract(this.getPos()).normalize().multiply(2));
                target.velocityModified = true;
            }

        }
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }





    private void comboAttack() {
        if (comboPhase == 0){
            comboPhase++;
            getWorld().playSound(null, this.getBlockPos(), ModSounds.OLD_LORD_COMBO_HIT, SoundCategory.HOSTILE ,10, 111f);
            attackTimer = 10;
            inCombo = true;
            return;
        }
        int area  = 20;
        Box box = new Box(getX() + area, this.getY() + area, this.getZ() + area, getX() - area, this.getY() - area, this.getZ() - area);
        PlayerEntity target = null;
        double distance = 9999;
        for (PlayerEntity player : getWorld().getEntitiesByClass(PlayerEntity.class, box, PlayerEntity::isAlive)){
            double testDistance =  Math.sqrt( Math.pow(player.getX() - getX(), 2) + Math.pow(player.getY() - getY(), 2) + Math.pow(player.getZ() - getZ(), 2));
            if (testDistance < distance){
                target = player;
                distance = testDistance;
            }
        }

        if (target != null){
            Vec3d direction = target.getRotationVec(1.0f);
            Vec3d pos = new Vec3d(target.getX() + direction.getX(), target.getY() + direction.getY(), target.getZ() + direction.getZ());
            this.setPos(pos.add(0,5,0));
            this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, target.getPos());

            triggerAnim("controller", "old_lord.attack_combo_" + comboPhase++);
            if (attackCanceled <= 0) {
                target.damage(getWorld().getDamageSources().generic(), 30 / ((float) target.getArmor() / 12));
                getWorld().playSound(null, this.getBlockPos(), ModSounds.OLD_LORD_COMBO_HIT, SoundCategory.HOSTILE ,1, 0.2f + getWorld().getRandom().nextFloat()/2);

            }
            else {
                getWorld().playSound(null, this.getBlockPos(), ModSounds.PARRY, SoundCategory.PLAYERS, 1, getWorld().random.nextFloat() + 0.7f);
                this.damage(getWorld().getDamageSources().generic(), 15 );
                ServerPlayNetworking.send((ServerPlayerEntity) target, new ParryOldLordPayload(0));
            }
            target.velocityModified = true;
            this.setVelocity(target.getVelocity().multiply(0.3f));
            this.velocityModified = true;
            target.setVelocity(target.getX() - this.getX(), 0, target.getZ() - this.getZ());
            target.setVelocity(target.getVelocity().multiply(2f));
            this.setVelocity(target.getVelocity());
            target.addVelocity(0,1,0);

            target.velocityModified = true;
            this.velocityModified = true;
            if (comboPhase > 3) {
                comboPhase = 0;
                attackTimer = 40;
                inCombo = false;
            }else {
                attackTimer = 10;
                inCombo = true;
            }
        }
    }

    @Override
    public boolean hasNoGravity() {
        return false;
    }

    private void setPos(Vec3d pos) {
        setPos(pos.x, pos.y, pos.z);
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (!inCombo)
            tAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("old_lord.walk"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
