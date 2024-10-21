package com.keko.items.tools;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SeaCrystalStriders extends Item {
    public SeaCrystalStriders(Settings settings) {
        super(settings);
    }

    double boost = 2.0d;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            user.setVelocity(user.getVelocity().multiply(boost));
            user.velocityModified = true;
        }
        return super.use(world, user, hand);
    }
}
