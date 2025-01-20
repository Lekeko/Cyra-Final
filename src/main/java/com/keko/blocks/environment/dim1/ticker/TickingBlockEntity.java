package com.keko.blocks.environment.dim1.ticker;

import com.keko.blocks.ModBlockEntity;
import com.keko.blocks.ModBlocks;
import com.keko.helpers.TickableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TickingBlockEntity extends BlockEntity implements TickableBlockEntity {
    private int ticks = 0;
    public TickingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.EXAMPLE_TICKING_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void tick() {
        assert world != null;

        generatePillar(world, pos, this.getCachedState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), ModBlocks.SEA_CRYSTAL_BRICKS_WALL.getDefaultState().with(Properties.WATERLOGGED, true));


    }
    private void generatePillar(World world, BlockPos pos, BlockState state) {
        int distance = 250;

        for (int i = 1; i < distance ; i++){
            if (!world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ())).isOf(Blocks.WATER)) {
                break;
            }
            world.setBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), ModBlocks.SEA_CRYSTAL_BRICKS_WALL.getDefaultState().with(Properties.WATERLOGGED, true));
        }
    }
}
