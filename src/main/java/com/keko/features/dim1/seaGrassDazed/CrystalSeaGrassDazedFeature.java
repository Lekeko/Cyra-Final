package com.keko.features.dim1.seaGrassDazed;

import com.keko.blocks.ModBlocks;
import com.keko.features.dim1.seaGrass.CrystalSeaGrassFeatureConfig;
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

public class CrystalSeaGrassDazedFeature extends Feature<CrystalSeaGrassFeatureDazedConfig> {
    public CrystalSeaGrassDazedFeature(Codec<CrystalSeaGrassFeatureDazedConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<CrystalSeaGrassFeatureDazedConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        CrystalSeaGrassFeatureDazedConfig config = context.getConfig();

        int number = config.number();
        Identifier blockId = config.blockId();

        BlockState blockState = Registries.BLOCK.get(blockId).getDefaultState();
        if (blockState == null) throw new IllegalStateException(blockId + " could not be parsed to a valid block identifier!");

        BlockPos testPos = new BlockPos(origin);

        int radius = 0;

        java.util.Random random1 = new java.util.Random();
        radius = random1.nextInt(5) + 2;
        int radius2 = radius + 3;

        for (int y = 0; y < world.getHeight(); y++) {
            testPos = testPos.up();
            if (world.getBlockState(testPos).isOf(ModBlocks.DAZED_SEA_STONE)) {
                if (world.getBlockState(testPos.up()).isOf(Blocks.WATER)) {
                    for (int i = -radius2; i < radius2; i++)
                        for (int j = -radius2; j < radius2; j++)
                            for (int k = -radius2; k < radius2; k++){
                                if (i*i + j*j + k * k<= radius2 * radius2){
                                    if (testPos.getY() >= world.getTopY()) break;
                                    BlockPos newPos = new BlockPos(testPos.getX() + i, testPos.getY() + j, testPos.getZ() + k);

                                    if (world.getBlockState(newPos).isOf(ModBlocks.DAZED_SEA_STONE) && world.getBlockState(newPos.up()).isOf(Blocks.WATER)){
                                        world.setBlockState(newPos, ModBlocks.SEA_STONE_GRASS_DAZED.getDefaultState(), 0x10);
                                        if (world.getRandom().nextBetween(1, 100) < 50)
                                            world.setBlockState(newPos.up(), ModBlocks.CRYSTAL_SEA_GRASS_DAZED.getDefaultState(), 0x10);
                                    }

                                }else if (i*i + j*j + k * k <= radius2 * radius2){
                                    BlockPos newPos = new BlockPos(testPos.getX() + i, testPos.getY() + j, testPos.getZ() + k);
                                    if (world.getBlockState(newPos).isOf(ModBlocks.DAZED_SEA_STONE) && world.getBlockState(newPos.up()).isOf(Blocks.WATER)){
                                        world.setBlockState(newPos, ModBlocks.SEA_STONE_GRASS_DAZED.getDefaultState(), 0x10);
                                    }
                                }

                    }

                    int radius3 = 10;
                    BlockPos testPos1 = new BlockPos(origin);

                    for (int i = -radius3; i < radius3; i++)
                        for (int j = -radius3; j < radius3; j++)
                            for (int k = -radius3; k < radius3; k++)
                                if (i*i + j*j + k * k<= radius3 * radius3){
                                    if (testPos1.getY() >= world.getTopY()) break;
                                    BlockPos newPos = new BlockPos(testPos1.getX() + i, testPos1.getY() + j, testPos1.getZ() + k);

                                    if (world.getBlockState(newPos).isOf(ModBlocks.SEA_STONE)){
                                        world.setBlockState(newPos, ModBlocks.DAZED_SEA_STONE.getDefaultState(), 0x10);
                                    }

                                }

                    return true;
                }
            }
        }

        return false;
    }
}
