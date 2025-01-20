package com.keko.entities.projectiles.electroCharge;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.FOTOL.FistOfTheOldLord;
import com.keko.model.FOTOL.FOTOLModel;
import com.keko.model.ModModelLayer;
import com.keko.model.electroCharge.ElectroChargeModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ElectroChargeRenderer extends ProjectileEntityRenderer<ElectroCharge> {
    private final ElectroChargeModel model;

    public ElectroChargeRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new ElectroChargeModel(context.getPart(ModModelLayer.ELECTRO_CHARGE));

    }

    @Override
    public Identifier getTexture(ElectroCharge entity) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/electro_charge.png");
    }

    @Override
    public void render(ElectroCharge persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(persistentProjectileEntity)), true, false);
        matrixStack.push();
        matrixStack.translate(0,-1.2,0);


        this.model.render(matrixStack, vertexConsumer, 255,  OverlayTexture.DEFAULT_UV, 16777215);matrixStack.pop();
    }
}
