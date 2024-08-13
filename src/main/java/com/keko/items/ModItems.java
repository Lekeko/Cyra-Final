package com.keko.items;

import com.keko.CyraFinal;
import com.keko.items.tools.BuffFlask;
import com.keko.items.tools.HealingFlask;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ENDERITE_CHUNK = registerItem(new Item(new Item.Settings()), "enderite_chunk");
    public static final Item ENDERITE_INGOT = registerItem(new Item(new Item.Settings()), "enderite_ingot");
    public static final Item HEALING_FLASK = registerItem(new HealingFlask(new Item.Settings().maxCount(1)), "healing_flask");
    public static final Item BUFF_FLASK = registerItem(new BuffFlask(new Item.Settings().maxCount(1)), "buff_flask");

    public static final Item ORB_OF_BOUND = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_bound");
    public static final Item ORB_OF_DAHY = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_dahy");
    public static final Item ORB_OF_FORCE = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_force");
    public static final Item ORB_OF_IMPETOUSITY = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_impetuosity");
    public static final Item ORB_OF_VITALITY = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_vitality");


    public static final Item ENDERITE_SWORD= registerItem(new SwordItem(ModToolMaterial.ENDERITE, (new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 4, -2.4F)))), "enderite_sword");
    public static final Item ENDERITE_SHOVEL = registerItem(new ShovelItem(ModToolMaterial.ENDERITE, (new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 2, -2.3F)))), "enderite_shovel");
    public static final Item ENDERITE_PICKAXE = registerItem(new PickaxeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 2, -2.0F))), "enderite_pickaxe");
    public static final Item ENDERITE_AXE = registerItem(new AxeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 6, -2.0F))), "enderite_axe");
    public static final Item ENDERITE_HOE = registerItem(new HoeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.ENDERITE, -3, 0.0F))), "enderite_hoe");

    public static final Item ENDERITE_HELMET = registerItem(new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.HELMET , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "enderite_helmet");
    public static final Item ENDERITE_CHESTPLATE = registerItem(new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.CHESTPLATE , new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(37))), "enderite_chestplate");
    public static final Item ENDERITE_LEGGINGS = registerItem(new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.LEGGINGS , new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(37))), "enderite_leggings");
    public static final Item ENDERITE_BOOTS = registerItem(new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.BOOTS , new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(37))), "enderite_boots");

    private static Item registerItem(Item item, String path) {
        return Registry.register(Registries.ITEM, Identifier.of(CyraFinal.MOD_ID, path), item);

    }

    public static void registerModItems() {
    }
}
