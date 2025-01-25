package com.keko.entities.projectiles.compulsionScythe;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.compulsionAxe.CompulsionAxe;
import com.keko.model.ModModelLayer;
import com.keko.model.compulsion.CompulsionScytheModel;
import com.keko.model.compulsion.CompulsionSwordModel;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class CompulsionScytheRendering extends ProjectileEntityRenderer<CompulsionScythe> {

    private final CompulsionScytheModel model;

    public CompulsionScytheRendering(EntityRendererFactory.Context context) {
        super(context);
        this.model = new CompulsionScytheModel(context.getPart(ModModelLayer.COMPULSION_SCYTHE));

    }

    @Override
    public Identifier getTexture(CompulsionScythe entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/compulsion_scythe.png");
    }

    @Override
    public void render(CompulsionScythe persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        float rotation = (MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(true) + persistentProjectileEntity.age) * 60;
        if (persistentProjectileEntity.getOwner() != null)
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.getOwner().prevYaw, persistentProjectileEntity.getOwner().getYaw())));


        matrixStack.scale(6,1,6);
        RenderSystem.disableCull();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(getTexture(persistentProjectileEntity)));
        this.model.render(matrixStack, vertexConsumer, 255,  OverlayTexture.DEFAULT_UV, 16777215);
        matrixStack.pop();
    }


    @Override
    public boolean shouldRender(CompulsionScythe entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
