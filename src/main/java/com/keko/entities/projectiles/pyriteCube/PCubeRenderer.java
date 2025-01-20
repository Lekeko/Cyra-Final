package com.keko.entities.projectiles.pyriteCube;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.seaBolt.SeaBolt;
import com.keko.model.ModModelLayer;
import com.keko.model.pCube.PCubeModel;
import com.keko.model.seaBolt.SeaBoltModel;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import java.awt.*;
import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PCubeRenderer extends ProjectileEntityRenderer<PCube> {

    private final PCubeModel model;
    private final int stripLength = 60;
    private final Map<UUID, ArrayList<Vec3d>> entityPositions = new HashMap<>();
    int tickTotal = 0;

    public PCubeRenderer(EntityRendererFactory.Context context, Identifier identifier, EntityModelLayer Projectile) {
        super(context);
        this.model = new PCubeModel(context.getPart(ModModelLayer.PCUBE));

    }

    @Override
    public Identifier getTexture(PCube entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/pyrite_primordial_cube.png");
    }

    @Override
    public void render(PCube persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (persistentProjectileEntity.color != null){
            int red = persistentProjectileEntity.color.getRed();
            int green = persistentProjectileEntity.color.getGreen();
            int blue = persistentProjectileEntity.color.getBlue();
            if (red != green && green != blue && blue != 255) {
                matrixStack.push();
                tickTotal++;
                UUID entityUUID = persistentProjectileEntity.getUuid();

                updateArrayList(persistentProjectileEntity, entityUUID);

                matrixStack.scale(1f, 1f, 1f);
                matrixStack.translate(0, -1, 0);


                Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
                Vec3d cameraPos = camera.getPos();

                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

                RenderSystem.disableCull();

                float maxTrailWidth = 1f;
                float trailWidth = maxTrailWidth;

                ArrayList<Vec3d> positions = entityPositions.get(entityUUID);

                if (persistentProjectileEntity.color.getRed() == 255 && persistentProjectileEntity.color.getGreen() == 102 && persistentProjectileEntity.color.getBlue() == 25){
                    float rotation = (MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(true) + persistentProjectileEntity.age) * 10 + persistentProjectileEntity.degree;

                    matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));
                    matrixStack.translate(0,1,4);
                    matrixStack.scale(1.2f,1.2f,1.2f);
                }else
                if (positions != null && positions.size() >= 15) {


                    for (int pose = 12; pose < positions.size() - 1; pose++) {

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

                        buffer.vertex(matrixStack.peek().getPositionMatrix(), (float) v1.x, (float) (v1.y + 1.2), (float) v1.z).color(red, green, blue, 255);
                        buffer.vertex(matrixStack.peek().getPositionMatrix(), (float) v2.x, (float) (v2.y + 1.2), (float) v2.z).color(red, green, blue, 255);
                        buffer.vertex(matrixStack.peek().getPositionMatrix(), (float) v3.x, (float) (v3.y + 1.2), (float) v3.z).color(red, green, blue, 255);
                        buffer.vertex(matrixStack.peek().getPositionMatrix(), (float) v4.x, (float) (v4.y + 1.2), (float) v4.z).color(red, green, blue, 255);
                        if (trailWidth > 0.03) {
                            trailWidth -= 0.02f;
                        }
                    }

                    RenderSystem.setShader(GameRenderer::getPositionColorProgram);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    BufferRenderer.drawWithGlobalProgram(buffer.end());
                    trailWidth = maxTrailWidth;
                }


                VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(persistentProjectileEntity)), true, false);
                this.model.render(matrixStack, vertexConsumer, 255, OverlayTexture.DEFAULT_UV, red * 256 * 256 + green * 256 + blue);

                matrixStack.pop();
            }
        }
    }

    @Override
    protected float getShadowRadius(PCube entity) {
        return 0;
    }





    private void updateArrayList(PCube persistentProjectileEntity, UUID entityUUID) {
        ArrayList<Vec3d> positions = entityPositions.computeIfAbsent(entityUUID, k -> new ArrayList<>());

        positions.addFirst(persistentProjectileEntity.getPos());

        if (positions.size() >= stripLength) {
            positions.removeLast();
        }
    }
}