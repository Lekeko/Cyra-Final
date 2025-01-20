package com.keko.features.dim1.kyaniteCrystalAltar;

import com.keko.blocks.ModBlocks;
import com.keko.entities.ModEntities;
import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import com.keko.features.dim1.roseCrystalAltar.RoseCrystalFeatureConfig;
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

public class KyaniteCrystalFeature extends Feature<KyaniteCrystalFeatureConfig> {
    public KyaniteCrystalFeature(Codec<KyaniteCrystalFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<KyaniteCrystalFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        KyaniteCrystalFeatureConfig config = context.getConfig();

        int number = config.number();
        Identifier blockId = config.blockId();

        BlockState blockState = Registries.BLOCK.get(blockId).getDefaultState();
        if (blockState == null) throw new IllegalStateException(blockId + " could not be parsed to a valid block identifier!");

        BlockPos testPos = new BlockPos(origin);
        final int fishes = 40;


        int radius = 5;

        if (world.getRandom().nextBetween(1, 100) < 25){
            for (int y = 0; y < world.getHeight(); y++) {
                testPos = testPos.up();
                if ((world.getBlockState(testPos).isOf(ModBlocks.DAZED_SEA_STONE) || world.getBlockState(testPos).isOf(ModBlocks.SEA_STONE_GRASS_DAZED)) && world.getBlockState(testPos.up()).isOf(Blocks.WATER)) {

                    world.setBlockState(testPos.up().up(), ModBlocks.KYANITE_CRYSTAL.getDefaultState(), 10);

                    for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++) {
                            BlockPos pos = new BlockPos(testPos.up().getX() + i, testPos.up().getY(), testPos.up().getZ() + j);
                            world.setBlockState(pos, ModBlocks.SEA_MIRIANITE.getDefaultState(), 10);
                        }

                    for (int i = -radius; i <= radius; i++)
                        for (int j = -radius; j <= radius; j++) {
                            if (i * i + j * j <= radius * radius && world.getRandom().nextBetween(1, 14) > Math.abs(i) + Math.abs(j)) {
                                BlockPos tempPos = testPos;
                                while (world.getBlockState(testPos).isOf(Blocks.WATER)) {
                                    tempPos = tempPos.down();
                                }
                                tempPos = new BlockPos(tempPos.getX() + i, testPos.getY(), tempPos.getZ() + j);
                                while (world.getBlockState(tempPos).isOf(Blocks.WATER)) {
                                    tempPos = tempPos.down();
                                }
                                world.setBlockState(tempPos, ModBlocks.SEA_MIRIANITE.getDefaultState(), 10);
                            }

                        }

                    for (int i = 0; i < fishes; i++){

                        BlockPos pos = new BlockPos(testPos.up().getX() + world.getRandom().nextBetween(-3, 3), testPos.up().getY() + world.getRandom().nextBetween(2, 5), testPos.up().getZ() + world.getRandom().nextBetween(-3, 3));

                        if (world.getBlockState(pos).isOf(Blocks.WATER)){
                            SeaRodentEntity entity = new SeaRodentEntity(ModEntities.DEEP_SALMON, world.toServerWorld());
                            entity.setPos(pos.getX(), pos.getY(), pos.getZ());
                            world.spawnEntity(entity);
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

}
