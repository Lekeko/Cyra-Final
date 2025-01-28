package com.keko.blocks;

import com.keko.CyraFinal;
import com.keko.blocks.blocksWithInterface.AlchemyTable;
import com.keko.blocks.blocksWithInterface.PyriteFabricator;
import com.keko.blocks.environment.SupporterBlock;
import com.keko.blocks.environment.dim1.*;
import com.keko.blocks.environment.dim1.furniture.*;
import com.keko.blocks.environment.dim1.kyanite.KyaniteCrystalBlock;
import com.keko.blocks.environment.dim1.roseCrystal.RoseCrystalBlock;
import com.keko.blocks.environment.specialBlocks.QueenOfTheSeasSpawnerBlock;
import com.keko.blocks.environment.specialBlocks.SkeletonLeaderSpawnerBlock;
import com.keko.blocks.environment.specialBlocks.TheOldLordSpawnerBlock;
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
    public static final  Block VOID_PYRITE_ORE = registerBlock(new Block(Block.Settings.create().strength(40.0F, 1400.0F).requiresTool()), "void_sea_stone_pyrite_ore", new Item.Settings());
    public static final  Block ANCIENT_PYRITE_ORE = registerBlock(new Block(Block.Settings.create().strength(40.0F, 1400.0F).requiresTool()), "ancient_void_sea_stone_pyrite_ore", new Item.Settings());
    public static final  Block PYRITE_LAMP = registerBlock(new PyriteLamp(Block.Settings.create().strength(40.0F, 1400.0F).nonOpaque().luminance((state) -> 12)), "pyrite_lamp", new Item.Settings());



    public static final  Block SEA_CRYSTAL_FORMATION = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_formation", new Item.Settings());

    public static final  Block SEA_CRYSTAL_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks", new Item.Settings());

    public static final  Block PYRITE_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "pyrite_bricks", new Item.Settings());
    public static final  Block PYRITE_BRICK_WALLS = registerBlock(new WallBlock(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "pyrite_brick_walls", new Item.Settings());
    public static final  Block PYRITE_BRICK_SLAB = registerBlock(new SlabBlock(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "pyrite_brick_slab", new Item.Settings());
    public static final  Block PYRITE_BRICK_STAIR = registerBlock(new StairsBlock(PYRITE_BRICKS.getDefaultState(), Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "pyrite_brick_stairs", new Item.Settings());
    public static final  Block PYRITE_BRICK_DOOR = registerBlock(new PyriteDoor(BlockSetType.OAK, Block.Settings.create().strength(10.0F, 200.0F).requiresTool().nonOpaque()), "pyrite_brick_door", new Item.Settings());
    public static final  Block PYRITE_BRICK_TRAPDOOR = registerBlock(new PyriteTrapdoor(BlockSetType.OAK, Block.Settings.create().strength(10.0F, 200.0F).requiresTool().nonOpaque()), "pyrite_brick_trapdoor", new Item.Settings());

    public static final  Block CHISELED_PYRITE_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "CHISELED_PYRITE_BRICKS".toLowerCase(), new Item.Settings());
    public static final  Block SMOOTH_PYRITE_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "SMOOTH_PYRITE_BRICKS".toLowerCase(), new Item.Settings());
    public static final  Block TILES_PYRITE_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "TILES_PYRITE_BRICKS".toLowerCase(), new Item.Settings());
    public static final  Block PILLAR_PYRITE_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "PILLAR_PYRITE_BRICKS".toLowerCase(), new Item.Settings());
    public static final  Block SMOOTH_PYRITE = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool().luminance((state) -> 15)), "SMOOTH_PYRITE".toLowerCase(), new Item.Settings());
    public static final  Block TILE_PYRITE = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool().luminance((state) -> 15)), "TILE_PYRITE".toLowerCase(), new Item.Settings());
    public static final  Block CHISELED_EYE_PYRITE_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "CHISELED_EYE_PYRITE_BRICKS".toLowerCase(), new Item.Settings());
    public static final  Block PYRITE_BLOCK_LAMP = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool().luminance((state) -> 15)), "PYRITE_BLOCK_LAMP".toLowerCase(), new Item.Settings());


    public static final  Block VOID_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "void_bricks", new Item.Settings());
    public static final  Block CHISELED_VOID_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "CHISELED_void_BRICKS".toLowerCase(), new Item.Settings());
    public static final  Block SMOOTH_VOID_BRICKS = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "SMOOTH_void_BRICKS".toLowerCase(), new Item.Settings());


    public static final  Block SEA_CRYSTAL_BLOCK = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_block", new Item.Settings());
    public static final  Block ENDERITE_BLOCK = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "enderite_block", new Item.Settings());
    public static final  Block PYRITE_BLOCK = registerBlock(new Block(Block.Settings.create().strength(10.0F, 200.0F).requiresTool().luminance((state) -> 10)), "pyrite_block", new Item.Settings());

    public static final  Block SEA_CRYSTAL_BRICKS_STAIRS = registerBlock(new StairsBlock(SEA_CRYSTAL_BRICKS.getDefaultState(), Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks_stairs", new Item.Settings());
    public static final  Block SEA_CRYSTAL_BRICKS_SLAB = registerBlock(new SlabBlock(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks_slab", new Item.Settings());

    public static final  Block SEA_CRYSTAL_BRICKS_WALL  = registerBlock(new WallBlock(Block.Settings.create().strength(10.0F, 200.0F).requiresTool()), "sea_crystal_bricks_wall", new Item.Settings());
    public static final  Block SEA_CRYSTAL_BRICKS_DOOR = registerBlock(new SeaCrystalBricksDoor(BlockSetType.OAK , Block.Settings.create().strength(10.0F, 200.0F).requiresTool().nonOpaque()), "sea_crystal_bricks_door", new Item.Settings());
    public static final  Block SEA_CRYSTAL_BRICKS_TRAPDOOR = registerBlock(new SeaCrystalBricksTrapdoor(BlockSetType.OAK , Block.Settings.create().strength(10.0F, 200.0F).requiresTool().nonOpaque()), "sea_crystal_bricks_trapdoor", new Item.Settings());

    public static final  Block SEA_STONE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_stone", new Item.Settings());
    public static final  Block DAZED_SEA_STONE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "dazed_sea_stone", new Item.Settings());
    public static final  Block VOID_STONE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "void_sea_stone", new Item.Settings());
    public static final  Block BIOLUMINESCENCE_WOOD = registerBlock(new PillarBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM)), "bioluminescence_wood", new Item.Settings());
    public static final  Block DAZED_WOOD = registerBlock(new PillarBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM)), "dazed_wood", new Item.Settings());
    public static final  Block DAZED_LEAVES = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.NETHER_WART_BLOCK)), "dazed_leaves", new Item.Settings());
    public static final  Block BIOLUMINESCENCE_LEAVES = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.NETHER_WART_BLOCK)), "bioluminescence_leaves", new Item.Settings());

    //BIOLUMINESCNECE WOOD THINGS
    public static final  Block BIOLUMINESCENCE_PLANKS = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), "BIOLUMINESCENCE_PLANKS".toLowerCase(), new Item.Settings());
    public static final  Block BIOLUMINESCENCE_STAIRS = registerBlock(new StairsBlock(BIOLUMINESCENCE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), "BIOLUMINESCENCE_STAIRS".toLowerCase(), new Item.Settings());
    public static final  Block BIOLUMINESCENCE_SLAB = registerBlock(new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)), "BIOLUMINESCENCE_SLAB".toLowerCase(), new Item.Settings());
    public static final  Block BIOLUMINESCENCE_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)), "BIOLUMINESCENCE_PRESSURE_PLATE".toLowerCase(), new Item.Settings());
    public static final  Block BIOLUMINESCENCE_BUTTON = registerBlock(new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)), "BIOLUMINESCENCE_BUTTON".toLowerCase(), new Item.Settings());
    public static final  Block BIOLUMINESCENCE_FENCE = registerBlock(new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)), "BIOLUMINESCENCE_FENCE".toLowerCase(), new Item.Settings());
    public static final  Block BIOLUMINESCENCE_FENCE_GATE = registerBlock(new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)), "BIOLUMINESCENCE_FENCE_GATE".toLowerCase(), new Item.Settings());
    public static final  Block BIOLUMINESCENCE_DOOR = registerBlock(new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)), "BIOLUMINESCENCE_DOOR".toLowerCase(), new Item.Settings());
    public static final  Block BIOLUMINESCENCE_TRAPDOOR = registerBlock(new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)), "BIOLUMINESCENCE_TRAPDOOR".toLowerCase(), new Item.Settings());

    public static final  Block DAZED_BIOLUMINESCENCE_PLANKS = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), "DAZED_BIOLUMINESCENCE_PLANKS".toLowerCase(), new Item.Settings());
    public static final  Block DAZED_BIOLUMINESCENCE_STAIRS = registerBlock(new StairsBlock(DAZED_BIOLUMINESCENCE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), "DAZED_BIOLUMINESCENCE_STAIRS".toLowerCase(), new Item.Settings());
    public static final  Block DAZED_BIOLUMINESCENCE_SLAB = registerBlock(new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)), "DAZED_BIOLUMINESCENCE_SLAB".toLowerCase(), new Item.Settings());
    public static final  Block DAZED_BIOLUMINESCENCE_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)), "DAZED_BIOLUMINESCENCE_PRESSURE_PLATE".toLowerCase(), new Item.Settings());
    public static final  Block DAZED_BIOLUMINESCENCE_BUTTON = registerBlock(new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)), "DAZED_BIOLUMINESCENCE_BUTTON".toLowerCase(), new Item.Settings());
    public static final  Block DAZED_BIOLUMINESCENCE_FENCE = registerBlock(new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)), "DAZED_BIOLUMINESCENCE_FENCE".toLowerCase(), new Item.Settings());
    public static final  Block DAZED_BIOLUMINESCENCE_FENCE_GATE = registerBlock(new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)), "DAZED_BIOLUMINESCENCE_FENCE_GATE".toLowerCase(), new Item.Settings());
    public static final  Block DAZED_BIOLUMINESCENCE_DOOR = registerBlock(new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)), "DAZED_BIOLUMINESCENCE_DOOR".toLowerCase(), new Item.Settings());
    public static final  Block DAZED_BIOLUMINESCENCE_TRAPDOOR = registerBlock(new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)), "DAZED_BIOLUMINESCENCE_TRAPDOOR".toLowerCase(), new Item.Settings());


    public static final  Block SEA_STONE_GRASS = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_stone_grass", new Item.Settings());
    public static final  Block SEA_STONE_GRASS_DAZED = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_stone_grass_dazed", new Item.Settings());

    public static final  Block CRYSTAL_SEA_GRASS = registerBlock(new CrystalSeaGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 8).nonOpaque()), "crystal_sea_grass", new Item.Settings());
    public static final  Block CRYSTAL_SEA_GRASS_DAZED = registerBlock(new CrystalSeaGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 8).nonOpaque()), "crystal_sea_grass_dazed", new Item.Settings());
    public static final  Block CRYSTAL_SEA_WEED = registerBlock(new SeaWeedBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 10).nonOpaque()), "crystal_sea_weed", new Item.Settings());
    public static final  Block DAZED_CRYSTAL_SEA_WEED = registerBlock(new SeaWeedBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 10).nonOpaque()), "dazed_crystal_sea_weed", new Item.Settings());
    public static final  Block DEEP_SEA_LANTERN = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 1.5F).luminance((state) -> 15) .sounds(BlockSoundGroup.AMETHYST_CLUSTER)), "deep_sea_lantern", new Item.Settings());
    public static final  Block DEEP_LANTERN = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 1.5F).luminance((state) -> 15) .sounds(BlockSoundGroup.AMETHYST_CLUSTER)), "deep_lantern", new Item.Settings());
    public static final  Block ROSE_CRYSTAL = registerBlock(new RoseCrystalBlock(AbstractBlock.Settings.create().mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 1.5F).luminance((state) -> 15) .sounds(BlockSoundGroup.AMETHYST_CLUSTER)), "rose_crystal", new Item.Settings());
    public static final  Block KYANITE_CRYSTAL = registerBlock(new KyaniteCrystalBlock(AbstractBlock.Settings.create().mapColor(MapColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 1.5F).luminance((state) -> 15) .sounds(BlockSoundGroup.AMETHYST_CLUSTER)), "kyanite_crystal", new Item.Settings());
    public static final  Block SUPPORTER = registerBlock(new SupporterBlock(AbstractBlock.Settings.create().nonOpaque().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).luminance((state) -> 15).requiresTool().strength(1323123.5F, 1321236.5F).sounds(BlockSoundGroup.LANTERN)), "supporter", new Item.Settings());
    public static final  Block SEA_MIRIANITE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_mirianite", new Item.Settings());
    public static final  Block SEA_MURIANITE = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "sea_murianite", new Item.Settings());
    public static final  Block SEA_STONE_BRICK = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)), "sea_stone_brick", new Item.Settings());
    public static final  Block DAZED_SEA_STONE_BRICK = registerBlock(new Block(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F)), "dazed_sea_stone_brick", new Item.Settings());


    public static final  Block CORE_OF_THE_SEA = registerBlock(new CoreOfTheSeaBlock(Block.Settings.create().strength(20.0F, 1200.0F).requiresTool().luminance((state) -> 15)), "core_of_the_sea", new Item.Settings());
    public static final Block ALCHEMY_TABLE = registerBlock(new AlchemyTable(AbstractBlock.Settings.copy(Blocks.BEACON)),"alchemy_table", new Item.Settings());
    public static final Block PYRITE_FABRICATOR = registerBlock(new PyriteFabricator(AbstractBlock.Settings.copy(Blocks.BEACON)),"pyrite_fabricator", new Item.Settings());








    //bosses
    public static final Block ZOMBIE_LEADER_BLOCK = registerBlock(new ZombieLeaderSpawnerBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)),"zombie_leader_spawner_block", new Item.Settings());
    public static final Block SKELETON_LEADER_BLOCK = registerBlock(new SkeletonLeaderSpawnerBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)),"skeleton_leader_spawner_block", new Item.Settings());
    //public static final Block QUEEN_OF_THE_SEAS_BLOCK = registerBlock(new QueenOfTheSeasSpawnerBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)),"queen_of_the_seas_spawner_block", new Item.Settings());
    public static final Block OLD_LORD_BLOCK = registerBlock(new TheOldLordSpawnerBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)),"old_lord_spawner_block", new Item.Settings());


    //dumb blocks

    public static final Block CHAIN_BLOCK_GENERATOR_BLOCK = registerBlock(new ChainBlockGeneratorBlock(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F, 6.5F).sounds(BlockSoundGroup.DEEPSLATE)), "skibidi", new Item.Settings());

    private static Block registerBlock(Block block, String path, Item.Settings settings) {
       ModItems.registerItem(new BlockItem(block, settings), path);
       return Registry.register(Registries.BLOCK, Identifier.of(CyraFinal.MOD_ID, path), block);

    }

    public static void registerModBlocks() {

    }

}
