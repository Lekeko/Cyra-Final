package com.keko.features.dim1.voidPyrite;

import com.keko.blocks.ModBlocks;
import com.keko.features.dim1.seaGrassDazed.CrystalSeaGrassFeatureDazedConfig;
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

public class VoidPyriteFeature extends Feature<VoidPyriteConfig> {
    public VoidPyriteFeature(Codec<VoidPyriteConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<VoidPyriteConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        VoidPyriteConfig config = context.getConfig();

        int number = config.number();
        Identifier blockId = config.blockId();

        BlockState blockState = Registries.BLOCK.get(blockId).getDefaultState();
        if (blockState == null) throw new IllegalStateException(blockId + " could not be parsed to a valid block identifier!");

        BlockPos testPos = new BlockPos(origin);

        int radius3 = 0;
        int radius4 = 0;

        java.util.Random random1 = new java.util.Random();
        radius3 = random1.nextInt(4) + 2;
        radius4 = random1.nextInt(3) + 1;

        int choiche = world.getRandom().nextBetween(1,2);


        switch (choiche){
            case 1 -> {
                if (world.getBlockState(testPos).isOf(ModBlocks.VOID_STONE)){
                    for (int i = -radius3; i < radius3; i++)
                        for (int j = -radius3; j < radius3; j++)
                            for (int k = -radius3; k < radius3; k++)
                                if (i*i + j*j + k * k<= radius3 * radius3){
                                    if (world.getRandom().nextBoolean() && world.getBlockState(new BlockPos(testPos.getX() + i, testPos.getY() + j, testPos.getZ() + k)).isOf(ModBlocks.VOID_STONE))
                                        world.setBlockState(new BlockPos(testPos.getX() + i, testPos.getY() + j, testPos.getZ() + k), ModBlocks.VOID_PYRITE_ORE.getDefaultState(), 10);
                                }
                }
            }
            case 2 -> {
                if (world.getBlockState(testPos).isOf(ModBlocks.VOID_STONE) && testPos.getY() < 100){
                    for (int i = -radius4; i < radius4; i++)
                        for (int j = -radius4; j < radius4; j++)
                            for (int k = -radius4; k < radius4; k++)
                                if (i*i + j*j + k * k<= radius4 * radius4){
                                    if (world.getRandom().nextBetween(1, 100) < 80 && world.getBlockState(new BlockPos(testPos.getX() + i, testPos.getY() + j, testPos.getZ() + k)).isOf(ModBlocks.VOID_STONE))
                                        world.setBlockState(new BlockPos(testPos.getX() + i, testPos.getY() + j, testPos.getZ() + k), ModBlocks.ANCIENT_PYRITE_ORE.getDefaultState(), 10);
                                }
                }
            }
        }


        return false;
    }
}
