package com.keko.blocks;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags  {

    public static final TagKey<Block> INCORRECT_FOR_ENDERITE_TOOL = of("incorrect_for_enderite_tool");
    public static final TagKey<Block> INCORRECT_FOR_SEA_CRYSTAL_TOOL = of("incorrect_for_sea_crystal_tool");
    public static final TagKey<Block> INCORRECT_FOR_PYRITE_TOOL = of("incorrect_for_pyrite_tool");
    public static final TagKey<Block> INCORRECT_FOR_BAUXITE_TOOL = of("incorrect_for_bauxite_tool");
    public static final TagKey<Block> INCORRECT_FOR_VULCANIUM_TOOL = of("incorrect_for_vulcanium_tool");
    public static final TagKey<Block> INCORRECT_FOR_CYRANIUM_TOOL = of("incorrect_for_cyranium_tool");

    public ModBlockTags() {
    }

    public static void register(){}

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of("fabric", id));
    }
}
