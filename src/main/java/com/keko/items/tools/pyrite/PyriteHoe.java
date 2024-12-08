package com.keko.items.tools.pyrite;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PyriteHoe extends SwordItem {
    
    
    public PyriteHoe(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial,  settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        if (!world.isClient){
            if (world.getBlockState(blockPos).isOf(Blocks.GRASS_BLOCK) || world.getBlockState(blockPos).isOf(Blocks.DIRT)){
                world.setBlockState(blockPos, Blocks.FARMLAND.getDefaultState());
                context.getStack().damage(1, Objects.requireNonNull(context.getPlayer()), LivingEntity.getSlotForHand(context.getHand()));
            }
        }
        return ActionResult.PASS;
    }
}
