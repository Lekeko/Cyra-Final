package com.keko.entities.projectiles.seaBolt;

import com.keko.CyraFinal;
import com.keko.model.ModModelLayer;
import com.keko.model.seaBolt.SeaBoltModel;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class BoltRenderer extends ProjectileEntityRenderer<SeaBolt> {

    private final SeaBoltModel model;

    public BoltRenderer(EntityRendererFactory.Context ctx,  Identifier identifier, EntityModelLayer cyrusProjectile) {
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
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevPitch, persistentProjectileEntity.getPitch()) + 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevPitch, persistentProjectileEntity.getPitch()) + 90.0F));
        matrixStack.scale(1f, 1f, 1f);
        matrixStack.translate(0.0, -1.5, 0.0);
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(persistentProjectileEntity)), true, false);
        matrixStack.pop();
        this.model.render(matrixStack, vertexConsumer, 255,  OverlayTexture.DEFAULT_UV, 16777215);
    }

}
