package com.keko.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public abstract class Directional {
	Block[] blocks = {
		Blocks.AIR,
		Blocks.WATER,
		Blocks.VOID_AIR,
		Blocks.SNOW,
		Blocks.FIRE,
		Blocks.TALL_GRASS,
		Blocks.POPPY,
		Blocks.DANDELION,
		Blocks.BLUE_ORCHID,
		Blocks.ALLIUM,
		Blocks.AZURE_BLUET,
		Blocks.RED_TULIP,
		Blocks.ORANGE_TULIP,
		Blocks.WHITE_TULIP,
		Blocks.PINK_TULIP,
		Blocks.OXEYE_DAISY,
		Blocks.CORNFLOWER,
		Blocks.LILY_OF_THE_VALLEY,
		Blocks.WITHER_ROSE,
		Blocks.BROWN_MUSHROOM_BLOCK,
		Blocks.RED_MUSHROOM,
		Blocks.SUNFLOWER,
		Blocks.LILAC,
		Blocks.ROSE_BUSH,
		Blocks.PEONY,
		Blocks.LARGE_FERN,
	};
	//I KNOW HOW IT LOOKS LIKE I KNOW I KNWO AAAAAAAAAAAA

	public static Vec3i rayCast(World world, Entity player, Vec3d direction, int distance){
		Vec3i vec3d = new Vec3i((int)player.getX(), (int)player.getY(), (int)player.getZ());
		for (double i = 0.1; i <= distance; i+= 0.1){
			BlockState state = world.getBlockState(new BlockPos(new Vec3i(vec3d.getX(), vec3d.getY(), vec3d.getZ())));
			if (!state.isOf(Blocks.AIR) && !state.isOf(Blocks.WATER) && !state.isOf(Blocks.VOID_AIR)){
				return vec3d;
			}else {
				vec3d = new Vec3i((int)
					(direction.getX() * i + player.getX()),
						(int)(direction.getY() * i + player.getY() + 1.75) ,
						(int)(direction.getZ() * i + player.getZ()));
			}
		}
		System.out.println(vec3d);
		return vec3d;

	}


}
