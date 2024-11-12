package com.keko;

import com.keko.blocks.ModBlocks;
import com.keko.entities.projectiles.seaBolt.BoltRenderer;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.game.BuffPayload;
import com.keko.game.HealPayload;
import com.keko.game.KeyBinds;
import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import com.keko.items.tools.BuffFlask;
import com.keko.model.ModModelLayer;
import com.keko.model.seaBolt.SeaBoltModel;
import com.keko.particle.ModParticles;
import com.keko.particle.custom.WaterBoltParticle;
import com.keko.screen.AlchemyTableScreen;
import com.keko.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class CyraModClient implements ClientModInitializer {

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
                assert itemStack != null;
                ((BuffFlask)(itemStack.getItem())).useEffect(context.player().getWorld(), context.player(), context.player().preferredHand);
                context.player().getItemCooldownManager().set(itemStack.getItem(), KeyBinds.cooldownBuff * 20);
            });
        });




        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEA_CRYSTAL_CLUSTER, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.ALCHEMY_TABLE_SCREENHANDLER_SCREEN_HANDLER_TYPE, AlchemyTableScreen::new);
        KeyBinds.initializeKeyBinds();
        EntityModelLayerRegistry.registerModelLayer(SEA_BOLT, SeaBoltModel::getTexturedModelData);
        EntityRendererRegistry.register(ModProjectileEntities.SEA_BOLT_ENTITY_TYPE, ctx -> new BoltRenderer(ctx, Identifier.of(CyraFinal.MOD_ID, "textures/entity/sea_bolt.png"), ModModelLayer.SEA_BOLT));
        ParticleFactoryRegistry.getInstance().register(ModParticles.WATER_BOLT_PARTICLE_TYPE, WaterBoltParticle.Factory::new);



    }
}
