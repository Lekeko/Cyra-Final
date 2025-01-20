package com.keko.entities.projectiles.compulsionAxe;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.compulsionSword.CompulsionSword;
import com.keko.model.ModModelLayer;
import com.keko.model.compulsion.CompulsionAxeModel;
import com.keko.model.compulsion.CompulsionSwordModel;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class CompulsionAxeRendering extends ProjectileEntityRenderer<CompulsionAxe> {

    private final CompulsionAxeModel model;

    public CompulsionAxeRendering(EntityRendererFactory.Context context) {
        super(context);
        this.model = new CompulsionAxeModel(context.getPart(ModModelLayer.COMPULSION_AXE));

    }

    @Override
    public Identifier getTexture(CompulsionAxe entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/compulsion_axe.png");
    }

    @Override
    public void render(CompulsionAxe persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        float rotation = (MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(true) + persistentProjectileEntity.age) * 30;

        matrixStack.push();

        matrixStack.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(180));
        matrixStack.translate(0,-2,0);
        float scaleFactor = persistentProjectileEntity.age / 10f + 2;
        matrixStack.scale(scaleFactor, 1, scaleFactor);
        matrixStack.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(-rotation));
        RenderSystem.disableCull();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(getTexture(persistentProjectileEntity)));
        this.model.render(matrixStack, vertexConsumer, 255,  OverlayTexture.DEFAULT_UV, 16777215);
        matrixStack.pop();
    }

    @Override
    public boolean shouldRender(CompulsionAxe entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
