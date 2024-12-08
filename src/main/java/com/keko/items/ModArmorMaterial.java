package com.keko.items;

import com.keko.CyraFinal;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterial{
    public static final RegistryEntry<ArmorMaterial> ENDERITE = register("enderite", Map.of(
                    ArmorItem.Type.HELMET, 4,
                    ArmorItem.Type.CHESTPLATE, 9,
                    ArmorItem.Type.LEGGINGS, 7,
                    ArmorItem.Type.BOOTS, 4
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(ModItems.ENDERITE_INGOT),
            4F,
            0.1F,
            false);

    public static final RegistryEntry<ArmorMaterial> SEA_CRYSTAL = register("sea_crystal", Map.of(
                    ArmorItem.Type.HELMET, 5,
                    ArmorItem.Type.CHESTPLATE, 10,
                    ArmorItem.Type.LEGGINGS, 8,
                    ArmorItem.Type.BOOTS, 6
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(ModItems.SEA_CRYSTAL),
            5.5F,
            0.3F,
            false);

    public static final RegistryEntry<ArmorMaterial> PYRITE = register("pyrite", Map.of(
                    ArmorItem.Type.HELMET, 6,
                    ArmorItem.Type.CHESTPLATE, 12,
                    ArmorItem.Type.LEGGINGS, 9,
                    ArmorItem.Type.BOOTS, 7
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(ModItems.RESTORED_PYRITE),
            6F,
            0.45F,
            false);


    public static RegistryEntry<ArmorMaterial> register(String id, Map<ArmorItem.Type, Integer> defensePoints,
                                                        int enchantability, RegistryEntry<SoundEvent> equipSound,
                                                        Supplier<Ingredient> repairIngredient, float toughness,
                                                        float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(Identifier.of(id), "", dyeable)
        );

        var material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredient, layers,
                toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of(CyraFinal.MOD_ID), material);

        return RegistryEntry.of(material);
    }

    public static void load() {}
}

