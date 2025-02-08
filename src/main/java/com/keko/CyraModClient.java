package com.keko;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.blocks.ModBlocks;
import com.keko.blocks.ModBlocksRendererMap;
import com.keko.blocks.environment.dim1.kyanite.KyaniteCrystalBlockEntity;
import com.keko.blocks.environment.dim1.roseCrystal.RoseCrystalBlockEntity;
import com.keko.entities.EntityRendererModRegister;
import com.keko.events.AmbianceEvents;
import com.keko.game.KeyBinds;
import com.keko.items.ModItems;
import com.keko.items.tools.FlashLight;
import com.keko.model.ModModelChanger;
import com.keko.model.ModModelsRegister;
import com.keko.packet.networking.ModMessages;
import com.keko.particle.ParticleRegisterMod;
import com.keko.screen.ClientHandleScreenRegister;
import foundry.veil.Veil;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class CyraModClient implements ClientModInitializer {
    public static final Identifier SEA_STRIDER_RIPTIDE = Identifier.of(CyraFinal.MOD_ID, "textures/entity/cyra_sea_strider_riptide.png");

    public static boolean mayDraw = false;
    public static boolean mayDrawOffHand = false;
    public static int coinCharges = 0;
    public static int coinChargesOffhand = 0;
    public static boolean shouldFlash = false;
    public static float totalTickDelta = 0;
    private final Color flashColor = new Color(255, 225, 49, 239);
    private int musicTicks = 300;

    public static void initializeFlash() {
        shouldFlash = true;
    }


    @Override
    public void onInitializeClient() {




        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEA_CRYSTAL_CLUSTER, RenderLayer.getCutout());

        ClientHandleScreenRegister.register();
        KeyBinds.initializeKeyBinds();
        ModModelsRegister.register();
        ModBlocksRendererMap.register();
        ParticleRegisterMod.register();
        ModMessages.registerS2CPacket();
        EntityRendererModRegister.register();



        ClientBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register((blockEntity, world) -> {
            if (world.isClient){
                if (blockEntity instanceof RoseCrystalBlockEntity){
                    VeilRenderSystem.renderer().getLightRenderer().removeLight(((RoseCrystalBlockEntity) blockEntity).light);
                }
                if (blockEntity instanceof KyaniteCrystalBlockEntity){
                    VeilRenderSystem.renderer().getLightRenderer().removeLight(((KyaniteCrystalBlockEntity) blockEntity).light);
                }
            }
        });


        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (shouldFlash) {

                totalTickDelta += tickDelta.getTickDelta(true);

                float lerpedAmount = MathHelper.clamp(totalTickDelta / 20F, 0.0F, 1.0F);
                int lerpedColor = ColorHelper.Argb.getArgb((int) (100 * (1 - lerpedAmount)), flashColor.getRed(), flashColor.getGreen(), flashColor.getBlue());

                drawContext.fill(0, 0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), lerpedColor);

                if (lerpedAmount >= 1.0F) {
                    shouldFlash = false;
                    totalTickDelta = 0;
                }
            }
        });


        ModModelChanger.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (musicTicks > 0) {
                musicTicks--;
            }
            else {
                AmbianceEvents.playMusic(client);
                musicTicks = 8000;
            }
        });
    }
}
