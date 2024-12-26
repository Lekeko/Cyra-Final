package com.keko.entities.projectiles.seaBolt;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.pyriteCube.PCube;
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
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BoltRenderer extends ProjectileEntityRenderer<SeaBolt> {

    private final SeaBoltModel model;
    private final int stripLength = 50;
    private final Map<UUID, ArrayList<Vec3d>> entityPositions = new HashMap<>();
    int tickTotal = 0;


    public BoltRenderer(EntityRendererFactory.Context ctx,  Identifier identifier, EntityModelLayer Projectile) {
        super(ctx);
        this.model = new SeaBoltModel(ctx.getPart(ModModelLayer.SEA_BOLT));

    }

    @Override
    public Identifier getTexture(SeaBolt entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/sea_bolt.png");
    }

    @Override
    public void render(SeaBolt persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();


        tickTotal++;
        UUID entityUUID = persistentProjectileEntity.getUuid();
        updateArrayList(persistentProjectileEntity, entityUUID);


        matrixStack.scale(1f, 1f, 1f);
        matrixStack.translate(0.1, -1f, 0.2);

        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        Vec3d cameraPos = camera.getPos();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        RenderSystem.disableCull();

        float maxTrailWidth = 0.8f;
        float trailWidth = maxTrailWidth;

        ArrayList<Vec3d> positions = entityPositions.get(entityUUID);


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

                Color color = new Color(76, 236, 255, 255);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                buffer.vertex(matrixStack.peek().getPositionMatrix(), (float) v1.x, (float) (v1.y + 1.2), (float) v1.z).color(red, green, blue, 255);
                buffer.vertex(matrixStack.peek().getPositionMatrix(), (float) v2.x, (float) (v2.y + 1.2), (float) v2.z).color(red, green, blue, 255);
                buffer.vertex(matrixStack.peek().getPositionMatrix(), (float) v3.x, (float) (v3.y + 1.2), (float) v3.z).color(red, green, blue, 255);
                buffer.vertex(matrixStack.peek().getPositionMatrix(), (float) v4.x, (float) (v4.y + 1.2), (float) v4.z).color(red, green, blue, 255);
                if (trailWidth > 0.03) {
                    trailWidth -= 0.04f;
                }
            }

            RenderSystem.setShader(GameRenderer::getPositionColorProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            BufferRenderer.drawWithGlobalProgram(buffer.end());
            trailWidth = maxTrailWidth;
        }



        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(persistentProjectileEntity)), true, false);

        //this.model.render(matrixStack, vertexConsumer, 255,  OverlayTexture.DEFAULT_UV, 16777215);

        matrixStack.pop();
    }

    private void updateArrayList(SeaBolt persistentProjectileEntity, UUID entityUUID) {
        ArrayList<Vec3d> positions = entityPositions.computeIfAbsent(entityUUID, k -> new ArrayList<>());

        positions.addFirst(persistentProjectileEntity.getPos());

        if (positions.size() >= stripLength) {
            positions.removeLast();
        }
    }

}
