package com.keko.items;

import com.keko.CyraFinal;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ENDERITE_CHUNK = registerItem(new Item(new Item.Settings()), "enderite_chunk");
    public static final Item ENDERITE_INGOT = registerItem(new Item(new Item.Settings()), "enderite_ingot");

    public static final Item ENDERITE_SWORD= registerItem(new SwordItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 3, -2.4F))), "enderite_sword");
    public static final Item ENDERITE_SHOVEL = registerItem(new ShovelItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 2, -2.5F))), "enderite_shovel");
    public static final Item ENDERITE_PICKAXE = registerItem(new PickaxeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 2, -2.0F))), "enderite_pickaxe");
    public static final Item ENDERITE_AXE = registerItem(new AxeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 6, -2.0F))), "enderite_axe");
    public static final Item ENDERITE_HOE = registerItem(new HoeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.ENDERITE, -4, -2.0F))), "enderite_hoe");

    public static final Item ENDERITE_HELMET = registerItem(new ArmorItem(), "enderite_helmet");
    public static final Item ENDERITE_CHESTPLATE = registerItem(new ArmorItem(), "enderite_chestplate");
    public static final Item ENDERITE_LEGGINGS = registerItem(new ArmorItem(), "enderite_leggings");
    public static final Item ENDERITE_BOOTS = registerItem(new ArmorItem(), "enderite_boots");

    private static Item registerItem(Item item, String path) {
        return Registry.register(Registries.ITEM, Identifier.of(CyraFinal.MOD_ID, path), item);

    }

    public static void registerModItems() {

    }
}
