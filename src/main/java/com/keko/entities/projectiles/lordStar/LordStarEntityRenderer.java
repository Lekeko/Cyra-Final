package com.keko.entities.projectiles.lordStar;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.pyriteCube.PCube;
import com.keko.model.ModModelLayer;
import com.keko.model.lordStar.LordStarModel;
import com.keko.model.pCube.PCubeModel;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LordStarEntityRenderer extends ProjectileEntityRenderer<LordStarEntity> {

        private final LordStarModel model;
        private final int stripLength = 50;
        private final Map<UUID, ArrayList<Vec3d>> entityPositions = new HashMap<>();
        int tickTotal = 0;

        public LordStarEntityRenderer(EntityRendererFactory.Context context, Identifier identifier, EntityModelLayer Projectile) {
            super(context);
            this.model = new LordStarModel(context.getPart(ModModelLayer.LORD_STAR));

        }

        @Override
        public Identifier getTexture(LordStarEntity entity) {
            return Identifier.of(CyraFinal.MOD_ID, "textures/entity/lord_star.png");
        }

        @Override
        public void render(LordStarEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
            int red = 255;
            int green = 255;
            int blue = 254;
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

            float maxTrailWidth = 0.4f;
            float trailWidth = maxTrailWidth;

            ArrayList<Vec3d> positions = entityPositions.get(entityUUID);

            if (positions != null && positions.size() >= 3) {


                for (int pose = 1; pose < positions.size() - 1; pose++) {

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
            //this.model.render(matrixStack, vertexConsumer, 255, OverlayTexture.DEFAULT_UV, red * 256 * 256 + green * 256 + blue);

            matrixStack.pop();
        }



        private void updateArrayList(LordStarEntity persistentProjectileEntity, UUID entityUUID) {
            ArrayList<Vec3d> positions = entityPositions.computeIfAbsent(entityUUID, k -> new ArrayList<>());

            positions.addFirst(persistentProjectileEntity.getPos());

            if (positions.size() >= stripLength) {
                positions.removeLast();
            }
        }
    }