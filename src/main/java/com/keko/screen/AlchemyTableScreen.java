package com.keko.screen;

import com.keko.CyraFinal;
import com.keko.blocks.environment.AlchemyTableEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AlchemyTableScreen extends HandledScreen<AlchemyTableScreenhandler> {
    public static final Identifier TEXTURE = Identifier.of(CyraFinal.MOD_ID, "textures/gui/alchemy_table_gui.png");
    public AlchemyTableScreen(AlchemyTableScreenhandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }


    public ButtonWidget button1;

    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Combine"), button -> {
            if (button.active){
                if (AlchemyTableEntity.canCraft){
                    AlchemyTableEntity.wantsToCraft = true;
                }
            }
        }) .dimensions(width / 2 - 29, height / 2 - 48, 50, 15)
                .build();
        addDrawableChild(button1);

        super.init();
    }



    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        playerInventoryTitleX = 2222;
        titleY = backgroundHeight /2 -45;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

    }
}
