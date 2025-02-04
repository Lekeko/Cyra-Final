package com.keko.items.tools;

import com.keko.blocks.ModBlocks;
import com.keko.midnightLibConfigs.MidnightConfigCyra;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SeaWandItem extends Item {
    public SeaWandItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){

            BlockPos blockPos = new BlockPos((int) Math.floor(user.getX()), (int) (Math.floor(user.getY()) - 1), (int) Math.floor(user.getZ()));
            if (world.getBlockState(blockPos).isOf(Blocks.AIR) || world.getBlockState(blockPos).isOf(Blocks.WATER)) {
                world.setBlockState(blockPos, ModBlocks.SUPPORTER.getDefaultState());
                user.getItemCooldownManager().set(user.getStackInHand(hand).getItem(), MidnightConfigCyra.cooldown_sea_wand * 20);
            }
        }
        return super.use(world, user, hand);
    }
}
