package com.keko.items.armor;

import com.google.common.collect.ImmutableMap;
import com.keko.items.ModArmorMaterial;
import com.keko.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.Map;

public class ModArmorItem extends ArmorItem {

    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    private void addStatusEffect(PlayerEntity player){
        boolean hasPlayerEffect = player.hasStatusEffect(StatusEffects.DOLPHINS_GRACE);

        if (hasCorrectArmourOn(player) && !hasPlayerEffect){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient){
            if (entity instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) entity;

                if (hasFullArmourOn(player)){
                    if (hasCorrectArmourOn(player))
                        addStatusEffect(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasFullArmourOn(PlayerEntity player){
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplates = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !boots.isEmpty() && !leggings.isEmpty() && !breastplates.isEmpty();
    }

    private boolean hasCorrectArmourOn(PlayerEntity player){
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET).getItem().getDefaultStack();
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS).getItem().getDefaultStack();
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST).getItem().getDefaultStack();
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD).getItem().getDefaultStack();

        return helmet.getItem().equals(ModItems.SEA_CRYSTAL_HELMET) && leggings.getItem().equals(ModItems.SEA_CRYSTAL_LEGGINGS) &&
                chestplate.getItem().equals(ModItems.SEA_CRYSTAL_CHESTPLATE) && boots.getItem().equals(ModItems.SEA_CRYSTAL_BOOTS);
    }

}
