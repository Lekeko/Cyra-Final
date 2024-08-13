package com.keko.items.tools;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HealingFlask extends Item {

    float healing_amount = 3; // Hearts
    int cooldown = 30; //seconds
    public HealingFlask(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setHealth(user.getHealth() + healing_amount * 2);
        ItemStack itemStack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 20 * cooldown);
        return super.use(world, user, hand);
    }
}
