package com.keko.blocks;

import com.keko.CyraFinal;
import com.keko.blocks.environment.AlchemyTable;
import com.keko.blocks.environment.SupporterBlock;
import com.keko.blocks.environment.dim1.*;
import com.keko.blocks.environment.specialBlocks.ZombieLeaderSpawnerBlock;
import com.keko.blocks.environment.dim1.SeaWeedBlock;
import com.keko.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;


public class ModBlocks {

    public static final  Block ENDERITE_ORE = registerBlock(new Block(Block.Settings.create().strength(30.0F, 1200.0F).requiresTool()), "enderite_ore", new Item.Settings());
    public static final  Block SEA_CRYSTAL_CLUSTER = registerBlock(new SeaCrystalCluster(7.0F, 3.0F, Block.Settings.create().strength(20.0F, 1000.0F).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().luminance((state) -> 20).solid().nonOpaque()), "sea_crystal_cluster", new Item.Settings());
    public static final  Block PYRITE_ORE = registerBlock(new Block(Block.Settings.create().strength(40.0F, 1400.0F).requiresTool()), "pyrite_ore", new Item.Settings());


    public static final Block CRYSTAL_SAPLING = registerBlock(
            new CrystalSaplingBlock(AbstractBlock.Settings.create()), "crystal_sapling", new Item.Settings());

    public static final  Block SEA_CRYSTAL_FORMATION = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_formation", new Item.Settings());

    public static final  Block SEA_CRYSTAL_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks", new Item.Settings());
    public static final  Block SEA_CRYSTAL_BLOCK = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_block", new Item.Settings());
    public static final  Block ENDERITE_BLOCK = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "enderite_block", new Item.Settings());

    public static final  Block SEA_CRYSTAL_BRICKS_STAIRS = registerBlock(new StairsBlock(SEA_CRYSTAL_BRICKS.getDefaultState(), Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks_stairs", new Item.Settings());
    public static final  Block SEA_CRYSTAL_BRICKS_SLAB = registerBlock(new SlabBlock(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks_slab", new Item.Settings());

    public static final  Block SEA_CRYSTAL_BRICKS_WALL  = registerBlock(new WallBlock(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks_wall", new Item.Settings());
    public static final  Block SEA_CRYSTAL_BRICKS_DOOR = registerBlock(new DoorBlock(BlockSetType.OAK , Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks_door", new Item.Settings());
    public static final  Block SEA_CRYSTAL_BRICKS_TRAPDOOR = registerBlock(new TrapdoorBlock(BlockSetType.OAK , Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks_trapdoor", new Item.Settings());

    public static final  Block SEA_STONE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_stone", new Item.Settings());
    public static final  Block BIOLUMINESCENCE_WOOD = registerBlock(new PillarBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM)), "bioluminescence_wood", new Item.Settings());
    public static final  Block BIOLUMINESCENCE_LEAVES = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.NETHER_WART_BLOCK).luminance((state) -> 4)), "bioluminescence_leaves", new Item.Settings());

    public static final  Block SEA_STONE_GRASS = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_stone_grass", new Item.Settings());

    public static final  Block CRYSTAL_SEA_GRASS = registerBlock(new CrystalSeaGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 8).nonOpaque()), "crystal_sea_grass", new Item.Settings());
    public static final  Block CRYSTAL_SEA_WEED = registerBlock(new SeaWeedBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 8).nonOpaque()), "crystal_sea_weed", new Item.Settings());
    public static final  Block TALL_CRYSTAL_SEA_GRASS_TOP = registerBlock(new CrystalSeaGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 8).nonOpaque()), "tall_crystal_sea_grass_top", new Item.Settings());
    public static final  Block TALL_CRYSTAL_SEA_GRASS_BOTTOM = registerBlock(new CrystalSeaGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 8).nonOpaque()), "tall_crystal_sea_grass_bottom", new Item.Settings());
    public static final  Block DEEP_SEA_LANTERN = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 1.5F).luminance((state) -> 30) .sounds(BlockSoundGroup.AMETHYST_CLUSTER)), "deep_sea_lantern", new Item.Settings());
    public static final  Block DEEP_LANTERN = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 1.5F).luminance((state) -> 30) .sounds(BlockSoundGroup.AMETHYST_CLUSTER)), "deep_lantern", new Item.Settings());
    public static final  Block SUPPORTER = registerBlock(new SupporterBlock(AbstractBlock.Settings.create().nonOpaque().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).luminance((state) -> 20).requiresTool().strength(1323123.5F, 1321236.5F).sounds(BlockSoundGroup.LANTERN)), "supporter", new Item.Settings());
    public static final  Block SEA_MIRIANITE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_mirianite", new Item.Settings());
    public static final  Block SEA_MURIANITE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_murianite", new Item.Settings());
    public static final  Block SEA_STONE_BRICK = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)), "sea_stone_brick", new Item.Settings());


    public static final  Block CORE_OF_THE_SEA = registerBlock(new CoreOfTheSeaBlock(Block.Settings.create().strength(20.0F, 1200.0F).requiresTool().luminance((state) -> 20)), "core_of_the_sea", new Item.Settings());
    public static final  Block PRISMATIC_LEAVES = registerBlock(new LeavesBlock(Block.Settings.create().strength(5.2F, 10.0F).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().luminance((state) -> 15)), "prismatic_leaves", new Item.Settings());
    public static final Block ALCHEMY_TABLE = registerBlock(new AlchemyTable(AbstractBlock.Settings.copy(Blocks.BEACON)),"alchemy_table", new Item.Settings());
    public static final Block ZOMBIE_LEADER_BLOCK = registerBlock(new ZombieLeaderSpawnerBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)),"zombie_leader_spawner_block", new Item.Settings());


    //dumb blocks

    public static final Block CHAIN_BLOCK_GENERATOR_BLOCK = registerBlock(new ChainBlockGeneratorBlock(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "skibidi", new Item.Settings());

    private static Block registerBlock(Block block, String path, Item.Settings settings) {
       ModItems.registerItem(new BlockItem(block, settings), path);
       return Registry.register(Registries.BLOCK, Identifier.of(CyraFinal.MOD_ID, path), block);

    }

    public static void registerModBlocks() {

    }

}
