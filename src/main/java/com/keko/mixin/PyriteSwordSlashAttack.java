package com.keko.mixin;

import com.keko.items.ModItems;
import com.keko.particle.ModParticles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)

public class PyriteSwordSlashAttack {

    @Inject(method="spawnSweepAttackParticles", at=@At("HEAD"), cancellable = true)
    private void onAttackSlash(CallbackInfo ci) {
        PlayerEntity value = (PlayerEntity) (Object) this;
        if (value.getMainHandStack().isOf(ModItems.PYRITE_SWORD) || value.getMainHandStack().isOf(ModItems.PYRITE_HOE) ){
            double d = (double)(-MathHelper.sin(value.getYaw() * ((float)Math.PI / 180F)));
            double e = (double)MathHelper.cos(value.getYaw() * ((float)Math.PI / 180F));
            if (value.getWorld() instanceof ServerWorld) {
                ((ServerWorld)value.getWorld()).spawnParticles(ModParticles.PYRITE_SLASH, value.getX() + d, value.getBodyY((double)0.5F), value.getZ() + e, 0, d, (double)0.0F, e, (double)0.0F);
            }
            ci.cancel();
        }
    }

}
