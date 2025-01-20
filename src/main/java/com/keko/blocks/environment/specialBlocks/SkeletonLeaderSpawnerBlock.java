package com.keko.blocks.environment.specialBlocks;

import com.keko.entities.ModEntities;
import com.keko.entities.bosses.skeletonLeader.SkeletonLeaderEntity;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import com.keko.items.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SkeletonLeaderSpawnerBlock extends BlockWithEntity implements BlockEntityProvider {
    public final int ARENA_SIZE = 20;
    public static int currentSizeBuilt = 1;
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public SkeletonLeaderSpawnerBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }




    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    Block[] arenaBlocks = new Block[]{
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.BONE_BLOCK,
            Blocks.GOLD_BLOCK,
    };
    //if you dont like how it looks go cry me a river

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        if (currentSizeBuilt < ARENA_SIZE) {
            world.setBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), Blocks.CHISELED_STONE_BRICKS.getDefaultState());

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
            world.scheduleBlockTick(pos, this, 5);
        }else {
            //I DONT CARE HOW IT LOOKS
            placePillars(world, new BlockPos(pos.getX() - currentSizeBuilt, pos.getY() - 1, pos.getZ() - 1), null);
            placePillars(world, new BlockPos(pos.getX() - currentSizeBuilt, pos.getY() - 1, pos.getZ() + 1), null);
            placePillars(world, new BlockPos(pos.getX() + currentSizeBuilt, pos.getY() - 1, pos.getZ() - 1), null);
            placePillars(world, new BlockPos(pos.getX() + currentSizeBuilt, pos.getY() - 1, pos.getZ() + 1), null);
            placePillars(world, new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() - currentSizeBuilt), null);
            placePillars(world, new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ() - currentSizeBuilt), null);
            placePillars(world, new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() + currentSizeBuilt), null);
            placePillars(world, new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ() + currentSizeBuilt) , pos);
            for (int i = -currentSizeBuilt; i <= currentSizeBuilt; i++) {
                placeBlock(state, world, new BlockPos(pos.getX() + currentSizeBuilt, pos.getY() - 1, pos.getZ() + i), true);
            }
            for (int i = -currentSizeBuilt; i <= currentSizeBuilt; i++) {
                placeBlock(state, world, new BlockPos(pos.getX() - currentSizeBuilt, pos.getY() - 1, pos.getZ() + i), true);
            }

            for (int i = -currentSizeBuilt; i <= currentSizeBuilt; i++) {
                placeBlock(state, world, new BlockPos(pos.getX() + i, pos.getY() - 1, pos.getZ() + currentSizeBuilt), true);
            }
            for (int i = -currentSizeBuilt; i <= currentSizeBuilt; i++) {
                placeBlock(state, world, new BlockPos(pos.getX() + i, pos.getY() - 1, pos.getZ() - currentSizeBuilt), true);
            }
            currentSizeBuilt = 1;

        }


        super.scheduledTick(state, world, pos, random);
    }

    private void placePillars(ServerWorld world, BlockPos pos, BlockPos origin) {
        world.setBlockState(pos, Blocks.CHISELED_STONE_BRICKS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1 , pos.getZ()), Blocks.BONE_BLOCK.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 2 , pos.getZ()), Blocks.BONE_BLOCK.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 3 , pos.getZ()), Blocks.BONE_BLOCK.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 4 , pos.getZ()), Blocks.NETHERRACK.getDefaultState());
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        long currentTime = world.getTimeOfDay(); long nightTime = 13000;
        long ticksToAdd = nightTime - (currentTime % 24000);
        world.setTimeOfDay(currentTime + ticksToAdd);
        lightning.setPosition(pos.getX(), pos.getY() + 5, pos.getZ());
        world.spawnEntity(lightning);
        if (origin != null)
            spawnBoss(world, origin);

    }

    private void spawnBoss(ServerWorld world, BlockPos pos) {
        SkeletonLeaderEntity skeletonLeader = new SkeletonLeaderEntity(ModEntities.SKELETON_LEADER, world);
        skeletonLeader.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        world.spawnEntity(skeletonLeader);
    }

    private void placeBlock(BlockState state, ServerWorld world, BlockPos pos) {

        int randomNumber = new java.util.Random().nextInt(arenaBlocks.length);

        world.setBlockState(pos, arenaBlocks[randomNumber].getDefaultState());
    }
    private void placeBlock(BlockState state, ServerWorld world, BlockPos pos, boolean border) {

        int randomNumber = new java.util.Random().nextInt(5);

        world.setBlockState(pos, Blocks.CHISELED_STONE_BRICKS.getDefaultState());
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient){
            if (player.getMainHandStack().isOf(ModItems.GILDED_SPINE)){
                player.getMainHandStack().decrementUnlessCreative(1, player);
                beginSpawningSequence(player, world, state, pos);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * (ARENA_SIZE - 14), 30));
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    private void beginSpawningSequence(PlayerEntity player, World world, BlockState state,  BlockPos pos) {
        world.scheduleBlockTick(pos, this, 5);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
