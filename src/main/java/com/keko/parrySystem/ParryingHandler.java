package com.keko.parrySystem;

import com.keko.items.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;

public class ParryingHandler {


    public static boolean hasPyriteWeaponInHand(PlayerEntity player, Hand hand) {
        return player.getStackInHand(hand).isOf(ModItems.PYRITE_HOE) || player.getStackInHand(hand).isOf(ModItems.PYRITE_SWORD) || player.getStackInHand(hand).isOf(ModItems.PYRITE_AXE);
    }
}
