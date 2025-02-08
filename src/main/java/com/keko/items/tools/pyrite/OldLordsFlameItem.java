package com.keko.items.tools.pyrite;

import com.keko.effects.ModStatusEffects;
import com.keko.items.ModItems;
import com.keko.midnightLibConfigs.MidnightConfigCyra;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class OldLordsFlameItem extends Item {
    public OldLordsFlameItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            if (checkForArmour(world, user)){
                ItemStack stack = user.getStackInHand(hand);
                if (!user.getItemCooldownManager().isCoolingDown(stack.getItem())) {
                    user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.COMPULSION, 1000, 1));
                    user.getItemCooldownManager().set(stack.getItem(), MidnightConfigCyra.cooldown_comp * 20);
                }
            }
        }
        return super.use(world, user, hand);
    }

    private boolean checkForArmour(World world, PlayerEntity user) {
        ItemStack boots = user.getEquippedStack(EquipmentSlot.FEET).getItem().getDefaultStack();
        ItemStack leggings = user.getEquippedStack(EquipmentSlot.LEGS).getItem().getDefaultStack();
        ItemStack chestplate = user.getEquippedStack(EquipmentSlot.CHEST).getItem().getDefaultStack();
        ItemStack helmet = user.getEquippedStack(EquipmentSlot.HEAD).getItem().getDefaultStack();

        return helmet.getItem().equals(ModItems.PYRITE_HELMET) && leggings.getItem().equals(ModItems.PYRITE_LEGGINGS) &&
                chestplate.getItem().equals(ModItems.PYRITE_CHESTPLATE) && boots.getItem().equals(ModItems.PYRITE_BOOTS);
    }
}
