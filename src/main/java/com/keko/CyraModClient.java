package com.keko;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.blocks.ModBlocks;
import com.keko.entities.ModEntities;
import com.keko.entities.bosses.skeletonLeader.client.SkeletonLeaderRenderer;
import com.keko.entities.bosses.zombieLeader.client.ZombieLeaderRenderer;
import com.keko.entities.projectiles.FOTOL.FistOfTheOldLordRendering;
import com.keko.entities.projectiles.pyriteCube.PCubeRenderer;
import com.keko.entities.projectiles.seaBolt.BoltRenderer;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.entities.testVeilRenderEntity.ModTestEntity;
import com.keko.entities.testVeilRenderEntity.cubeEntity.CubeEntityRenderer;
import com.keko.game.BuffPayload;
import com.keko.game.HealPayload;
import com.keko.game.KeyBinds;
import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import com.keko.items.tools.BuffFlask;
import com.keko.model.FOTOL.FOTOLModel;
import com.keko.model.ModModelLayer;
import com.keko.model.pCube.PCubeModel;
import com.keko.model.seaBolt.SeaBoltModel;
import com.keko.particle.ModParticles;
import com.keko.particle.custom.PyriteSlashParticle;
import com.keko.particle.custom.PyriteStarParticle;
import com.keko.particle.custom.WaterBoltParticle;
import com.keko.screen.alchemyTableScreen.AlchemyTableScreen;
import com.keko.screen.ModScreenHandlers;
import com.keko.screen.pyriteFabricatorScreen.PyriteFabricatorScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

import static com.keko.model.ModModelLayer.FOTOL;

public class CyraModClient implements ClientModInitializer {
    public static final Identifier CYRA_TRIDENT_RIPTIDE = Identifier.of(CyraFinal.MOD_ID, "textures/entity/cyra_sea_strider_riptide.png");


    public static final EntityModelLayer SEA_BOLT = new EntityModelLayer(Identifier.of(CyraFinal.MOD_ID, "entity/sea_bolt"), "main");

    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playC2S().register(HealPayload.ID, HealPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(HealPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                context.player().heal(2 * KeyBinds.healing_amount);
                ItemStack itemStack = InvSearch.getItemInInv(context.player(), ModItems.HEALING_FLASK);
                assert itemStack != null;
                context.player().getItemCooldownManager().set(itemStack.getItem(), KeyBinds.cooldown * 20);
            });
        });

        PayloadTypeRegistry.playC2S().register(BuffPayload.ID, BuffPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(BuffPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                ItemStack itemStack = InvSearch.getItemInInv(context.player(), ModItems.BUFF_FLASK);
                PlayerEntity user = context.player();
                assert itemStack != null;
                HashMap<Integer, StatusEffectInstance> effect = BuffFlask.effect;
                if (!context.player().getItemCooldownManager().isCoolingDown(itemStack.getItem())){
                    itemStack.use(user.getWorld(), user, context.player().preferredHand);-
                }
            });
        });



        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEA_CRYSTAL_CLUSTER, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.ALCHEMY_TABLE_SCREENHANDLER_SCREEN_HANDLER_TYPE, AlchemyTableScreen::new);
        HandledScreens.register(ModScreenHandlers.PYRITE_FABRICATOR_SCREENHANDLER_SCREEN_HANDLER_TYPE, PyriteFabricatorScreen::new);
        KeyBinds.initializeKeyBinds();
        EntityModelLayerRegistry.registerModelLayer(SEA_BOLT, SeaBoltModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FOTOL, FOTOLModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayer.PCUBE, PCubeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModProjectileEntities.SEA_BOLT_ENTITY_TYPE, ctx -> new BoltRenderer(ctx, Identifier.of(CyraFinal.MOD_ID, "textures/entity/sea_bolt.png"), ModModelLayer.SEA_BOLT));
        EntityRendererRegistry.register(ModTestEntity.CUBE_ENTITY_TYPE, CubeEntityRenderer::new);
        EntityRendererRegistry.register(ModProjectileEntities.FIST_OF_THE_OLD_LORD, FistOfTheOldLordRendering::new);
        EntityRendererRegistry.register(ModProjectileEntities.P_CUBE_ENTITY_TYPE, ctx -> new PCubeRenderer(ctx, Identifier.of(CyraFinal.MOD_ID, "textures/entity/pyrite_primordial_cube.png"), ModModelLayer.PCUBE));
        ParticleFactoryRegistry.getInstance().register(ModParticles.WATER_BOLT_PARTICLE_TYPE, WaterBoltParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.PYRITE_SLASH, PyriteSlashParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.PYRITE_STAR, PyriteStarParticle.Factory::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SUPPORTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRYSTAL_SEA_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TALL_CRYSTAL_SEA_GRASS_BOTTOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TALL_CRYSTAL_SEA_GRASS_TOP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRYSTAL_SEA_WEED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEA_CRYSTAL_BRICKS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEA_CRYSTAL_BRICKS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PYRITE_LAMP, RenderLayer.getTranslucent());
        EntityRendererRegistry.register(ModEntities.ZOMBIE_LEADER, ZombieLeaderRenderer::new);
        EntityRendererRegistry.register(ModEntities.SKELETON_LEADER, SkeletonLeaderRenderer::new);



        Identifier variantProperty = Identifier.of(CyraFinal.MOD_ID, "variant");
        ModelPredicateProviderRegistry.register(ModItems.PYRITE_PRIMORDIAL_CUBE, variantProperty, (itemStack, clientWorld, livingEntity, seed) -> {
            if (itemStack.isEmpty()) {
                return 0f;
            }
            if (itemStack.get(ModDataComponentTypes.VARIANT) == null)
                return 0f;
            else {
                return (float) (itemStack.get(ModDataComponentTypes.VARIANT) - 1) / 10;
            }
        });
    }
}
