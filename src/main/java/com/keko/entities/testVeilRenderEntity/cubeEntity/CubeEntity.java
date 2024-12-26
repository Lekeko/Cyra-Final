package com.keko.entities.testVeilRenderEntity.cubeEntity;

import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.entities.testVeilRenderEntity.ModTestEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CubeEntity extends Entity  {

    public CubeEntity(EntityType<CubeEntity> cubeEntityEntityType, World world) {
        super(ModTestEntity.CUBE_ENTITY_TYPE, world);
    }


    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }
}
