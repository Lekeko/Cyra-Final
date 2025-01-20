package com.keko.items.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;

public class SkeletonLeaderBattleBowitem extends BowItem {
    public SkeletonLeaderBattleBowitem(Settings settings) {
        super(settings);
    }
    @Override
    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        projectile.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yaw, 0.0F, speed, divergence);
        projectile.setVelocity(projectile.getVelocity().multiply(5f));
    }
}
