package com.keko.entities.projectiles.compulsionSword;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.FOTOL.FistOfTheOldLord;
import com.keko.model.FOTOL.FOTOLModel;
import com.keko.model.ModModelLayer;
import com.keko.model.compulsion.CompulsionSwordModel;
import net.minecraft.client.MinecraftClient;
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

public class CompulsionSwordRendering extends ProjectileEntityRenderer<CompulsionSword> {

    private final CompulsionSwordModel model;

    public CompulsionSwordRendering(EntityRendererFactory.Context context) {
        super(context);
        this.model = new CompulsionSwordModel(context.getPart(ModModelLayer.COMPULSION_SWORD));

    }

    @Override
    public Identifier getTexture(CompulsionSword entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/compulsion_sword.png");
    }

    @Override
    public void render(CompulsionSword persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(persistentProjectileEntity)), true, false);
        matrixStack.translate(0, -5, 0);
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0F));
        float rotation = (MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(true) + persistentProjectileEntity.age) * 10;

        matrixStack.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(rotation));

        this.model.render(matrixStack, vertexConsumer, 255,  OverlayTexture.DEFAULT_UV, 16777215);
    }
}
