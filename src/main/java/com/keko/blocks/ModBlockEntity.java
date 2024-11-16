package com.keko.blocks;

import com.keko.CyraFinal;
import com.keko.blocks.environment.AlchemyTableEntity;
import com.keko.blocks.environment.dim1.ChainBlockGeneratorBlock;
import com.keko.blocks.environment.dim1.ticker.TickingBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModBlockEntity {
    public static final BlockEntityType<AlchemyTableEntity> ALCHEMY_TABLE_ENTITY_BLOCK_ENTITY_TYPE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "alchemy_table_pl"),
                  BlockEntityType.Builder.create(AlchemyTableEntity::new,
                            ModBlocks.ALCHEMY_TABLE).build());

    public static final BlockEntityType<TickingBlockEntity> EXAMPLE_TICKING_BLOCK_ENTITY = register("aaaaaaa",
            BlockEntityType.Builder.create(TickingBlockEntity::new, ModBlocks.CHAIN_BLOCK_GENERATOR_BLOCK)
                    .build());


    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, CyraFinal.id(name), type);
    }
    public static void registerBlockEnt() {

    }
}
