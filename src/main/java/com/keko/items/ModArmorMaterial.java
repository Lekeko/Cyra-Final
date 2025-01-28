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
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterial{
    public static final RegistryEntry<ArmorMaterial> ENDERITE = register("enderite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->{
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.BODY, 8);
            }), 20 , SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(ModItems.ENDERITE_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(CyraFinal.MOD_ID, "enderite"))),4f,0.1f));

    public static final RegistryEntry<ArmorMaterial> SEA_CRYSTAL = register("sea_crystal",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->{
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.BOOTS, 6);
                map.put(ArmorItem.Type.BODY, 9);
            }), 20 , SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(ModItems.SEA_CRYSTAL),
                    List.of(new ArmorMaterial.Layer(Identifier.of(CyraFinal.MOD_ID, "sea_crystal"))),5.5f,0.3f));

    public static final RegistryEntry<ArmorMaterial> PYRITE = register("pyrite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->{
                map.put(ArmorItem.Type.HELMET, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 12);
                map.put(ArmorItem.Type.LEGGINGS, 9);
                map.put(ArmorItem.Type.BOOTS, 7);
                map.put(ArmorItem.Type.BODY, 11);
            }), 20 , SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(ModItems.SEA_CRYSTAL),
                    List.of(new ArmorMaterial.Layer(Identifier.of(CyraFinal.MOD_ID, "pyrite"))),6f,0.45f));


    public static RegistryEntry<ArmorMaterial> register(String name, Supplier<ArmorMaterial> materialSupplier){
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(CyraFinal.MOD_ID, name), materialSupplier.get());

    }

    public static void load() {}
}

