package com.keko.events;

import com.keko.effects.ModStatusEffects;
import com.keko.entities.ModEntities;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.entities.projectiles.compulsionAxe.CompulsionAxe;
import com.keko.entities.projectiles.compulsionAxe.CompulsionAxeRendering;
import com.keko.entities.projectiles.compulsionSword.CompulsionSword;
import com.keko.helpers.Directional;
import com.keko.items.ModItems;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CompulsionEvents {
    public static void checkCompulsion(PlayerEntity player, World world, Hand hand) {
        if (player.hasStatusEffect(ModStatusEffects.COMPULSION)){
            ItemStack stack = player.getStackInHand(hand);
            if (stack.isOf(ModItems.PYRITE_SWORD))
                summonSwordBig(player, world);
            if (stack.isOf(ModItems.PYRITE_AXE))
                swirlAround(player, world);
            if (stack.isOf(ModItems.PYRITE_HOE))
                grabEnemies(player, world);
        }
    }

    private static void grabEnemies(PlayerEntity player, World world) {

    }

    private static void swirlAround(PlayerEntity player, World world) {
        world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.NEUTRAL, 1.5F, 1.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        CompulsionAxe axe = new CompulsionAxe(ModProjectileEntities.COMPULSION_AXE_ENTITY_TYPE, world);
        axe.setOwner(player);
        Vec3d pos = player.getPos();
        axe.setPos(pos.x, pos.y, pos.z);
        world.spawnEntity(axe);
    }

    private static void summonSwordBig(PlayerEntity player, World world) {
        world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.NEUTRAL, 1.5F, 1.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        CompulsionSword sword = new CompulsionSword(ModProjectileEntities.COMPULSION_SWORD_ENTITY_TYPE, world);
            Vec3d pos = Directional.rayCast(world, player, player.getRotationVec(1.0f), 40);
            while (world.getBlockState(BlockPos.ofFloored(pos)).isOf(Blocks.AIR) || world.getBlockState(BlockPos.ofFloored(pos)).isOf(Blocks.WATER))
                pos = pos.add(0,-1,0);
            pos = pos.add(0, 20, 0);
            sword.setVelocity(0,3,0);
            sword.setOwner(player);
            sword.setPos(pos.x, pos.y, pos.z);
            world.spawnEntity(sword);

    }
}
