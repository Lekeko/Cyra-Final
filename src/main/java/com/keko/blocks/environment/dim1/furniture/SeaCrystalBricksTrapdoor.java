package com.keko.blocks.environment.dim1.furniture;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class SeaCrystalBricksTrapdoor extends TrapdoorBlock {
    public SeaCrystalBricksTrapdoor(BlockSetType type, Settings settings) {
        super(type, settings);
    }
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


}
