package com.keko.mixin;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkRocketItem.class)
public abstract class ElytraNerfMixin {

    @Inject(method="use", at=@At("HEAD"), cancellable = true)
    private  void onUseTick(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable ci) {
        if (user.isFallFlying()){
            if (!world.isClient){
                ci.cancel();
            }
        }
    }
}