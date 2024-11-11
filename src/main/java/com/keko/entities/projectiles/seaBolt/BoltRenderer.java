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


        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
        matrixStack.push();

        matrixStack.scale(2, 21, 21);

        matrixStack.pop();
    }


}
