package com.keko.blocks;

import com.keko.CyraFinal;
import com.keko.blocks.environment.AlchemyTable;
import com.keko.blocks.environment.dim1.CoreOfTheSeaBlock;
import com.keko.world.ModConfiguredFeatures;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.lang.module.Configuration;
import java.util.Optional;


public class ModBlocks {

    public static final  Block ENDERITE_ORE = registerBlock(new Block(Block.Settings.create().strength(30.0F, 1200.0F).requiresTool()), "enderite_ore");
    public static final  Block CORE_OF_THE_SEA = registerBlock(new CoreOfTheSeaBlock(Block.Settings.create().strength(20.0F, 1200.0F).requiresTool().luminance((state) -> 20)), "core_of_the_sea");
    public static final  Block PRISMATIC_LEAVES = registerBlock(new LeavesBlock(Block.Settings.create().strength(5.2F, 10.0F).nonOpaque().sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().luminance((state) -> 15)), "prismatic_leaves");
    public static final Block ALCHEMY_TABLE = registerBlock(new AlchemyTable(AbstractBlock.Settings.copy(Blocks.BEACON)),"alchemy_table");
    public static final SaplingBlock PRISMATIC_SAMPLING = (SaplingBlock) registerBlock(
            new SaplingBlock(
                    new SaplingGenerator(
                           CyraFinal.id("prismatic").toString(),
                            0.1f,
                            Optional.empty(),
                            Optional.empty(),
                            Optional.of(ModConfiguredFeatures.PRISMATIC_TREE_KEY),
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty()
                    ),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.RED)
                            .ticksRandomly()
                            .strength(0.0f)
                            .nonOpaque()
                            .allowsSpawning(Blocks::canSpawnOnLeaves)
                            .suffocates(Blocks::never)
                            .blockVision(Blocks::never)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .solidBlock(Blocks::never)
                            .noCollision())
            ,"prismatic_sapling");
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
