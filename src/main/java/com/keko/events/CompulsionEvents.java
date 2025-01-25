package com.keko.events;

import com.keko.effects.ModStatusEffects;
import com.keko.entities.ModEntities;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.entities.projectiles.compulsionAxe.CompulsionAxe;
import com.keko.entities.projectiles.compulsionAxe.CompulsionAxeRendering;
import com.keko.entities.projectiles.compulsionScythe.CompulsionScythe;
import com.keko.entities.projectiles.compulsionSword.CompulsionSword;
import com.keko.helpers.Directional;
import com.keko.items.ModItems;
import com.keko.sounds.ModSounds;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
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
                dashToEnemy(player, world);
        }
    }

    private static void dashToEnemy(PlayerEntity player, World world) {
        Vec3d pos = new Vec3d(player.getX(), player.getY() + 1.73, + player.getZ());
        Entity target = null;
        for (int i = 0; i < 20 && target == null; i++){
            pos = pos.add(player.getRotationVec(1.0f).x, player.getRotationVec(1.0f).y, player.getRotationVec(1.0f).z);
            int area = 4;
            for (Entity entity : world.getEntitiesByClass(Entity.class, new Box((int) pos.x + area, (int) pos.y + area, (int) pos.z + area,(int) pos.x - area, (int) pos.y - area, (int) pos.z - area), Entity::isAlive)){
                if (entity != player &&!( entity instanceof ItemEntity))
                    target = entity;
            }
        }

        if (target!= null)
        {
            world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), ModSounds.PARRY, SoundCategory.NEUTRAL, 1.5F, 1.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

            target.damage(world.getDamageSources().playerAttack(player), 15 -( target instanceof PlayerEntity ? ((PlayerEntity)target).getArmor()/4f : 0));

            target.setVelocity(target.getPos().subtract(player.getPos()).normalize().multiply(player.distanceTo(target)/4f).add(0,1.2f,0));
            target.velocityModified = true;
            player.setVelocity(target.getPos().subtract(player.getPos()).normalize().multiply(player.distanceTo(target)/4f).add(0,1,0));
            player.velocityModified = true;
        }


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
