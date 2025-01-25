package com.keko.entities.projectiles.oldLordsSpear;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.FOTOL.FistOfTheOldLord;
import com.keko.model.FOTOL.FOTOLModel;
import com.keko.model.ModModelLayer;
import com.keko.model.oldLordsSpear.OldLordsSpearModel;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class OldLordsSpearEntityRenderer extends ProjectileEntityRenderer<OldLordsSpearEntity> {

    private final OldLordsSpearModel model;

    public OldLordsSpearEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new OldLordsSpearModel(context.getPart(ModModelLayer.OLD_LORDS_SPEAR));

    }

    @Override
    public Identifier getTexture(OldLordsSpearEntity entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/old_lords_spear.png");
    }


    @Override
    public void render(OldLordsSpearEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
    matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevYaw, persistentProjectileEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevPitch, persistentProjectileEntity.getPitch()) + 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));
        matrixStack.translate(0,-2,0);
        RenderSystem.disableCull();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(getTexture(persistentProjectileEntity)));
        this.model.render(matrixStack, vertexConsumer, 255,  OverlayTexture.DEFAULT_UV, 16777215);

        matrixStack.pop();}

    @Override
    public boolean shouldRender(OldLordsSpearEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
