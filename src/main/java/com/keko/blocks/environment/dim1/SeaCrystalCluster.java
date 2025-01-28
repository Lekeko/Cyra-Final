package com.keko.blocks.environment.dim1;


import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class SeaCrystalCluster extends Block implements Waterloggable {
    public static final MapCodec<SeaCrystalCluster> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(Codec.FLOAT.fieldOf("height").forGetter((block) -> {
            return block.height;
        }), Codec.FLOAT.fieldOf("aabb_offset").forGetter((block) -> {
            return block.xzOffset;
        }), createSettingsCodec()).apply(instance, SeaCrystalCluster::new);
    });
    public static final BooleanProperty WATERLOGGED;
    public static final DirectionProperty FACING;
    private final float height;
    private final float xzOffset;
    protected final VoxelShape northShape;
    protected final VoxelShape southShape;
    protected final VoxelShape eastShape;
    protected final VoxelShape westShape;
    protected final VoxelShape upShape;
    protected final VoxelShape downShape;

    public MapCodec<SeaCrystalCluster> getCodec() {
        return CODEC;
    }

    public SeaCrystalCluster(float height, float xzOffset, AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, false)).with(FACING, Direction.UP));
        this.upShape = Block.createCuboidShape((double)xzOffset, 0.0, (double)xzOffset, (double)(16.0F - xzOffset), (double)height, (double)(16.0F - xzOffset));
        this.downShape = Block.createCuboidShape((double)xzOffset, (double)(16.0F - height), (double)xzOffset, (double)(16.0F - xzOffset), 16.0, (double)(16.0F - xzOffset));
        this.northShape = Block.createCuboidShape((double)xzOffset, (double)xzOffset, (double)(16.0F - height), (double)(16.0F - xzOffset), (double)(16.0F - xzOffset), 16.0);
        this.southShape = Block.createCuboidShape((double)xzOffset, (double)xzOffset, 0.0, (double)(16.0F - xzOffset), (double)(16.0F - xzOffset), (double)height);
        this.eastShape = Block.createCuboidShape(0.0, (double)xzOffset, (double)xzOffset, (double)height, (double)(16.0F - xzOffset), (double)(16.0F - xzOffset));
        this.westShape = Block.createCuboidShape((double)(16.0F - height), (double)xzOffset, (double)xzOffset, 16.0, (double)(16.0F - xzOffset), (double)(16.0F - xzOffset));
        this.height = height;
        this.xzOffset = xzOffset;
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = (Direction)state.get(FACING);
        switch (direction) {
            case NORTH:
                return this.northShape;
            case SOUTH:
                return this.southShape;
            case EAST:
                return this.eastShape;
            case WEST:
                return this.westShape;
            case DOWN:
                return this.downShape;
            case UP:
            default:
                return this.upShape;
        }
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = (Direction)state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction);
    }


    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return state; }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        return (BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER)).with(FACING, ctx.getSide());
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{WATERLOGGED, FACING});
    }


    static {
        WATERLOGGED = Properties.WATERLOGGED;
        FACING = Properties.FACING;
    }
}
