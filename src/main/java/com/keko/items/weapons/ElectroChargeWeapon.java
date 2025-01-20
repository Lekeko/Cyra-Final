package com.keko.items.weapons;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.entities.projectiles.electroCharge.ElectroCharge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ElectroChargeWeapon extends Item {
    public ElectroChargeWeapon(Settings settings) {
        super(settings);
        this.getDefaultStack().set(ModDataComponentTypes.ELECTRO_VARIANT, 0);
    }

    int tickRate = 2;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            ElectroCharge electroCharge = new ElectroCharge(world, user);
            electroCharge.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2F, 1.0F);
            world.spawnEntity(electroCharge);
        }

        if (!user.isCreative())
            user.getItemCooldownManager().set(itemStack.getItem(), 60);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (tickRate > 2 && world.isClient){
            if (stack.get(ModDataComponentTypes.ELECTRO_VARIANT) != null) {
                if (stack.get(ModDataComponentTypes.ELECTRO_VARIANT) == 11)
                    stack.set(ModDataComponentTypes.ELECTRO_VARIANT, 0);

                if (stack.get(ModDataComponentTypes.ELECTRO_VARIANT) == 10)
                    stack.set(ModDataComponentTypes.ELECTRO_VARIANT, 11);
                else
                    stack.set(ModDataComponentTypes.ELECTRO_VARIANT, stack.get(ModDataComponentTypes.ELECTRO_VARIANT) + 1);
            } else {
                stack.set(ModDataComponentTypes.ELECTRO_VARIANT, 0);
            }

            tickRate = 0;
        }
        tickRate++;
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
