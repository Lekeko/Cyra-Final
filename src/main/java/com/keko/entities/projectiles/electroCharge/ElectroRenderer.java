package com.keko.entities.projectiles.electroCharge;

import com.keko.entities.projectiles.pyriteCube.PCube;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class ElectroRenderer extends EntityRenderer<Electro> {
    ArrayList<Vec3d> positions = new ArrayList<>();
    private final int stripLength = 100;


    public ElectroRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(Electro entity) {
        return null;
    }

    @Override
    public void render(Electro entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        updateArrayList(entity);

        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        Vec3d cameraPos = camera.getPos();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        RenderSystem.disableCull();

        float maxTrailWidth = 1f;
        float trailWidth = maxTrailWidth;

        if (positions != null && positions.size() >= 2) {
            for (int pose = 0; pose < positions.size() - 1; pose++) {

                Vec3d pos1 = positions.get(pose);
                Vec3d pos2 = positions.get(pose + 1);

                Vec3d dir = pos2.subtract(pos1).normalize();
                Vec3d side1 = dir.crossProduct(new Vec3d(0, 1, 0)).multiply(trailWidth / 2);
                Vec3d side2 = side1.multiply(-1);


                Vec3d v1 = pos1.add(side1);
                Vec3d v2 = pos1.add(side2);
                Vec3d v3 = pos2.add(side2);
                Vec3d v4 = pos2.add(side1);

                v1 = v1.subtract(cameraPos);
                v2 = v2.subtract(cameraPos);
                v3 = v3.subtract(cameraPos);
                v4 = v4.subtract(cameraPos);
                Color color1 = new Color(205, 254, 255, 255);
                Color color2 = new Color(173, 255, 252, 255);

                buffer.vertex(matrices.peek().getPositionMatrix(), (float) v1.x, (float) (v1.y + 1.2), (float) v1.z).color(color1.getRed(), color1.getGreen(), color1.getBlue(), 1);
                buffer.vertex(matrices.peek().getPositionMatrix(), (float) v2.x, (float) (v2.y + 1.2), (float) v2.z).color(color2.getRed(), color2.getGreen(), color2.getBlue(), 1);
                buffer.vertex(matrices.peek().getPositionMatrix(), (float) v3.x, (float) (v3.y + 1.2), (float) v3.z).color(color1.getRed(), color1.getGreen(), color1.getBlue(), 1);
                buffer.vertex(matrices.peek().getPositionMatrix(), (float) v4.x, (float) (v4.y + 1.2), (float) v4.z).color(color2.getRed(), color2.getGreen(), color2.getBlue(), 1);
                if (trailWidth > 0.03) {
                    trailWidth -= 0.04f;
                }
            }

            RenderSystem.setShader(GameRenderer::getPositionColorProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            BufferRenderer.drawWithGlobalProgram(buffer.end());
        }



        matrices.pop();
    }

    private void updateArrayList(Electro persistentProjectileEntity) {

        positions.addFirst(persistentProjectileEntity.getPos());

        if (positions.size() >= stripLength) {
            positions.removeLast();
        }
    }
}
