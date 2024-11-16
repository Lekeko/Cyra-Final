package com.keko.blocks.environment.dim1;

import com.keko.blocks.ModBlocks;
import com.keko.world.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class CoreOfTheSeaBlock extends Block {
    public CoreOfTheSeaBlock(Settings settings) {
        super(settings);
    }

    ArrayList<Block> blocksOfPortal = new ArrayList<>();

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient){
            if(canTeleportPlayer(state, world, pos, player, hit)){
                if (!player.isSneaking()) {
                    MinecraftServer server = world.getServer();
                    if (server != null) {
                        if (player instanceof ServerPlayerEntity) {
                            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                            if (world.getRegistryKey() == ModDimensions.MURIEL_KAIA_LEVEL_KEY) {
                                ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                                if (overWorld != null) {
                                    boolean doSetBlock = true;
                                    BlockPos destPos = getDest(player.getBlockPos(), overWorld, false);
                                    for (BlockPos checkPos : BlockPos.iterate(destPos.down(10).west(10).south(10), destPos.up(10).east(10).north(10))) {
                                        if (overWorld.getBlockState(checkPos).getBlock() == ModBlocks.CORE_OF_THE_SEA) {
                                            doSetBlock = false;
                                            break;
                                        }
                                    }

                                    if (destPos.getY() > 310){
                                        destPos = new BlockPos(destPos.getX(), 310, destPos.getZ());
                                    }

                                    if (doSetBlock) {
                                        overWorld.setBlockState(destPos, ModBlocks.CORE_OF_THE_SEA.getDefaultState());
                                    }
                                    serverPlayer.teleport(overWorld, destPos.getX(), destPos.getY(), destPos.getZ(),
                                            serverPlayer.bodyYaw, serverPlayer.prevPitch);
                                }
                            } else {
                                ServerWorld muriel = server.getWorld(ModDimensions.MURIEL_KAIA_LEVEL_KEY);
                                if (muriel != null) {
                                    BlockPos destPos = getDest(serverPlayer.getBlockPos(), muriel, true);
                                    boolean doSetBlock = true;
                                    for (BlockPos checkPos : BlockPos.iterate(destPos.down(10).west(10).south(10), destPos.up(10).east(10).north(10))) {
                                        if (muriel.getBlockState(checkPos).getBlock() == ModBlocks.CORE_OF_THE_SEA) {
                                            doSetBlock = false;
                                            break;
                                        }
                                    }
                                    if (doSetBlock) {
                                        muriel.setBlockState(destPos, ModBlocks.CORE_OF_THE_SEA.getDefaultState());
                                    }
                                    serverPlayer.teleport(muriel, destPos.getX(), destPos.getY(), destPos.getZ(),
                                            serverPlayer.bodyYaw, serverPlayer.prevPitch);
                                }
                            }
                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }
        }

        return super.onUse(state, world, pos, player, hit);
    }
    public static BlockPos getDest(BlockPos pos, World destWorld, boolean isInDimension) {
        double y = 61;

        if (!isInDimension) {
            y = pos.getY();
        }

        BlockPos destPos = new BlockPos(pos.getX(), (int) y, pos.getZ());
        int tries = 0;
        while ((!destWorld.getBlockState(destPos).isAir() && !destWorld.getBlockState(destPos)
                .canBucketPlace(Fluids.WATER)) &&
                (!destWorld.getBlockState(destPos.up()).isAir() && !destWorld.getBlockState(destPos.up())
                        .canBucketPlace(Fluids.WATER)) && tries < 25) {
            destPos = destPos.up(2);
            tries++;
        }

        return destPos;
    }

    private boolean canTeleportPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        boolean firstType = true;

        for (int j = -2; j <= 2 ; j+=4){
            for (int i = pos.getX() - 1; i <= pos.getX() + 1; i++) {
                if (!(world.getBlockState(new BlockPos(i, j+ pos.getY(), pos.getZ())).isOf(Blocks.PRISMARINE) ||
                        world.getBlockState(new BlockPos(i, j+ pos.getY(), pos.getZ())).isOf(Blocks.PRISMARINE_BRICKS) ||
                        world.getBlockState(new BlockPos(i, j+ pos.getY(), pos.getZ())).isOf(Blocks.DARK_PRISMARINE) ||
                        world.getBlockState(new BlockPos(i, j+ pos.getY(), pos.getZ())).isOf(ModBlocks.SEA_CRYSTAL_BRICKS) ||
                        world.getBlockState(new BlockPos(i, j+ pos.getY(), pos.getZ())).isOf(Blocks.SEA_LANTERN))){
                    firstType = false;
                }
            }
        }

        for (int j = -2; j <= 2 ; j+=4){
            for (int i = pos.getY() - 1; i <= pos.getY() + 1; i++) {
                if (!(world.getBlockState(new BlockPos(pos.getX() + j, i, pos.getZ())).isOf(Blocks.PRISMARINE) ||
                        world.getBlockState(new BlockPos(pos.getX() + j, i, pos.getZ())).isOf(Blocks.PRISMARINE_BRICKS) ||
                        world.getBlockState(new BlockPos(pos.getX() + j, i, pos.getZ())).isOf(Blocks.DARK_PRISMARINE) ||
                        world.getBlockState(new BlockPos(pos.getX() + j, i, pos.getZ())).isOf(ModBlocks.SEA_CRYSTAL_BRICKS) ||
                        world.getBlockState(new BlockPos(pos.getX() + j, i, pos.getZ())).isOf(Blocks.SEA_LANTERN))){
                    firstType = false;
                }
            }
        }

        if (firstType)
            return true;

        else {
            for (int j = -2; j <= 2 ; j+=4){
                for (int i = pos.getZ() - 1; i <= pos.getZ() + 1; i++) {
                    if (!(world.getBlockState(new BlockPos(pos.getX(), j+ pos.getY(), i)).isOf(Blocks.PRISMARINE) ||
                            world.getBlockState(new BlockPos(pos.getX(), j+ pos.getY(), i)).isOf(Blocks.PRISMARINE_BRICKS) ||
                            world.getBlockState(new BlockPos(pos.getX(), j+ pos.getY(), i)).isOf(Blocks.DARK_PRISMARINE) ||
                            world.getBlockState(new BlockPos(pos.getX(), j+ pos.getY(), i)).isOf(ModBlocks.SEA_CRYSTAL_BRICKS) ||
                            world.getBlockState(new BlockPos(pos.getX(), j+ pos.getY(), i)).isOf(Blocks.SEA_LANTERN))){
                        player.sendMessage(Text.literal("Core of the sea needs to be surrounded by a circle!").withColor(6001584));
                        return false;
                    }
                }
            }
            for (int j = -2; j <= 2 ; j+=4){
                for (int i = pos.getY() - 1; i <= pos.getY() + 1; i++) {
                    if (!(world.getBlockState(new BlockPos(pos.getX() , i, pos.getZ() + j)).isOf(Blocks.PRISMARINE) ||
                            world.getBlockState(new BlockPos(pos.getX() , i, pos.getZ() + j)).isOf(Blocks.PRISMARINE_BRICKS) ||
                            world.getBlockState(new BlockPos(pos.getX() , i, pos.getZ() + j)).isOf(Blocks.DARK_PRISMARINE) ||
                            world.getBlockState(new BlockPos(pos.getX() , i, pos.getZ() + j)).isOf(ModBlocks.SEA_CRYSTAL_BRICKS) ||
                            world.getBlockState(new BlockPos(pos.getX() , i, pos.getZ() + j)).isOf(Blocks.SEA_LANTERN))){
                        player.sendMessage(Text.literal("Core of the sea needs to be surrounded by a circle!").withColor(6001584));
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
