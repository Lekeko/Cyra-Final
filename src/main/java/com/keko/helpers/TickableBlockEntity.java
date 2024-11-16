package com.keko.helpers;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.world.World;

public interface TickableBlockEntity {
    void tick();

    static <T extends BlockEntity>BlockEntityTicker<T> getTicker(World pworld){
        return pworld.isClient ? null : ((world, pos, state, blockEntity) -> {
           if (blockEntity instanceof TickableBlockEntity tickableBlockEntity){
               tickableBlockEntity.tick();
           }
        });
    }
}
