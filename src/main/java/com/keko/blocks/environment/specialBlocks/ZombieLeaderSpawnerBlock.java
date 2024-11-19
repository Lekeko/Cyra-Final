package com.keko.blocks.environment.specialBlocks;

import com.keko.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ZombieLeaderSpawnerBlock extends Block {
    public final int ARENA_SIZE = 8;
    public static int currentSizeBuilt = 1;
    public ZombieLeaderSpawnerBlock(Settings settings) {
        super(settings);
    }

    Block[] arenaBlocks = new Block[]{
            Blocks.STONE_BRICKS,
            Blocks.STONE_BRICKS,
            Blocks.STONE_BRICKS,
            Blocks.MOSSY_STONE_BRICKS,
            Blocks.MOSSY_STONE_BRICKS,
            Blocks.CRACKED_STONE_BRICKS,
            Blocks.CRACKED_STONE_BRICKS,
            Blocks.STONE_BRICKS,
            Blocks.STONE_BRICK_STAIRS
    };

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (currentSizeBuilt < ARENA_SIZE) {

            for (int i = -currentSizeBuilt; i <= currentSizeBuilt; i++) {
                placeBlock(state, world, new BlockPos(pos.getX() + currentSizeBuilt, pos.getY() - 1, pos.getZ() + i));
            }
            for (int i = -currentSizeBuilt; i <= currentSizeBuilt; i++) {
                placeBlock(state, world, new BlockPos(pos.getX() - currentSizeBuilt, pos.getY() - 1, pos.getZ() + i));
            }

            for (int i = -currentSizeBuilt; i <= currentSizeBuilt; i++) {
                placeBlock(state, world, new BlockPos(pos.getX() + i, pos.getY() - 1, pos.getZ() + currentSizeBuilt));
            }
            for (int i = -currentSizeBuilt; i <= currentSizeBuilt; i++) {
                placeBlock(state, world, new BlockPos(pos.getX() + i, pos.getY() - 1, pos.getZ() - currentSizeBuilt));
            }
            currentSizeBuilt++;
            world.scheduleBlockTick(pos, this, 20);
        }else {
            //I DONT CARE HOW IT LOOKS
            placePillars(world, new BlockPos(pos.getX() - currentSizeBuilt, pos.getY() - 1, pos.getZ() - 1));
            placePillars(world, new BlockPos(pos.getX() - currentSizeBuilt, pos.getY() - 1, pos.getZ() + 1));
            placePillars(world, new BlockPos(pos.getX() + currentSizeBuilt, pos.getY() - 1, pos.getZ() - 1));
            placePillars(world, new BlockPos(pos.getX() + currentSizeBuilt, pos.getY() - 1, pos.getZ() + 1));
            placePillars(world, new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() - currentSizeBuilt));
            placePillars(world, new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ() - currentSizeBuilt));
            placePillars(world, new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() + currentSizeBuilt));
            placePillars(world, new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ() + currentSizeBuilt));
            currentSizeBuilt = 1;

        }


        super.scheduledTick(state, world, pos, random);
    }

    private void placePillars(ServerWorld world, BlockPos pos) {
        world.setBlockState(pos, Blocks.CHISELED_STONE_BRICKS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1 , pos.getZ()), Blocks.CHISELED_STONE_BRICKS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 2 , pos.getZ()), Blocks.CHISELED_STONE_BRICKS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 3 , pos.getZ()), Blocks.CHISELED_STONE_BRICKS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 4 , pos.getZ()), Blocks.NETHERRACK.getDefaultState());
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        long currentTime = world.getTimeOfDay(); long nightTime = 13000;
        long ticksToAdd = nightTime - (currentTime % 24000);
        world.setTimeOfDay(currentTime + ticksToAdd);
        lightning.setPosition(pos.getX(), pos.getY() + 5, pos.getZ());
        world.spawnEntity(lightning);

    }

    private void placeBlock(BlockState state, ServerWorld world, BlockPos pos) {

        int randomNumber = new java.util.Random().nextInt(5);

        world.setBlockState(pos, arenaBlocks[randomNumber].getDefaultState());
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient){
            if (player.getMainHandStack().isOf(ModItems.ROTTEN_NECKLACE)){
                player.getMainHandStack().decrementUnlessCreative(1, player);
                beginSpawningSequence(player, world, state, pos);
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    private void beginSpawningSequence(PlayerEntity player, World world, BlockState state,  BlockPos pos) {
        world.scheduleBlockTick(pos, this, 20);
    }
}
