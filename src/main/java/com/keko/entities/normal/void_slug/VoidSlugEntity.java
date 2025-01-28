package com.keko.entities.normal.void_slug;

import com.keko.entities.normal.bottom_stalker.BottomStalkerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

public class VoidSlugEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public VoidSlugEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAtributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.2f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.50f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.3f);

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
        AnimationController<VoidSlugEntity> controller = new AnimationController<>(this, "controller", 0, this::predicate);
        controller.triggerableAnim("void_slug.crawl", RawAnimation.begin().thenPlay("void_slug.crawl"));

        controllers.add(controller);
    }

    private PlayState predicate(AnimationState<VoidSlugEntity> seaRodentEntityAnimationState) {
        seaRodentEntityAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("void_slug.crawl"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
