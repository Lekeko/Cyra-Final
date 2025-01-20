package com.keko.entities.normal.jellyfish;

import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import com.keko.items.ModItems;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;

public class JellyFishEntity extends WaterCreatureEntity implements GeoEntity {

    private int attackTimer = 0;
    public float tiltAngle;
    public float prevTiltAngle;
    public float rollAngle;
    public float prevRollAngle;
    public float thrustTimer;
    public float prevThrustTimer;
    public float tentacleAngle;
    public float prevTentacleAngle;
    private float swimVelocityScale;
    private float thrustTimerSpeed;
    private float turningSpeed;
    private float swimX;
    private float swimY;
    private float swimZ;
    Color color = new Color(68, 205, 255, 255);
    Light electrocutionLight = new PointLight().setBrightness(1).setRadius(2).setColor(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f);
    private int lightDuration = 0;

    public JellyFishEntity(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
        this.random.setSeed((long)this.getId());
        this.thrustTimerSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;

    }
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static DefaultAttributeContainer.Builder setAtributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40)
                .add(EntityAttributes.GENERIC_ARMOR, 5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.50f);

    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new JellyFishEntity.SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, (double)1.0F, false));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));

        super.initGoals();
    }

    @Override
    public void onRemoved() {
        if (getWorld().isClient){
            VeilRenderSystem.renderer().getLightRenderer().removeLight(electrocutionLight);
        }
        super.onRemoved();
    }

    @Override
    public void tick() {
        this.setAir(300);
        if (!getWorld().isClient){
            attackTimer++;
            if (attackTimer > 40){
                attackNearestPlayer();
                attackTimer = 0;
            }
        }else {
            ((PointLight)electrocutionLight).setPosition(this.getX(), this.getY(), this.getZ());
        }



        super.tick();
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        if (getWorld().isClient)
            VeilRenderSystem.renderer().getLightRenderer().addLight(electrocutionLight);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        this.prevTiltAngle = this.tiltAngle;
        this.prevRollAngle = this.rollAngle;
        this.prevThrustTimer = this.thrustTimer;
        this.prevTentacleAngle = this.tentacleAngle;
        this.thrustTimer += this.thrustTimerSpeed;
        if ((double)this.thrustTimer > (Math.PI * 2D)) {
            if (this.getWorld().isClient) {
                this.thrustTimer = ((float)Math.PI * 2F);
            } else {
                this.thrustTimer -= ((float)Math.PI * 2F);
                if (this.random.nextInt(10) == 0) {
                    this.thrustTimerSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.getWorld().sendEntityStatus(this, (byte)19);
            }
        }

        if (this.isInsideWaterOrBubbleColumn()) {
            if (this.thrustTimer < (float)Math.PI) {
                float f = this.thrustTimer / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;
                if ((double)f > (double)0.75F) {
                    this.swimVelocityScale = 1.0F;
                    this.turningSpeed = 1.0F;
                } else {
                    this.turningSpeed *= 0.8F;
                }
            } else {
                this.swimVelocityScale *= 0.9F;
                this.turningSpeed *= 0.99F;
            }

            if (!this.getWorld().isClient) {
                this.setVelocity((double)(this.swimX * this.swimVelocityScale), (double)(this.swimY * this.swimVelocityScale), (double)(this.swimZ * this.swimVelocityScale));
            }

            Vec3d vec3d = this.getVelocity();
            double d = vec3d.horizontalLength();
            this.bodyYaw += (-((float)MathHelper.atan2(vec3d.x, vec3d.z)) * (180F / (float)Math.PI) - this.bodyYaw) * 0.1F;
            this.setYaw(this.bodyYaw);
            this.rollAngle += (float)Math.PI * this.turningSpeed * 1.5F;
            this.tiltAngle += (-((float)MathHelper.atan2(d, vec3d.y)) * (180F / (float)Math.PI) - this.tiltAngle) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.thrustTimer)) * (float)Math.PI * 0.25F;
            if (!this.getWorld().isClient) {
                double e = this.getVelocity().y;
                if (this.hasStatusEffect(StatusEffects.LEVITATION)) {
                    e = 0.05 * (double)(this.getStatusEffect(StatusEffects.LEVITATION).getAmplifier() + 1);
                } else {
                    e -= this.getFinalGravity();
                }

                this.setVelocity((double)0.0F, e * (double)0.98F, (double)0.0F);
            }

            this.tiltAngle += (-90.0F - this.tiltAngle) * 0.02F;
        }

    }

    private void attackNearestPlayer() {
        int searchArea = 10;
        Box box = new Box(getX() + searchArea, getY() + searchArea, getZ() + searchArea, getX() - searchArea, getY() - searchArea,  getZ() - searchArea);
        for (PlayerEntity player : getWorld().getEntitiesByClass(PlayerEntity.class, box, PlayerEntity::isAlive)){
            if (!player.isInCreativeMode()) {
                Vec3d velocity = player.getPos().subtract(getPos()).multiply(0.8f);
                this.setVelocity(velocity);
                break;
            }
        }


    }


    @Override
    public boolean isGlowing() {
        return true;
    }




    public void setSwimmingVector(float x, float y, float z) {
        this.swimX = x;
        this.swimY = y;
        this.swimZ = z;
    }

    public boolean hasSwimmingVector() {
        return this.swimX != 0.0F || this.swimY != 0.0F || this.swimZ != 0.0F;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        AnimationController<JellyFishEntity> controller = new AnimationController<>(this, "controller", 0, this::predicate);
        controller.triggerableAnim("jellyfish.swim", RawAnimation.begin().thenPlay("jellyfish.swim"));

        controllers.add(controller);
    }

    private PlayState predicate(AnimationState<JellyFishEntity> seaRodentEntityAnimationState) {
        seaRodentEntityAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("jellyfish.swim"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    class SwimGoal extends Goal {
        private final JellyFishEntity jellyfish;

        public SwimGoal(final JellyFishEntity jellyfish) {
            this.jellyfish = jellyfish;
        }

        public boolean canStart() {
            return true;
        }

        public void tick() {
            int i = this.jellyfish.getDespawnCounter();
            if (i > 100) {
                this.jellyfish.setSwimmingVector(0.0F, 0.0F, 0.0F);
            } else if (this.jellyfish.getRandom().nextInt(toGoalTicks(50)) == 0 || !this.jellyfish.touchingWater || !this.jellyfish.hasSwimmingVector()) {
                float f = this.jellyfish.getRandom().nextFloat() * ((float)Math.PI * 2F);
                float g = MathHelper.cos(f) * 0.2F;
                float h = -0.1F + this.jellyfish.getRandom().nextFloat() * 0.2F;
                float j = MathHelper.sin(f) * 0.2F;
                this.jellyfish.setSwimmingVector(g, h, j);
            }

        }
    }
}
