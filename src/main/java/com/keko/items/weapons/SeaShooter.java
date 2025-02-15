package com.keko.items.weapons;

import com.keko.entities.projectiles.seaBolt.SeaBolt;
import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SeaShooter extends Item {
    public SeaShooter(Settings settings) {
        super(settings.maxCount(1));
    }

    private final int COOLDWON = 2;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            if (InvSearch.hasItemInInv(user, ModItems.SEA_BOLT)) {
                ItemStack stack = InvSearch.getItemStackInInv(user, ModItems.SEA_BOLT);
                user.getWorld().playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (user.getWorld().getRandom().nextFloat() * 0.4F + 0.8F));
                stack.decrement(1);
                shootBolt(world, user);
                user.getItemCooldownManager().set(user.getActiveItem().getItem(), COOLDWON * 20);
            }
        }
        return super.use(world, user, hand);
    }

    private void shootBolt(World world, Entity user) {
        SeaBolt seaBolt = new SeaBolt(world , (LivingEntity) user, ModItems.SEA_BOLT.getDefaultStack());
        seaBolt.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 4.5F, 1.0F);
        world.spawnEntity(seaBolt);
    }
}
