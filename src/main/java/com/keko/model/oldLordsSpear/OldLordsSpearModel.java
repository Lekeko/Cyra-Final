package com.keko.model.oldLordsSpear;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class OldLordsSpearModel  extends Model {
    private final ModelPart spear_projectile;

    public OldLordsSpearModel(ModelPart layerFactory) {
        super(RenderLayer::getEntitySolid);
        this.spear_projectile = layerFactory;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(-19, -19).cuboid(0.0F, -4.0F, -18.0F, 0.0F, 7.0F, 39.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 27).cuboid(0.0F, -7.0F, -8.0F, 0.0F, 7.0F, 39.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, -0.5F, -10.0F, 0.0F, 0.0F, 1.5708F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.spear_projectile.render(matrices, vertices, light, overlay, color);
    }
}