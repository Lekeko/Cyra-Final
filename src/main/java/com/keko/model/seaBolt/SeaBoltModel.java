package com.keko.model.seaBolt;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class SeaBoltModel extends Model {
    private final ModelPart sea_bolt_projectile;

    public SeaBoltModel(ModelPart layerFactory) {
        super(RenderLayer::getEntitySolid);
        this.sea_bolt_projectile = layerFactory;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 24).cuboid(-9.0F, -5.0F, 1.0F, 0.0F, 2.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(-10.0F, -4.0F, 1.0F, 2.0F, 0.0F, 12.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(-11.0F, -4.0F, 2.0F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 0).cuboid(-9.0F, -6.0F, 2.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 24.0F, -8.0F));

        ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(0, 1).cuboid(-1.5F, -1.35F, 1.0F, 0.9F, 0.9F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-8.9F, -2.6F, 0.0F, 0.0F, 0.0F, 0.7854F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.sea_bolt_projectile.render(matrices, vertices, light, overlay);
    }


}
