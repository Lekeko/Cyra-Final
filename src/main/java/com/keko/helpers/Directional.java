package com.keko.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public abstract class Directional {

		public static Vec3d rayCast(World world, Entity player, Vec3d direction, double distance) {
			Vec3d startPos = player.getEyePos();
			Vec3d endPos = startPos.add(direction.normalize().multiply(distance));

			EntityHitResult entityHitResult = ProjectileUtil.raycast(
					player,
					startPos,
					endPos,
					player.getBoundingBox().stretch(direction.multiply(distance)).expand(1.0),
					(target) -> !target.isSpectator() && target.canHit(),
					distance * distance
			);

			RaycastContext context = new RaycastContext(
					startPos,
					endPos,
					RaycastContext.ShapeType.OUTLINE,
					RaycastContext.FluidHandling.NONE,
					player
			);
			BlockHitResult blockHitResult = world.raycast(context);

			if (entityHitResult != null) {
				Vec3d entityHitPos = entityHitResult.getPos();
				double entityDist = startPos.squaredDistanceTo(entityHitPos);

				if (blockHitResult.getType() == HitResult.Type.MISS || entityDist <= startPos.squaredDistanceTo(blockHitResult.getPos())) {
					return entityHitPos;
				}
			}

			if (blockHitResult.getType() != HitResult.Type.MISS)
				return blockHitResult.getPos();

			return endPos;
		}
	}

