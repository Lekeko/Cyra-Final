package com.keko.mixin;

import com.keko.items.ModItems;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.TridentRiptideFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.keko.CyraModClient.SEA_STRIDER_RIPTIDE;

@Mixin(TridentRiptideFeatureRenderer.class)

public class CyraRiptideMixin {

    @ModifyVariable(method = "render", at = @At("STORE"))
    private VertexConsumer CyraRiptideBecauseItLooksBetter(VertexConsumer orig, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity && livingEntity.isUsingRiptide() && (livingEntity.getMainHandStack().getItem() == ModItems.SEA_CRYSTAL_STRIDERS )) {
            return vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(SEA_STRIDER_RIPTIDE));
        }
        return orig;
    }
}
