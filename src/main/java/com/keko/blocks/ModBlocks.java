package com.keko.blocks;

import com.keko.CyraFinal;
import com.keko.blocks.environment.AlchemyTable;
import com.keko.blocks.environment.dim1.CoreOfTheSeaBlock;
import com.keko.blocks.environment.dim1.SeaCrystalCluster;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;


public class ModBlocks {

    public static final  Block ENDERITE_ORE = registerBlock(new Block(Block.Settings.create().strength(30.0F, 1200.0F).requiresTool()), "enderite_ore");
    public static final  Block SEA_CRYSTAL_CLUSTER = registerBlock(new SeaCrystalCluster(7.0F, 3.0F, Block.Settings.create().strength(20.0F, 1000.0F).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().luminance((state) -> 20).solid().nonOpaque()), "sea_crystal_cluster");
    public static final  Block PYRITE_ORE = registerBlock(new Block(Block.Settings.create().strength(40.0F, 1400.0F).requiresTool()), "pyrite_ore");


    public static final  Block SEA_CRYSTAL_FORMATION = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_formation");
    public static final  Block SEA_STONE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_stone");
    public static final  Block SEA_MIRIANITE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_mirianite");
    public static final  Block SEA_MURIANITE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_murianite");
    public static final  Block SEA_STONE_BRICK = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)), "sea_stone_brick");

    public static final  Block CORE_OF_THE_SEA = registerBlock(new CoreOfTheSeaBlock(Block.Settings.create().strength(20.0F, 1200.0F).requiresTool().luminance((state) -> 20)), "core_of_the_sea");
    public static final  Block PRISMATIC_LEAVES = registerBlock(new LeavesBlock(Block.Settings.create().strength(5.2F, 10.0F).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().luminance((state) -> 15)), "prismatic_leaves");
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
