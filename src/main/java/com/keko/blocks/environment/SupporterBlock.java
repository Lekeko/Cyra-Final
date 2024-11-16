package com.keko.blocks.environment;

import com.keko.particle.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IceBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SupporterBlock extends Block {



    public SupporterBlock(Settings sounds) {
        super(sounds);
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 100);
        System.out.println(world);
        world.playSound((PlayerEntity)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));



        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        for (int i = 0; i < 30; i++){
            java.util.Random random = new java.util.Random();
            world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, true, pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f,
                    random.nextFloat() - .5, random.nextFloat() - .5, random.nextFloat() - .5);
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }




    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient){
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        world.spawnParticles(ModParticles.WATER_BOLT_PARTICLE_TYPE, pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f, 1,0,0, 0, 0);
        world.playSound((PlayerEntity)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        super.scheduledTick(state, world, pos, random);
    }


    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override

    protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }


    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

}
