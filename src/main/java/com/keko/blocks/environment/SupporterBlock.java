package com.keko.blocks.environment;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class SupporterBlock extends Block {

    public static int age;


    public SupporterBlock(Settings sounds) {
        super(sounds);
        age = 0;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient){
            age++;
            if (age > 200){
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }

        super.randomTick(state, world, pos, random);
    }
}
