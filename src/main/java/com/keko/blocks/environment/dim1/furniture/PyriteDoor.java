package com.keko.blocks.environment.dim1.furniture;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;

public class PyriteDoor extends DoorBlock {
    public PyriteDoor(BlockSetType type, Settings settings) {
        super(type, settings);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

}
