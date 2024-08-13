package com.keko.game;

import com.keko.CyraFinal;
import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
@Environment(EnvType.CLIENT)
public class HealCooldown implements HudRenderCallback {
    private static final Identifier HEAL_FLASK_ICON = Identifier.of(CyraFinal.MOD_ID, "textures/item/healing_flask.png");
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width/2;
            y = height;

            RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
            RenderSystem.setShaderColor(1,1,1,1);
            RenderSystem.setShaderTexture(0, HEAL_FLASK_ICON);


        }
    }
}
