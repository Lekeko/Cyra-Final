package com.keko.entities.projectiles.seaBolt;

import com.keko.CyraFinal;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class BoltRenderer extends ProjectileEntityRenderer<SeaBolt> {
    public BoltRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(SeaBolt entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/sea_bolt.png");
    }

    @Override
    public void render(SeaBolt persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevYaw, persistentProjectileEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevPitch, persistentProjectileEntity.getPitch())));
        int j = 0;
        float h = 0.0F;
        float k = 0.5F;
        float l = 0.0F;
        float m = 0.15625F;
        float n = 0.0F;
        float o = 0.15625F;
        float p = 0.15625F;
        float q = 0.3125F;
        float r = 0.05625F;
        float s = (float)persistentProjectileEntity.shake - g;
        if (s > 0.0F) {
            float t = -MathHelper.sin(s * 3.0F) * s;
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(t));
        }

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0F));
        matrixStack.scale(0.85625F, 0.05625F, 0.05625F);
        matrixStack.translate(-4.0F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture(persistentProjectileEntity)));
        MatrixStack.Entry entry = matrixStack.peek();
        this.vertex(entry, vertexConsumer, -16, -2, -2, 0.0F, 0.15625F, -1, 0, 0, i);
        this.vertex(entry, vertexConsumer, -16, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, i);
        this.vertex(entry, vertexConsumer, -16, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, i);
        this.vertex(entry, vertexConsumer, -16, 2, -2, 0.0F, 0.3125F, -1, 0, 0, i);
        this.vertex(entry, vertexConsumer, -16, 2, -2, 0.0F, 0.15625F, 1, 0, 0, i);
        this.vertex(entry, vertexConsumer, -16, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, i);
        this.vertex(entry, vertexConsumer, -16, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, i);
        this.vertex(entry, vertexConsumer, -16, -2, -2, 0.0F, 0.3125F, 1, 0, 0, i);

        for(int u = 0; u < 4; ++u) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
            this.vertex(entry, vertexConsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, i);
            this.vertex(entry, vertexConsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, i);
            this.vertex(entry, vertexConsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, i);
            this.vertex(entry, vertexConsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, i);
        }

        matrixStack.pop();
    }


}
