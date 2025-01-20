package com.keko.entities.projectiles.FOTOL;

import com.keko.CyraFinal;
import com.keko.model.FOTOL.FOTOLModel;
import com.keko.model.ModModelLayer;
import com.keko.model.pCube.PCubeModel;
import com.keko.model.seaBolt.SeaBoltModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class FistOfTheOldLordRendering extends ProjectileEntityRenderer<FistOfTheOldLord> {

    private final FOTOLModel model;

    public FistOfTheOldLordRendering(EntityRendererFactory.Context context) {
        super(context);
        this.model = new FOTOLModel(context.getPart(ModModelLayer.FOTOL));

    }

    @Override
    public Identifier getTexture(FistOfTheOldLord entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/fist_of_the_old_lord.png");
    }

    @Override
    public void render(FistOfTheOldLord persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(persistentProjectileEntity)), true, false);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevYaw, persistentProjectileEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevPitch, persistentProjectileEntity.getPitch()) + 90.0F));

        this.model.render(matrixStack, vertexConsumer, 255,  OverlayTexture.DEFAULT_UV, 16777215);
    }
}
