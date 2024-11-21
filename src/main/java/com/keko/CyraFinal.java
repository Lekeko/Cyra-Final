package com.keko;

import com.keko.blocks.ModBlockEntity;
import com.keko.blocks.ModBlocks;
import com.keko.entities.ModEntities;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.items.ModArmorMaterial;
import com.keko.items.ModItemGroup;
import com.keko.items.ModItems;
import com.keko.particle.ModParticles;
import com.keko.screen.ModScreenHandlers;
import com.keko.world.ModDimensions;
import com.keko.world.biome.ModBiomes;
import com.keko.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
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
		ModProjectileEntities.registerModEntityProjectiles();
		ModParticles.register();



					LOGGER.info("Skibidi toilet ohio rizz pomni gayat");

		FabricDefaultAttributeRegistry.register(ModEntities.ZOMBIE_LEADER, ZombieLeaderEntity.setAtributes());
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}