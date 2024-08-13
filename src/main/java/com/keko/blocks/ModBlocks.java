package com.keko.blocks;

import com.keko.CyraFinal;
import com.keko.blocks.environment.AlchemyTable;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;


public class ModBlocks {

    public static final  Block ENDERITE_ORE = registerBlock(new Block(Block.Settings.create().strength(30.0F, 1200.0F).requiresTool()), "enderite_ore");
    public static final Block ALCHEMY_TABLE = registerBlock(new AlchemyTable(AbstractBlock.Settings.copy(Blocks.BEACON)),"alchemy_table");

    private static Block registerBlock(Block block, String path) {
        registerBlockItem(block, path);
       return Registry.register(Registries.BLOCK, Identifier.of(CyraFinal.MOD_ID, path), block);

    }

    private static Item registerBlockItem(Block block, String path) {
        return Registry.register(Registries.ITEM, Identifier.of(CyraFinal.MOD_ID, path), new BlockItem(block, new Item.Settings()));

    }

    public static void registerModBlocks() {

    }

}
