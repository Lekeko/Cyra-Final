package com.keko.features.dim1.crystalTrees;

import com.keko.blocks.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CrystalTreesFeature extends Feature<CrystalTreesFeatureConfig> {
    public CrystalTreesFeature(Codec<CrystalTreesFeatureConfig> configCodec) {
        super(configCodec);
    }


    public boolean generate(FeatureContext<CrystalTreesFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        CrystalTreesFeatureConfig config = context.getConfig();

        BlockPos testPos = new BlockPos(origin);


        int height = random.nextBetween(7, 14);
        for (int y = 0; y < world.getHeight(); y++) {
            testPos = testPos.up();
            if (world.getBlockState(testPos).isOf(ModBlocks.SEA_STONE)) {
                if (world.getBlockState(testPos.up()).isOf(Blocks.WATER)) {

                    generateTrunk(context, height, testPos, random, world);
                    BlockPos coreCrown = new BlockPos(testPos.getX(), testPos.getY() + height, testPos.getZ());
                    generateCrown(context, height, coreCrown, random, world);

                    return true;
                }
            }
        }

        return false;
    }

    private void generateCrown(FeatureContext<CrystalTreesFeatureConfig> context, int height, BlockPos testPos, Random random, StructureWorldAccess world) {
        float r = 3;
        for (float i = -r; i <= r; i+=1)
            for (float j = -r; j <= r; j+=1){
                float checker = i*i + j*j;
                if (Math.ceil(checker) < r*r - 3){
                        if (testPos.getY() >= world.getTopY()) break;
                        BlockPos pos = new BlockPos((int) (testPos.getX() + i), testPos.getY(), (int) (testPos.getZ() + j));
                        world.setBlockState(pos, ModBlocks.BIOLUMINESCENCE_LEAVES.getDefaultState(), 0x10);

                        int length = random.nextBetween(3, 5);
                        BlockPos posDown = pos.down();

                    for (int k = 1; k < length && world.getBlockState(posDown).isOf(Blocks.WATER); k++) {
                            world.setBlockState(posDown, ModBlocks.CRYSTAL_SEA_WEED.getDefaultState(), 0x10);

                            posDown = posDown.down();
                        }
                }else if (Math.floor(checker) > r*r - 2 && (Math.abs(i) + Math.abs(j) != r*2 )){
                        if (testPos.getY() >= world.getTopY()) break;
                        BlockPos pos = new BlockPos((int) (testPos.getX() + i), testPos.getY() - 1, (int) (testPos.getZ() + j));
                        world.setBlockState(pos, ModBlocks.BIOLUMINESCENCE_LEAVES.getDefaultState(), 0x10);
                        if (random.nextBoolean()){
                            pos = pos.down();
                            world.setBlockState(pos, ModBlocks.BIOLUMINESCENCE_LEAVES.getDefaultState(), 0x10);
                        }

                        int length = random.nextBetween(3, 5);
                        BlockPos posDown = pos.down();


                        for (int k = 1; k < length && world.getBlockState(posDown).isOf(Blocks.WATER); k++) {

                            world.setBlockState(posDown, ModBlocks.CRYSTAL_SEA_WEED.getDefaultState(), 0x10);

                            posDown = posDown.down();
                        }
                    }
            }
    }

    private void generateTrunk(FeatureContext<CrystalTreesFeatureConfig> context, int height, BlockPos testPos, Random random, StructureWorldAccess world) {
        if (height > 8)
            for (int i = -1; i <= 1; i++)
                for (int j = -1; j <= 1; j++){
                    if (random.nextBoolean()){
                        BlockPos pos = new BlockPos(testPos.getX() + i, testPos.getY(), testPos.getZ() + j);
                        world.setBlockState(pos, ModBlocks.BIOLUMINESCENCE_WOOD.getDefaultState(), 0x10);
                    }
                }
        boolean tilted = false;
        for (int i = 1; i <= height; i++){
            if (testPos.getY() + height * 2 >= world.getTopY()) break;

            BlockPos pos = new BlockPos(testPos.getX(), testPos.getY() + 1, testPos.getZ());
            if (random.nextBoolean() && !tilted) {
                pos = new BlockPos(pos.getX() + random.nextBetween(-1, 1), pos.getY(), pos.getZ() + random.nextBetween(-1, 1));
                tilted = true;
            }

            world.setBlockState(testPos, ModBlocks.BIOLUMINESCENCE_WOOD.getDefaultState(), 0x10);
            testPos = pos;
        }

    }


}
