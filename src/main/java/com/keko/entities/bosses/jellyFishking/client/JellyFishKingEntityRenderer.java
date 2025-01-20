package com.keko.entities.bosses.jellyFishking.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.jellyFishking.JellyFishKingEntity;
import com.keko.entities.normal.jellyfish.JellyFishEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class JellyFishKingEntityRenderer extends GeoEntityRenderer<JellyFishKingEntity> {
    public JellyFishKingEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new JellyFishKingEntityModel());
    }

    @Override
    public Identifier getTextureLocation(JellyFishKingEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/jellyfish_king.png");
    }

    @Override
    public void render(JellyFishKingEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        poseStack.push();
        poseStack.scale(5, 5,5);
        poseStack.translate(0,-1,0);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);poseStack.pop();
    }
}
