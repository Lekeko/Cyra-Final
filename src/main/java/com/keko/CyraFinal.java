package com.keko;

import com.keko.blocks.ModBlocks;
import com.keko.items.ModItems;
import com.keko.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyraFinal implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cyra");
	public static final String MOD_ID = "cyra";

	@Override
	public void onInitialize() {
		ModWorldGen.generateModWorldGen();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		LOGGER.info("Esti foarte amuzant");
	}
}