package com.keko.features.dim1.crystalTreesDazed;

import com.keko.blocks.ModBlocks;
import com.keko.features.dim1.crystalTrees.CrystalTreesFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CrystalTreesDazedFeature extends Feature<CrystalTreesFeatureDazedConfig> {
    public CrystalTreesDazedFeature(Codec<CrystalTreesFeatureDazedConfig> configCodec) {
        super(configCodec);
    }
    int xOffset = 0;
    int zOffset = 0;

    public boolean generate(FeatureContext<CrystalTreesFeatureDazedConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        CrystalTreesFeatureDazedConfig config = context.getConfig();

        BlockPos testPos = new BlockPos(origin);


        int height = random.nextBetween(7, 14);
        for (int y = 0; y < world.getHeight(); y++) {
            testPos = testPos.up();
            if (world.getBlockState(testPos).isOf(ModBlocks.SEA_STONE)) {
                if (world.getBlockState(testPos.up()).isOf(Blocks.WATER)) {

                    generateTrunk(context, height, testPos, random, world);
                    BlockPos coreCrown = new BlockPos(testPos.getX() + xOffset, testPos.getY() + height, testPos.getZ() + zOffset );
                    generateCrown(context, height, coreCrown, random, world);

                    return true;
                }
            }
        }

        return false;
    }

    private void generateCrown(FeatureContext<CrystalTreesFeatureDazedConfig> context, int height, BlockPos testPos, Random random, StructureWorldAccess world) {
        float r = 3;
        for (float i = -r; i <= r; i+=1)
            for (float j = -r; j <= r; j+=1){
                float checker = i*i + j*j;
                if (Math.ceil(checker) < r*r - 3){
                        if (testPos.getY() >= world.getTopY()) break;
                        BlockPos pos = new BlockPos((int) (testPos.getX() + i), testPos.getY(), (int) (testPos.getZ() + j));
                        world.setBlockState(pos, ModBlocks.DAZED_LEAVES.getDefaultState(), 0x10);

                        int length = random.nextBetween(3, 5);
                        BlockPos posDown = pos.down();

                    for (int k = 1; k < length && world.getBlockState(posDown).isOf(Blocks.WATER); k++) {
                            world.setBlockState(posDown, ModBlocks.DAZED_CRYSTAL_SEA_WEED.getDefaultState(), 0x10);

                            posDown = posDown.down();
                        }
                }else if (Math.floor(checker) > r*r - 2 && (Math.abs(i) + Math.abs(j) != r*2 )){
                        if (testPos.getY() >= world.getTopY()) break;
                        BlockPos pos = new BlockPos((int) (testPos.getX() + i), testPos.getY() - 1, (int) (testPos.getZ() + j));
                        world.setBlockState(pos, ModBlocks.DAZED_LEAVES.getDefaultState(), 0x10);
                        if (random.nextBoolean()){
                            pos = pos.down();
                            world.setBlockState(pos, ModBlocks.DAZED_LEAVES.getDefaultState(), 0x10);
                        }

                        int length = random.nextBetween(3, 5);
                        BlockPos posDown = pos.down();


                        for (int k = 1; k < length && world.getBlockState(posDown).isOf(Blocks.WATER); k++) {

                            world.setBlockState(posDown, ModBlocks.DAZED_CRYSTAL_SEA_WEED.getDefaultState(), 0x10);

                            posDown = posDown.down();
                        }
                    }
            }
    }

    private void generateTrunk(FeatureContext<CrystalTreesFeatureDazedConfig> context, int height, BlockPos testPos, Random random, StructureWorldAccess world) {
        if (height > 8)
            for (int i = -1; i <= 1; i++)
                for (int j = -1; j <= 1; j++){
                    if (random.nextBoolean()){
                        BlockPos pos = new BlockPos(testPos.getX() + i, testPos.getY(), testPos.getZ() + j);
                        world.setBlockState(pos, ModBlocks.DAZED_WOOD.getDefaultState(), 0x10);
                    }
                }
        boolean tilted = false;
        for (int i = 1; i <= height; i++){
            if (testPos.getY() + height * 2 >= world.getTopY()) break;

            BlockPos pos = new BlockPos(testPos.getX(), testPos.getY() + 1, testPos.getZ());
            if (random.nextBoolean() && !tilted) {
                xOffset = random.nextBetween(-1, 1);
                zOffset = random.nextBetween(-1, 1);
                pos = new BlockPos(pos.getX() + xOffset, pos.getY(), pos.getZ() + zOffset);
                tilted = true;
            }

            world.setBlockState(testPos, ModBlocks.DAZED_WOOD.getDefaultState(), 0x10);
            testPos = pos;
        }

    }


}
