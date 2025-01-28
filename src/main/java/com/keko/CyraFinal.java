package com.keko;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.blocks.ModBlockEntity;
import com.keko.blocks.ModBlockTags;
import com.keko.blocks.ModBlocks;
import com.keko.effects.ModStatusEffects;
import com.keko.entities.ModEntities;
import com.keko.entities.bosses.jellyFishking.JellyFishKingEntity;
import com.keko.entities.bosses.oldLord.OldLordEntity;
import com.keko.entities.bosses.skeletonLeader.SkeletonLeaderEntity;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import com.keko.entities.normal.bottom_stalker.BottomStalkerEntity;
import com.keko.entities.normal.deep_salmon.DeepSalmonEntity;
import com.keko.entities.normal.groxion.GroxionEntity;
import com.keko.entities.normal.jellyfish.JellyFishEntity;
import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import com.keko.entities.normal.stridely.StridelyEntity;
import com.keko.entities.normal.void_slug.VoidSlugEntity;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.events.AmbianceEvents;
import com.keko.events.BottomStalkerSpawn;
import com.keko.events.CompulsionEvents;
import com.keko.features.ModFeature;
import com.keko.items.ModArmorMaterial;
import com.keko.items.ModItemGroup;
import com.keko.items.ModItems;
import com.keko.packet.networking.ModMessages;
import com.keko.parrySystem.ParryingHandler;
import com.keko.particle.ModParticles;
import com.keko.screen.ModScreenHandlers;
import com.keko.sounds.ModSounds;
import com.keko.util.ModLootTableModif;
import com.keko.world.ModDimensions;
import com.keko.world.biome.ModBiomes;
import com.keko.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyraFinal implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cyra");
	public static final String MOD_ID = "cyra";

	int ticker = 0;
	int spawnTicker = 0;
	int ambianceTick = 0;


	@Override
	public void onInitialize() {
		ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of("cyra_particles"),
				FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(), ResourcePackActivationType.ALWAYS_ENABLED);

		ModItemGroup.registerItemGroups();
		ModStatusEffects.registerStatusEffects();
		ModWorldGen.generateModWorldGen();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModArmorMaterial.load();
		ModFeature.registerFeatures();
		ModBlockEntity.registerBlockEnt();
		ModScreenHandlers.registerScreenhandler();
		ModDimensions.register();
		ModBiomes.registerBiomes();
		ModProjectileEntities.registerModEntityProjectiles();
		ModParticles.register();
		ModFeature.registerFeatures();
		ModSounds.registerSounds();
		ModBlockTags.register();

		ModMessages.registerC2SPacket();

		ModDataComponentTypes.registerDataComponents();
		ModLootTableModif.modifu();

		PlayerBlockBreakEvents.AFTER.register(
				(world, player, pos, state, entity) -> {
					if (world.getBiome(pos).matchesKey(ModBiomes.VOID_WATERS)){
						if (ticker > 0)
							if (!world.isClient) {
								VoidSlugEntity slugEntity = new VoidSlugEntity(ModEntities.VOID_SLUG_ENTITY_ENTITY_TYPE, world);
								slugEntity.setPos(player.getX(), player.getY() + 1, player.getZ());
								world.spawnEntity(slugEntity);
							}

						if (world.random.nextBetween(1, 500) < 5 && state.isOf(ModBlocks.VOID_STONE)) {
							world.playSound(null, pos, ModSounds.EERY, SoundCategory.AMBIENT, 2, 0.4f);
							ticker = 80;
						}
					}
				}
		);

		UseItemCallback.EVENT.register((player, world, hand) ->{
			if (!world.isClient && ParryingHandler.hasPyriteWeaponInHand(player, hand)){
				OldLordEntity entity = null;
				for (OldLordEntity oldLord : world.getEntitiesByClass(OldLordEntity.class, new Box(player.getX() + 20, player.getY() + 20, player.getZ() + 20, player.getX() - 20, player.getY() - 20, player.getZ() - 20), OldLordEntity::isAlive))
					entity = oldLord;
				if (entity != null && !player.getItemCooldownManager().isCoolingDown(player.getStackInHand(hand).getItem())) {
					entity.attackCanceled = 5;
					player.getItemCooldownManager().set(player.getStackInHand(hand).getItem(), 5);
				}
			}

			if (!world.isClient){
				CompulsionEvents.checkCompulsion(player, world, hand);
			}

			return TypedActionResult.pass(ItemStack.EMPTY);
		});


		ServerTickEvents.END_SERVER_TICK.register(server -> {
			if (ticker > 0){
				ticker--;
			}
			spawnTicker ++;
			if (spawnTicker > 10 * 20) {
				spawnTicker = 0;
				BottomStalkerSpawn.tryToSpawnAStalker(server);
			}
			if (ambianceTick > 0)
				ambianceTick--;
			else {
				AmbianceEvents.scarePlayersInVoid(server);
				ambianceTick = 200;
			}



		});


		LOGGER.info("Initializing Cyra..");

		FabricDefaultAttributeRegistry.register(ModEntities.ZOMBIE_LEADER, ZombieLeaderEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SKELETON_LEADER, SkeletonLeaderEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.JELLYFISH, JellyFishEntity.setAtributes());

		FabricDefaultAttributeRegistry.register(ModEntities.DEEP_SALMON, DeepSalmonEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.GROXION, GroxionEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SEA_RODENT, SeaRodentEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.STRIDELY, StridelyEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BOTTOM_STALKER, BottomStalkerEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.VOID_SLUG_ENTITY_ENTITY_TYPE, VoidSlugEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.JELLYFISH_KING, JellyFishKingEntity.setAtributes());
		FabricDefaultAttributeRegistry.register(ModEntities.OLD_LORD_ENTITY_ENTITY_TYPE, OldLordEntity.setAtributes());



	}


	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}