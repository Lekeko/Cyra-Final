package com.keko.items;

import com.keko.CyraFinal;
import com.keko.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CyraFinal.MOD_ID, "cyra"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.cyra"))
                    .icon(() -> new ItemStack(ModItems.SEA_CRYSTAL_PICKAXE)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.ENDERITE_ORE);
                        entries.add(ModBlocks.SEA_STONE);
                        entries.add(ModBlocks.SEA_MIRIANITE);
                        entries.add(ModBlocks.SEA_MURIANITE);
                        entries.add(ModBlocks.PYRITE_ORE);
                        entries.add(ModBlocks.SEA_STONE_BRICK);
                        entries.add(ModItems.ENDERITE_CHUNK);
                        entries.add(ModItems.ENDERITE_INGOT);
                        entries.add(ModItems.ENDERITE_SWORD);
                        entries.add(ModItems.ENDERITE_PICKAXE);
                        entries.add(ModItems.ENDERITE_AXE);
                        entries.add(ModItems.ENDERITE_SHOVEL);
                        entries.add(ModItems.ENDERITE_HOE);
                        entries.add(ModItems.ENDERITE_HELMET);
                        entries.add(ModItems.ENDERITE_CHESTPLATE);
                        entries.add(ModItems.ENDERITE_LEGGINGS);
                        entries.add(ModItems.ENDERITE_BOOTS);
                        entries.add(ModItems.HEALING_FLASK);
                        entries.add(ModItems.BUFF_FLASK);
                        entries.add(ModItems.ORB_OF_BOUND);
                        entries.add(ModItems.ORB_OF_FORCE);
                        entries.add(ModItems.ORB_OF_DAHY);
                        entries.add(ModItems.ORB_OF_IMPETUOSITY);
                        entries.add(ModItems.ORB_OF_VITALITY);
                        entries.add(ModBlocks.ALCHEMY_TABLE);
                        entries.add(ModBlocks.SEA_CRYSTAL_CLUSTER);
                        entries.add(ModItems.SEA_CRYSTAL);
                        entries.add(ModItems.SEA_CRYSTAL_FRAGMENT);
                        entries.add(ModItems.SEA_CRYSTAL_AXE);
                        entries.add(ModItems.SEA_CRYSTAL_HELMET);
                        entries.add(ModItems.SEA_CRYSTAL_CHESTPLATE);
                        entries.add(ModItems.SEA_CRYSTAL_LEGGINGS);
                        entries.add(ModItems.SEA_CRYSTAL_BOOTS);
                        entries.add(ModItems.SEA_CRYSTAL_SWORD);
                        entries.add(ModItems.SEA_CRYSTAL_PICKAXE);
                        entries.add(ModItems.SEA_CRYSTAL_SHOVEL);
                        entries.add(ModItems.SEA_CRYSTAL_HOE);
                        entries.add(ModItems.SEA_CRYSTAL_STRIDERS);
                    }).build());


    public static void registerItemGroups() {
    }
}
