package com.keko.entities.projectiles.FOTOL;

import com.keko.entities.projectiles.ModProjectileEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class FistOfTheOldLord extends PersistentProjectileEntity {
    public FistOfTheOldLord(EntityType<? extends FistOfTheOldLord> entityType, World world) {
        super(entityType, world);
    }

    public FistOfTheOldLord(World world, LivingEntity entity, @Nullable ItemStack stack, BlockPos target, Color color){
        super(ModProjectileEntities.FIST_OF_THE_OLD_LORD, entity, world, stack, stack);
    }


    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }
}
