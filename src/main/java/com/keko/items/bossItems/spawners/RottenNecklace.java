package com.keko.items.bossItems.spawners;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RottenNecklace extends Item {
    public RottenNecklace(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            spawnBoss(world, user);
        }else {
            world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.HOSTILE, 2,2);
        }

        return super.use(world, user, hand);
    }

    private void spawnBoss(World world, PlayerEntity user) {

    }
}
