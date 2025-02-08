package com.keko.helpers;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class CyraBoxHelper {
    public static Box createBox(BlockPos origin, int x, int y, int z){
        return new Box(origin.getX() + x, origin.getY() + y, origin.getZ() + z, origin.getX() - x, origin.getY() - y, origin.getZ() - z);
    }
}
