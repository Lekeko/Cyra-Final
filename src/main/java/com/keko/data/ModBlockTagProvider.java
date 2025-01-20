package com.keko.data;

import com.keko.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(BlockTags.WALLS)
                    .add(ModBlocks.SEA_CRYSTAL_BRICKS_WALL);
            getOrCreateTagBuilder(BlockTags.WALLS)
                    .add(ModBlocks.PYRITE_BRICK_WALLS);
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.BIOLUMINESCENCE_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.BIOLUMINESCENCE_FENCE_GATE);
    }
}
