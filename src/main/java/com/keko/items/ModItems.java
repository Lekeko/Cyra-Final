package com.keko.items;

import com.keko.CyraFinal;
import com.keko.entities.ModEntities;
import com.keko.entities.projectiles.electroCharge.ElectroCharge;
import com.keko.food.ModFoods;
import com.keko.items.armor.ModArmorItem;
import com.keko.items.bossItems.spawners.AncientTitFer;
import com.keko.items.bossItems.spawners.GildedSpine;
import com.keko.items.bossItems.spawners.JellyCrown;
import com.keko.items.bossItems.spawners.RottenNecklace;
import com.keko.items.tools.*;
import com.keko.items.tools.pyrite.OldLordsFlameItem;
import com.keko.items.tools.pyrite.PyriteHoe;
import com.keko.items.weapons.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ModItems {
    public static final Set<Item> ITEMS = new HashSet<>();

    /*MISC*/

    public static final Item ORB_OF_BOUND = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "orb_of_bound");
    public static final Item ORB_OF_DAHY = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "orb_of_dahy");
    public static final Item ORB_OF_FORCE = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "orb_of_force");
    public static final Item ORB_OF_IMPETUOSITY = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "orb_of_impetuosity");
    public static final Item ORB_OF_VITALITY = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "orb_of_vitality");
    public static final Item ORB_OF_ZEPHYR = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "orb_of_zephyr");
    public static final Item BROKEN_PYRITE_PRIMORDIAL_CUBE = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)), "broken_pyrite_primordial_cube");
    public static final Item STRANGE_LOOKING_ROD = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)), "strange_looking_rod");
    public static final Item JELLY_PASTA = registerItem(new Item(new Item.Settings().maxCount(16).food(ModFoods.JELLY)), "jelly_pasta");

    public static final Item DEPTH_CHARM_TIER_1 = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)), "DEPTH_CHARM_TIER_1".toLowerCase());
    public static final Item DEPTH_CHARM_TIER_2 = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)), "DEPTH_CHARM_TIER_2".toLowerCase());
    public static final Item DEPTH_CHARM_TIER_3 = registerItem(new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)), "DEPTH_CHARM_TIER_3".toLowerCase());

    public static final Item HEALING_FLASK = registerItem(new HealingFlask(new Item.Settings().maxCount(1)), "healing_flask");
    public static final Item BUFF_FLASK = registerItem(new BuffFlask(new Item.Settings().maxCount(1)), "buff_flask");


    public static final Item ROTTEN_NECKLACE = registerItem(new RottenNecklace(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)), "rotten_necklace");
    public static final Item GILDED_SPINE = registerItem(new GildedSpine(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)), "gilded_spine");
    public static final Item ANCIENT_TIT_FER = registerItem(new AncientTitFer(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)), "ANCIENT_TIT_FER".toLowerCase());
    public static final Item JELLY_CROWN = registerItem(new JellyCrown(new Item.Settings().maxCount(1)), "jelly_crown");
    public static final Item ELECTRO_CHARGE = registerItem(new ElectroChargeWeapon(new Item.Settings().maxCount(16).rarity(Rarity.RARE)), "electro_charge1");
    public static final Item JELLY_TENTACLES = registerItem(new Item(new Item.Settings()), "jelly_tentacles");
    public static final Item FLASHLIGHT = registerItem(new FlashLight(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "sea_crystal_flash_light");

    //fishes :>

    public static final Item DEEP_SALMON_BUCKET = registerItem(new EntityBucketItem(ModEntities.DEEP_SALMON, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)), "deep_salmon_bucket");
    public static final Item GROXION_BUCKET = registerItem(new EntityBucketItem(ModEntities.GROXION, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)), "groxion_bucket");
    public static final Item SEA_RODENT_BUCKET = registerItem(new EntityBucketItem(ModEntities.SEA_RODENT, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)), "sea_rodent_bucket");
    public static final Item STRIDELY_BUCKET = registerItem(new EntityBucketItem(ModEntities.STRIDELY, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)), "stridely_bucket");

    //FISHES TO EAT NOOOOO
    public static final Item DEEP_SALMON = registerItem(new Item(new Item.Settings().maxCount(64).food(ModFoods.DEEP_SALMON)), "deep_salmon");
    public static final Item SEA_RODENT = registerItem(new Item(new Item.Settings().maxCount(64).food(ModFoods.SEA_RODENT)), "sea_rodent");
    public static final Item GROXION = registerItem(new Item(new Item.Settings().maxCount(64).food(ModFoods.GROXION)), "groxion");
    public static final Item STRIDELY = registerItem(new Item(new Item.Settings().maxCount(64).food(ModFoods.STRIDELY)), "stridely");
    public static final Item COOKED_DEEP_SALMON = registerItem(new Item(new Item.Settings().maxCount(64).food(ModFoods.COOKED_DEEP_SALMON)), "cooked_deep_salmon");
    public static final Item COOKED_SEA_RODENT = registerItem(new Item(new Item.Settings().maxCount(64).food(ModFoods.COOKED_SEA_RODENT)), "cooked_sea_rodent");
    public static final Item COOKED_GROXION = registerItem(new Item(new Item.Settings().maxCount(64).food(ModFoods.COOKED_GROXION)), "cooked_groxion");
    public static final Item FRIED_STRIDELY = registerItem(new Item(new Item.Settings().maxCount(64).food(ModFoods.FRIED_STRIDELY)), "fried_stridely");




    //O2 Tanks
    public static final Item ENDERITE_OXYGEN_TANK = registerItem(new OxygenTank(new Item.Settings().maxDamage(10 * 60 * 20).rarity(Rarity.UNCOMMON)), "ENDERITE_OXYGEN_TANK".toLowerCase());
    public static final Item SEA_CRYSTAL_OXYGEN_TANK = registerItem(new OxygenTank(new Item.Settings().maxDamage(20 * 60 * 20).rarity(Rarity.UNCOMMON)), "SEA_CRYSTAL_OXYGEN_TANK".toLowerCase());
    public static final Item PYRITE_OXYGEN_TANK = registerItem(new OxygenTank(new Item.Settings().maxDamage(50 * 60 * 20).rarity(Rarity.RARE)), "PYRITE_OXYGEN_TANK".toLowerCase());

    //BOSS WEAPONS
    public static final Item ZOMBIE_AXE = registerItem(new ZombieLeaderBattleAxeItem(ModToolMaterial.ENDERITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.ENDERITE, 6, -2.0F)).rarity(Rarity.UNCOMMON)), "zombie_leader_battle_axe");
    public static final Item SKELETON_BOW = registerItem(new SkeletonLeaderBattleBowitem(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)), "skeleton_leader_battle_bow");


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
    public static final Item SEA_WAND = registerItem(new SeaWandItem(new Item.Settings().rarity(Rarity.RARE)), "sea_support_block_wand");
    public static final Item SEA_SHOOTER = registerItem(new SeaShooter(new Item.Settings().rarity(Rarity.UNCOMMON)), "sea_shooter");
    public static final Item SEA_BOLT =  registerItem(new Item(new Item.Settings().maxCount(64)), "sea_bolt");

    public static final Item SEA_CRYSTAL_HELMET = registerItem(new ModArmorItem(ModArmorMaterial.SEA_CRYSTAL, ArmorItem.Type.HELMET , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "sea_crystal_helmet");
    public static final Item SEA_CRYSTAL_CHESTPLATE = registerItem(new ArmorItem(ModArmorMaterial.SEA_CRYSTAL, ArmorItem.Type.CHESTPLATE , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "sea_crystal_chestplate");
    public static final Item SEA_CRYSTAL_LEGGINGS = registerItem(new ArmorItem(ModArmorMaterial.SEA_CRYSTAL, ArmorItem.Type.LEGGINGS , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "sea_crystal_leggings");
    public static final Item SEA_CRYSTAL_BOOTS = registerItem(new ArmorItem(ModArmorMaterial.SEA_CRYSTAL, ArmorItem.Type.BOOTS , new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))), "sea_crystal_boots");

    public static final Item SEA_CRYSTAL_STRIDERS = registerItem(new SeaCrystalStriders(new Item.Settings().rarity(Rarity.RARE)), "sea_crystal_striders");

    public static final Item SEA_CRYSTAL_SWORD = registerItem(new SwordItem(ModToolMaterial.SEA_CRYSTAL, (new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, 6, -1.8F)))), "sea_crystal_sword");
    public static final Item SEA_CRYSTAL_SHOVEL = registerItem(new ShovelItem(ModToolMaterial.SEA_CRYSTAL, (new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, 2, -2.0F)))), "sea_crystal_shovel");
    public static final Item SEA_CRYSTAL_PICKAXE = registerItem(new PickaxeItem(ModToolMaterial.SEA_CRYSTAL, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, 2, -2.0F))), "sea_crystal_pickaxe");
    public static final Item SEA_CRYSTAL_AXE = registerItem(new AxeItem(ModToolMaterial.SEA_CRYSTAL, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, 7, -2.4F))), "sea_crystal_axe");
    public static final Item SEA_CRYSTAL_HOE = registerItem(new HoeItem(ModToolMaterial.SEA_CRYSTAL, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.SEA_CRYSTAL, -1, -1.8F))), "sea_crystal_hoe");

    //PYRITE
    public static final Item PYRITE_CHUNK = registerItem(new Item(new Item.Settings()), "pyrite_chunk");
    public static final Item OLD_LORDS_FLAME = registerItem(new OldLordsFlameItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "old_lords_flame");
    public static final Item SHACKLE_OF_IMPRISONMENT = registerItem(new ShackleOfImprisonment(new Item.Settings().maxCount(1)), "shackle_of_imprisonment");
    public static final Item RESTORED_PYRITE = registerItem(new Item(new Item.Settings().rarity(Rarity.EPIC)), "restored_pyrite");
    public static final Item PYRITE_PRIMORDIAL_CUBE = registerItem(new PyritePrimordialCube(new Item.Settings().rarity(Rarity.EPIC)), "pyrite_primordial_cube1");
    public static final Item PYRITE_BULWARK = registerItem(new ShieldItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "PYRITE_BULWARK".toLowerCase());
    public static final Item OLD_LORDS_SPEAR = registerItem(new OldLordsSpearItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "old_lords_spear");

    public static final Item PYRITE_SWORD = registerItem(new SwordItem(ModToolMaterial.PYRITE, (new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.PYRITE, 8, -1.4F))).rarity(Rarity.EPIC)), "pyrite_sword");
    public static final Item PYRITE_SHOVEL = registerItem(new ShovelItem(ModToolMaterial.PYRITE, (new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.PYRITE, 3, -1.8F)))), "pyrite_shovel");
    public static final Item PYRITE_PICKAXE = registerItem(new PickaxeItem(ModToolMaterial.PYRITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.PYRITE, 4, -1.6F))), "pyrite_pickaxe");
    public static final Item PYRITE_AXE = registerItem(new AxeItem(ModToolMaterial.PYRITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.PYRITE, 9, -2.1F)).rarity(Rarity.EPIC)), "pyrite_axe");
    public static final Item PYRITE_HOE = registerItem(new PyriteHoe(ModToolMaterial.PYRITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.PYRITE, 7, -1.1F)).rarity(Rarity.EPIC)), "pyrite_hoe");

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
