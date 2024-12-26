package com.keko.entities.testVeilRenderEntity.cubeEntity;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class CubeEntityRenderer extends EntityRenderer<CubeEntity> {
    public CubeEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(CubeEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        Tessellator tessellator = Tessellator.getInstance();

        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);

        Color color = new Color(255, 0, 0, 255);

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        RenderSystem.disableCull();


        buffer.vertex(matrices.peek().getPositionMatrix(), 0, 0, 0 ).color(red, green, blue, 255);
        buffer.vertex(matrices.peek().getPositionMatrix(), 0, 2 ,0).color(blue, red, green, 255);
        buffer.vertex(matrices.peek().getPositionMatrix(), 2, 0, 0).color(green, blue, red, 255);
        buffer.vertex(matrices.peek().getPositionMatrix(), 2, 2 ,0).color(red, green, blue, 255);

        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        BufferRenderer.drawWithGlobalProgram(buffer.end());





        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.pop();
    }

    @Override
    public Identifier getTexture(CubeEntity entity) {
        return null;
    }
}
