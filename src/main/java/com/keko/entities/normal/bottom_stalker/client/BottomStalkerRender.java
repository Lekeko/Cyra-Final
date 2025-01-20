package com.keko.entities.normal.bottom_stalker.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.bottom_stalker.BottomStalkerEntity;
import com.keko.entities.normal.deep_salmon.DeepSalmonEntity;
import com.keko.entities.normal.deep_salmon.client.DeepSalmonModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BottomStalkerRender extends GeoEntityRenderer<BottomStalkerEntity> {
    public BottomStalkerRender(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BottomStalkerModel());
    }

    @Override
    public void render(BottomStalkerEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        poseStack.push();
        poseStack.scale(10,10,10);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pop();
    }

    @Override
    public Identifier getTextureLocation(BottomStalkerEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/bottom_stalker.png");
    }

}
