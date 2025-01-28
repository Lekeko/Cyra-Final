package com.keko.blocks.environment.dim1;

import com.keko.blocks.ModBlocks;
import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import com.keko.world.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class CoreOfTheSeaBlock extends Block {
    public CoreOfTheSeaBlock(Settings settings) {
        super(settings);
    }
    private final int radius = 8;
    ArrayList<Block> blocksOfPortal = new ArrayList<>();

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient){
            if(canTeleportPlayer(state, world, pos, player, hit) && isSafe(player)){
                if (!player.isSneaking()) {
                    MinecraftServer server = world.getServer();
                    if (server != null) {
                        if (player instanceof ServerPlayerEntity) {
                            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                            if (world.getRegistryKey() == ModDimensions.MURIEL_KAIA_LEVEL_KEY) {
                                ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                                if (overWorld != null) {
                                    int i = 319;
                                    for (i = 319; i >= -64; i--){
                                        if (overWorld.getBlockState(new BlockPos(pos.getX(), i, pos.getZ())).isOf(ModBlocks.CORE_OF_THE_SEA)){
                                            break;
                                        }
                                    }
                                    if (i > -64) {
                                        serverPlayer.teleport(overWorld, pos.getX() + 1, i, pos.getZ(),
                                                serverPlayer.bodyYaw, serverPlayer.prevPitch);
                                        overWorld.setBlockState(new BlockPos(pos.getX(), i, pos.getZ()), ModBlocks.CORE_OF_THE_SEA.getDefaultState());
                                    }
                                    else
                                    {
                                        BlockPos posNew = new BlockPos(pos.getX(), 319, pos.getZ());
                                        while (overWorld.getBlockState(posNew).isOf(Blocks.AIR))
                                            posNew = posNew.down();
                                        if (posNew.getY() > -64) {
                                            overWorld.setBlockState(new BlockPos(posNew.getX(),  posNew.getY(), posNew.getZ()), ModBlocks.CORE_OF_THE_SEA.getDefaultState());

                                            serverPlayer.teleport(overWorld, posNew.getX() + 1, posNew.getY(), posNew.getZ(),
                                                    serverPlayer.bodyYaw, serverPlayer.prevPitch);
                                        }
                                        else Objects.requireNonNull(serverPlayer).sendMessage(Text.literal("Teleporting position out of the world!").withColor(new Color(245, 165, 255, 255).getRGB()));

                                    }


                                }
                            } else {
                                ServerWorld muriel = server.getWorld(ModDimensions.MURIEL_KAIA_LEVEL_KEY);
                                assert muriel != null;
                                int i = 300;
                                for (i = 300; i >= -64; i--){
                                    if (muriel.getBlockState(new BlockPos(pos.getX(), i, pos.getZ())).isOf(ModBlocks.CORE_OF_THE_SEA)){
                                        break;
                                    }
                                }
                                if (i > -64) {
                                    serverPlayer.teleport(muriel, pos.getX() + 1, i, pos.getZ(),
                                            serverPlayer.bodyYaw, serverPlayer.prevPitch);
                                    muriel.setBlockState(new BlockPos(pos.getX(), i, pos.getZ()), ModBlocks.CORE_OF_THE_SEA.getDefaultState());
                                }else {
                                    BlockPos posBuilder = new BlockPos(pos.getX(), 220, pos.getZ());
                                    makeSPHERE(posBuilder, muriel);
                                    makeFRAME(posBuilder, muriel);
                                    muriel.setBlockState(new BlockPos(pos.getX(), 220, pos.getZ()), ModBlocks.CORE_OF_THE_SEA.getDefaultState());
                                    serverPlayer.teleport(muriel, pos.getX() + 1, 220, pos.getZ(),
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

    private boolean isSafe(PlayerEntity player) {
        ItemStack stack = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_1);
        if (stack == null) {
            stack = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_2);
            if (stack == null) {
                stack = InvSearch.getItemStackInInv(player, ModItems.DEPTH_CHARM_TIER_3);
                if (stack == null) {
                    if (player.getWorld().getRegistryKey() == ModDimensions.MURIEL_KAIA_LEVEL_KEY)
                        return true;
                    player.sendMessage(Text.literal("To enter, you need a depth charm!").withColor(6001584));
                    return false;
                }
            }
        }
                return true;
    }

    private void makeFRAME(BlockPos pos, ServerWorld muriel) {
        //CRY ME A RIVER ITS MORE EFFICIENT THIS WAY
        muriel.setBlockState(new BlockPos(pos.getX() + 2, pos.getY() + 1, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );
        muriel.setBlockState(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ()), Blocks.PRISMARINE.getDefaultState() );
        muriel.setBlockState(new BlockPos(pos.getX() + 2, pos.getY() - 1, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );


        muriel.setBlockState(new BlockPos(pos.getX() - 2, pos.getY() + 1, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );
        muriel.setBlockState(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ()), Blocks.PRISMARINE.getDefaultState() );
        muriel.setBlockState(new BlockPos(pos.getX() - 2, pos.getY() - 1, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );

        muriel.setBlockState(new BlockPos(pos.getX() + 1, pos.getY() + 2, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );
        muriel.setBlockState(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );
        muriel.setBlockState(new BlockPos(pos.getX() - 1, pos.getY() + 2, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );

        muriel.setBlockState(new BlockPos(pos.getX() + 1, pos.getY() - 2, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );
        muriel.setBlockState(new BlockPos(pos.getX(), pos.getY() - 2, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );
        muriel.setBlockState(new BlockPos(pos.getX() - 1, pos.getY() - 2, pos.getZ()), Blocks.PRISMARINE.getDefaultState() );

    }

    private void makeSPHERE(BlockPos pos, ServerWorld muriel) {
        int radiusSquared = radius * radius;

        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                for (int k = -radius; k <= radius; k++) {
                    int distanceSquared = i * i + j * j + k * k;

                    if (distanceSquared >= radiusSquared - 7 && distanceSquared <= radiusSquared + 7) {
                        if (muriel.getBlockState(pos.add(i, j, k)).isOf(Blocks.WATER))
                            muriel.setBlockState(pos.add(i, j, k), Blocks.GLASS.getDefaultState());
                    } else if (distanceSquared < radiusSquared) {
                        muriel.setBlockState(pos.add(i, j, k), Blocks.AIR.getDefaultState());
                    }
                }
            }
        }
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
