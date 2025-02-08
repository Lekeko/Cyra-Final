package com.keko.mixin;

import com.keko.entities.bosses.oldLord.OldLordEntity;
import com.keko.helpers.CyraBoxHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class OldLordNullifyFallDamageMixin {
    @Unique
    PlayerEntity player = (PlayerEntity) (Object) this;

    @Inject(method="handleFallDamage", at=@At("HEAD"), cancellable = true)
    private void nope(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
        boolean choice = isOldLordNearby(player);
        if (choice){
            cir.cancel();
        }
    }

    @Unique
    private boolean isOldLordNearby(PlayerEntity player) {
        Box area = CyraBoxHelper.createBox(player.getBlockPos(), 20, 20, 20);
        return !player.getWorld().getEntitiesByClass(OldLordEntity.class, area, Entity::isAlive).isEmpty();
    }
}
