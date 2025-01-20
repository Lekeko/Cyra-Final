package com.keko.features.dim1.voidStuctures;

import com.keko.blocks.ModBlocks;
import com.keko.features.dim1.voidPyrite.VoidPyriteConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class VoidStructuresFeature extends Feature<VoidStructuresConfig> {
    public VoidStructuresFeature(Codec<VoidStructuresConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<VoidStructuresConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        VoidStructuresConfig config = context.getConfig();

        int number = config.number();
        Identifier blockId = config.blockId();

        BlockState blockState = Registries.BLOCK.get(blockId).getDefaultState();
        if (blockState == null) throw new IllegalStateException(blockId + " could not be parsed to a valid block identifier!");

        BlockPos testPos = new BlockPos(origin);

        if (world.getBlockState(testPos).isOf(Blocks.WATER) && world.getRandom().nextBetween(1, 10) < 2){
            int step = 0;
            int checks = 0;
            do {
                checks = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++){
                        BlockPos currentPos = new BlockPos(testPos.getX() + i, testPos.getY(), testPos.getZ() + j);
                        if (world.getBlockState(currentPos).isOf(Blocks.WATER)){
                            checks++;
                            if (step == 0) {
                                if (i == 0 || j == 0)
                                    world.setBlockState(currentPos, ModBlocks.CHISELED_VOID_BRICKS.getDefaultState(), 10);
                                else
                                    world.setBlockState(currentPos, ModBlocks.SMOOTH_VOID_BRICKS.getDefaultState(), 10);
                            } else
                                world.setBlockState(currentPos, ModBlocks.VOID_BRICKS.getDefaultState(), 10);
                        }

                    }
                step++;
                testPos = testPos.down();
            }while (checks != 0);
        }


        return false;
    }
}
