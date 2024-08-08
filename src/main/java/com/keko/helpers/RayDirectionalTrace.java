package com.keko.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class RayDirectionalTrace {
    public static Vec3d rayCast(World world, Entity player, Vec3d direction, int distance){
        Vec3d vec3d = new Vec3d(player.getX(), player.getY(), player.getZ());
        for (double i = 0.1; i <= distance; i+= 0.1){
            BlockState state = world.getBlockState(new BlockPos(new Vec3i((int)vec3d.z, (int)vec3d.y, (int)vec3d.z)));
            if (!state.isOf(Blocks.AIR) && !state.isOf(Blocks.WATER) && !state.isOf(Blocks.VOID_AIR) && state.isSolidBlock(world, new BlockPos(new Vec3i((int)vec3d.z, (int)vec3d.y, (int)vec3d.z)))){
                return vec3d;
            }else {
                vec3d = new Vec3d(
                        direction.getX() * i + player.getX(),
                        direction.getY() * i + player.getY() + 1.75 ,
                        direction.getZ() * i + player.getZ());
            }
        }
        return vec3d;

    }
}
