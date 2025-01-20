package com.keko.entities.normal.jellyfish.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.jellyfish.JellyFishEntity;
import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import com.keko.entities.normal.sea_rodent.client.SeaRodentModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class JellyFishEntityRenderer extends GeoEntityRenderer<JellyFishEntity> {
    public JellyFishEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new JellyFishEntityModel());
    }

    @Override
    public Identifier getTextureLocation(JellyFishEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/jellyfish.png");
    }

    @Override
    public void render(JellyFishEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        poseStack.push();
        poseStack.scale(2,2,2);
        poseStack.pop();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
