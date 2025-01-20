package com.keko.entities.projectiles.lordStar;

import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.items.ModItems;
import com.keko.packet.DegreePayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class LordStarEntity extends PersistentProjectileEntity {
    public LordStarEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public LordStarEntity(World world, LivingEntity entity){
        super(ModProjectileEntities.LORD_STAR, entity, world, ModItems.ELECTRO_CHARGE.getDefaultStack(), ModItems.ELECTRO_CHARGE.getDefaultStack());
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.GLASS.getDefaultStack();
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        if (!state.isOf(Blocks.AIR))
            discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        discard();
    }

    @Override
    public void tick() {
        if (!getWorld().isClient && age > 30) discard();
        super.tick();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!getWorld().isClient && entityHitResult.getEntity() != getOwner()) {
            entityHitResult.getEntity().damage(getWorld().getDamageSources().generic(), 4);
            discard();
        }
    }
}
