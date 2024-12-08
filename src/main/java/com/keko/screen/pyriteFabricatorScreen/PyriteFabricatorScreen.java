package com.keko.screen.pyriteFabricatorScreen;

import com.keko.CyraFinal;
import com.keko.screen.alchemyTableScreen.AlchemyTableScreenhandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PyriteFabricatorScreen extends HandledScreen<PyriteFabricatorScreenHandler> {
    public static final Identifier TEXTURE = Identifier.of(CyraFinal.MOD_ID, "textures/gui/pyrite_fabricator_gui.png");

    public PyriteFabricatorScreen(PyriteFabricatorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderColor(1, 1, 1,1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        titleY = 22222;
        playerInventoryTitleX = 22222;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

    }
}

