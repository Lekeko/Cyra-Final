package com.keko.mixin;

import com.keko.CyraFinal;
import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import com.keko.world.biome.ModBiomes;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerCharmMixin {
    @Unique
    PlayerEntity player = (PlayerEntity) (Object) this;

    @Inject(method = "tick", at=@At("HEAD"))
    private void tickerCharm(CallbackInfo ci){
        if (!player.getWorld().isClient && (player.getWorld().getBiome(player.getBlockPos()).matchesKey(ModBiomes.VOID_WATERS) ||
                player.getWorld().getBiome(player.getBlockPos()).matchesKey(ModBiomes.DAZED_WATERS)||
                player.getWorld().getBiome(player.getBlockPos()).matchesKey(ModBiomes.MURIEL_WATERS))){
            if (player.getY() <= 300 && player.getY() > 200)
                checkForCharm(1);
            if (player.getY() <= 200 && player.getY() > 100)
                checkForCharm(2);
            if (player.getY() <= 100 && player.getY() > 0)
                checkForCharm(3);
        }

        if ((player.getWorld().getBiome(player.getBlockPos()).matchesKey(ModBiomes.VOID_WATERS))){
            checkForCharm(3);
            if (player.hasStatusEffect(StatusEffects.NIGHT_VISION)){
                player.clearStatusEffects();
                if (!player.getWorld().isClient)
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,200,1));
            }
        }


        if (!player.getWorld().isClient && InvSearch.hasO2(player) != null){
            if (player.isSubmergedInWater()) {
                InvSearch.hasO2(player).damage(1, player, null);
                player.setAir(player.getMaxAir());
            }else  InvSearch.hasO2(player).damage(-10, player, null);

        }
    }

    @Unique
    private void checkForCharm(int i) {
        if (player.isSubmergedInWater())
            switch (i){
            case 1->
            {
                ItemStack stack1 = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_1);
                ItemStack stack2 = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_2);
                ItemStack stack3 = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_3);
                if (stack2 == null && stack3 == null && stack1 == null) {
                    player.damage(player.getWorld().getDamageSources().generic(), 4);
                }
            }
            case 2->{
                ItemStack stack1 = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_2);
                ItemStack stack2 = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_3);
                if (stack1 == null && stack2 == null) {
                    player.addVelocity(player.getVelocity().getX(), 2, player.getVelocity().z);
                    player.damage(player.getWorld().getDamageSources().generic(), 5);
                }
            }
            case 3->{
                ItemStack stack = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_3);
                if (stack == null) {
                    player.addVelocity(player.getVelocity().getX(), 2, player.getVelocity().z);
                    player.damage(player.getWorld().getDamageSources().generic(), 6);
                }
            }
        }
    }


}
