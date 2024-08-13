package com.keko.data;

import com.keko.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModitemTagProvider extends FabricTagProvider<Item> {

    public ModitemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.ENDERITE_SWORD);
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.ENDERITE_SHOVEL);
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.ENDERITE_AXE);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.ENDERITE_PICKAXE);
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.ENDERITE_HOE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.ENDERITE_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.ENDERITE_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.ENDERITE_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.ENDERITE_BOOTS);
    }
}
