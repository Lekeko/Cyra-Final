package com.keko.items;

import com.keko.CyraFinal;
import com.keko.items.armor.ModArmorItem;
import com.keko.items.bossItems.spawners.RottenNecklace;
import com.keko.items.tools.*;
import com.keko.items.tools.pyrite.PyriteHoe;
import com.keko.items.weapons.PyritePrimordialCube;
import com.keko.items.weapons.SeaShooter;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ModItems {
    public static final Set<Item> ITEMS = new HashSet<>();



    /*MISC*/

    public static final Item ORB_OF_BOUND = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_bound");
    public static final Item ORB_OF_DAHY = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_dahy");
    public static final Item ORB_OF_FORCE = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_force");
    public static final Item ORB_OF_IMPETUOSITY = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_impetuosity");
    public static final Item ORB_OF_VITALITY = registerItem(new Item(new Item.Settings().maxCount(1)), "orb_of_vitality");

    public static final Item HEALING_FLASK = registerItem(new HealingFlask(new Item.Settings().maxCount(1)), "healing_flask");
    public static final Item BUFF_FLASK = registerItem(new BuffFlask(new Item.Settings().maxCount(1)), "buff_flask");


    public static final Item ROTTEN_NECKLACE = registerItem(new RottenNecklace(new Item.Settings().maxCount(1)), "rotten_necklace");

    //TEST
    public static final Item FLASHLIGHT = registerItem(new FlashLight(new Item.Settings().maxCount(1)), "spyglass");





    /*enderite*/
        public static final Item ENDERITE_CHUNK = registerItem(new Item(new Item.Settings()), "enderite_chunk");
        public static final Item ENDERITE_INGOT = registerItem(new Item(new Item.Settings()), "enderite_ingot");

        public static final Item ENDERITE_SWORD = registerItem(new SwordItem(ModToolMaterial.ENDERITE, (new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 4, -2.4F)))), "enderite_sword");
        public static final Item ENDERITE_SHOVEL = registerItem(new ShovelItem(ModToolMaterial.ENDERITE, (new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 2, -2.3F)))), "enderite_shovel");
        public static final Item ENDERITE_PICKAXE = registerItem(new PickaxeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 2, -2.0F))), "enderite_pickaxe");
        public static final Item ENDERITE_AXE = registerItem(new AxeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 6, -2.0F))), "enderite_axe");
        public static final Item ENDERITE_HOE = registerItem(new HoeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.ENDERITE, -3, -1.8F))), "enderite_hoe");

        public static final Item ENDERITE_HELMET = registerItem(new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "enderite_helmet");
        public static final Item ENDERITE_CHESTPLATE = registerItem(new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(37))), "enderite_chestplate");
        public static final Item ENDERITE_LEGGINGS = registerItem(new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(37))), "enderite_leggings");
        public static final Item ENDERITE_BOOTS = registerItem(new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(37))), "enderite_boots");






    //SEA CRYSTAL

    public static final Item SEA_CRYSTAL_FRAGMENT = registerItem(new Item(new Item.Settings()), "sea_crystal_fragment");
    public static final Item SEA_CRYSTAL = registerItem(new Item(new Item.Settings()), "sea_crystal");
    public static final Item SEA_WAND = registerItem(new SeaWandItem(new Item.Settings()), "sea_support_block_wand");
    public static final Item SEA_SHOOTER = registerItem(new SeaShooter(new Item.Settings()), "sea_shooter");
    public static final Item SEA_BOLT =  registerItem(new Item(new Item.Settings().maxCount(1)), "sea_bolt");

    public static final Item SEA_CRYSTAL_HELMET = registerItem(new ModArmorItem(ModArmorMaterial.SEA_CRYSTAL, ArmorItem.Type.HELMET , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "sea_crystal_helmet");
    public static final Item SEA_CRYSTAL_CHESTPLATE = registerItem(new ArmorItem(ModArmorMaterial.SEA_CRYSTAL, ArmorItem.Type.CHESTPLATE , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "sea_crystal_chestplate");
    public static final Item SEA_CRYSTAL_LEGGINGS = registerItem(new ArmorItem(ModArmorMaterial.SEA_CRYSTAL, ArmorItem.Type.LEGGINGS , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "sea_crystal_leggings");
    public static final Item SEA_CRYSTAL_BOOTS = registerItem(new ArmorItem(ModArmorMaterial.SEA_CRYSTAL, ArmorItem.Type.BOOTS , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "sea_crystal_boots");

    public static final Item SEA_CRYSTAL_STRIDERS = registerItem(new SeaCrystalStriders(new Item.Settings()), "sea_crystal_striders");

    public static final Item SEA_CRYSTAL_SWORD = registerItem(new SwordItem(ModToolMaterial.SEA_CRYSTAL, (new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, 6, -1.8F)))), "sea_crystal_sword");
    public static final Item SEA_CRYSTAL_SHOVEL = registerItem(new ShovelItem(ModToolMaterial.SEA_CRYSTAL, (new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, 2, -2.0F)))), "sea_crystal_shovel");
    public static final Item SEA_CRYSTAL_PICKAXE = registerItem(new PickaxeItem(ModToolMaterial.SEA_CRYSTAL, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, 2, -2.0F))), "sea_crystal_pickaxe");
    public static final Item SEA_CRYSTAL_AXE = registerItem(new AxeItem(ModToolMaterial.SEA_CRYSTAL, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, 7, -2.4F))), "sea_crystal_axe");
    public static final Item SEA_CRYSTAL_HOE = registerItem(new HoeItem(ModToolMaterial.SEA_CRYSTAL, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, -1, -1.8F))), "sea_crystal_hoe");

    //PYRITE
    public static final Item PYRITE_CHUNK = registerItem(new Item(new Item.Settings()), "pyrite_chunk");
    public static final Item OLD_LORDS_FLAME = registerItem(new Item(new Item.Settings()), "old_lords_flame");
    public static final Item SHACKLE_OF_IMPRISONMENT = registerItem(new ShackleOfImprisonment(new Item.Settings()), "shackle_of_imprisonment");
    public static final Item RESTORED_PYRITE = registerItem(new Item(new Item.Settings()), "restored_pyrite");
    public static final Item PYRITE_PRIMORDIAL_CUBE = registerItem(new PyritePrimordialCube(new Item.Settings()), "pyrite_primordial_cube1");

    public static final Item PYRITE_SWORD = registerItem(new SwordItem(ModToolMaterial.PYRITE, (new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.PYRITE, 8, -1.4F)))), "pyrite_sword");
    public static final Item PYRITE_SHOVEL = registerItem(new ShovelItem(ModToolMaterial.PYRITE, (new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.PYRITE, 3, -1.8F)))), "pyrite_shovel");
    public static final Item PYRITE_PICKAXE = registerItem(new PickaxeItem(ModToolMaterial.PYRITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.PYRITE, 4, -1.6F))), "pyrite_pickaxe");
    public static final Item PYRITE_AXE = registerItem(new AxeItem(ModToolMaterial.PYRITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.PYRITE, 9, -2.1F))), "pyrite_axe");
    public static final Item PYRITE_HOE = registerItem(new PyriteHoe(ModToolMaterial.PYRITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.PYRITE, 7, -1.1F))), "pyrite_hoe");

    public static final Item PYRITE_HELMET = registerItem(new ArmorItem(ModArmorMaterial.PYRITE, ArmorItem.Type.HELMET , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "pyrite_helmet");
    public static final Item PYRITE_CHESTPLATE = registerItem(new ArmorItem(ModArmorMaterial.PYRITE, ArmorItem.Type.CHESTPLATE , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "pyrite_chestplate");
    public static final Item PYRITE_LEGGINGS = registerItem(new ArmorItem(ModArmorMaterial.PYRITE, ArmorItem.Type.LEGGINGS , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "pyrite_leggings");
    public static final Item PYRITE_BOOTS = registerItem(new ArmorItem(ModArmorMaterial.PYRITE, ArmorItem.Type.BOOTS , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "pyrite_boots");



    public static <T extends Item> T registerItem(T item, String path) {
        T value =  Registry.register(Registries.ITEM, Identifier.of(CyraFinal.MOD_ID, path), item);
        ITEMS.add(value);
        return  value;
    }

    public static void registerModItems() {
    }

    public static void fillTab(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries) {
        ITEMS.stream().sorted(Comparator.comparing(item -> Registries.ITEM.getKey(item) .map(RegistryKey::toString).orElseThrow())).forEach(item -> entries.add(new ItemStack(item)));

    }
}
