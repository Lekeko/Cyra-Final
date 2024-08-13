package com.keko.blocks;

import com.keko.CyraFinal;
import com.keko.blocks.environment.AlchemyTableEntity;
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

    public static void registerBlockEnt() {

    }
}
