package com.keko.blocks.environment.specialBlocks;

import com.keko.entities.ModEntities;
import com.keko.entities.bosses.oldLord.OldLordEntity;
import com.keko.items.ModItems;
import com.keko.sounds.ModSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TheOldLordSpawnerBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    private int ticks = 0;


    public TheOldLordSpawnerBlock(AbstractBlock.Settings settings) {
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

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.playSound(null, pos, ModSounds.OLD_LORD_TICK, SoundCategory.HOSTILE, 5, 0.4f);
        if (!world.isClient) {
            ticks++;
            if (ticks == 5){
                spawnBoss(world, pos);
                ticks = 0;
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        world.scheduleBlockTick(pos, this, 30);

        super.scheduledTick(state, world, pos, random);
    }

    private void spawnBoss(ServerWorld world, BlockPos pos) {
        OldLordEntity oldLord = new OldLordEntity(ModEntities.OLD_LORD_ENTITY_ENTITY_TYPE, world);
        oldLord.setPos(pos.getX() + .5, pos.getY() + 1, pos.getZ() + .5);
        oldLord.setHeartPos(pos);
        world.spawnEntity(oldLord);

    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient){
            if (player.getMainHandStack().isOf(ModItems.ANCIENT_TIT_FER)){
                player.getMainHandStack().decrementUnlessCreative(1, player);
                world.scheduleBlockTick(pos, this, 30);
            }
        }

        return super.onUse(state, world, pos, player, hit);
    }

    private void beginSpawningSequence(PlayerEntity player, World world, BlockState state, BlockPos pos) {
    }
}
