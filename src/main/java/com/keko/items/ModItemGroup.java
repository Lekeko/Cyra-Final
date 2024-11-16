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
                    .icon(() -> new ItemStack(ModItems.SEA_CRYSTAL_PICKAXE)).entries(ModItems::fillTab).build());


    public static void registerItemGroups() {
    }
}
