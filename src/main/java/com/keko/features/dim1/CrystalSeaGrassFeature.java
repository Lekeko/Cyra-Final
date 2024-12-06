package com.keko.features.dim1;

import com.keko.blocks.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CrystalSeaGrassFeature extends Feature<CrystalSeaGrassFeatureConfig> {
    public CrystalSeaGrassFeature(Codec<CrystalSeaGrassFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<CrystalSeaGrassFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        CrystalSeaGrassFeatureConfig config = context.getConfig();

        int number = config.number();
        Identifier blockId = config.blockId();

        BlockState blockState = Registries.BLOCK.get(blockId).getDefaultState();
        if (blockState == null) throw new IllegalStateException(blockId + " could not be parsed to a valid block identifier!");

        BlockPos testPos = new BlockPos(origin);
        for (int y = 0; y < world.getHeight(); y++) {
            testPos = testPos.up();
            // the tag name is dirt, but includes grass, mud, podzol, etc.
            if (world.getBlockState(testPos).isOf(ModBlocks.SEA_STONE)) {
                if (world.getBlockState(testPos.up()).isOf(Blocks.WATER)) {
                    for (int i = 0; i < 1; i++) {
                        // create a simple pillar of blocks
                        testPos = new BlockPos(testPos.getX(), testPos.getY()+1, testPos.getZ());
                        world.setBlockState(testPos, blockState, 0x10);
                        testPos = testPos.up();

                        // ensure we don't try to place blocks outside the world
                        if (testPos.getY() >= world.getTopY()) break;
                    }
                    return true;
                }
            }
        }

        return false;
    }
}
