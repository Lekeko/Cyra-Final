package com.keko;

import com.keko.blocks.ModBlockEntity;
import com.keko.blocks.ModBlocks;
import com.keko.game.HealPayload;
import com.keko.game.KeyBinds;
import com.keko.helpers.InvSearch;
import com.keko.items.ModArmorMaterial;
import com.keko.items.ModItemGroup;
import com.keko.items.ModItems;
import com.keko.screen.ModScreenHandlers;
import com.keko.world.ModDimensions;
import com.keko.world.biome.ModBiomes;
import com.keko.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyraFinal implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cyra");
	public static final String MOD_ID = "cyra";

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModWorldGen.generateModWorldGen();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModArmorMaterial.load();
		ModBlockEntity.registerBlockEnt();
		ModScreenHandlers.registerScreenhandler();
		ModDimensions.register();
		ModBiomes.registerBiomes();




		LOGGER.info("Skibidi toilet ohio rizz pomni gayat");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}