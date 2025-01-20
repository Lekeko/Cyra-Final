package com.keko.model.compulsion;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class CompulsionSwordModel extends Model {
    private final ModelPart bb_main;
    public CompulsionSwordModel(ModelPart layerFactory) {
        super(RenderLayer::getEntitySolid);
        this.bb_main = layerFactory;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 92).cuboid(-5.0F, -77.0F, -4.5F, 10.0F, 73.0F, 5.0F, new Dilation(0.0F))
                .uv(106, 114).cuboid(-3.0F, -103.0F, -4.5F, 6.0F, 22.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(22, 0).cuboid(-3.0F, -15.0F, -1.0F, 4.0F, 15.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(33.0F, -66.0F, -1.5F, -3.1416F, 0.0F, 2.3562F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(12, 20).cuboid(-3.0F, -18.0F, -1.0F, 4.0F, 18.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(37.0F, -66.25F, -1.5F, -3.1416F, 0.0F, 0.7854F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(12, 20).cuboid(-3.0F, -18.0F, -1.0F, 4.0F, 18.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-36.0F, -66.25F, -1.5F, 0.0F, 0.0F, 2.3562F));

        ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(22, 0).cuboid(-3.0F, -15.0F, -1.0F, 4.0F, 15.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-32.0F, -66.0F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r5 = bb_main.addChild("cube_r5", ModelPartBuilder.create().uv(128, 106).cuboid(1.0F, -5.25F, -5.0F, 10.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-11.075F, -57.925F, 0.0F, 0.0F, 0.0F, -2.3562F));

        ModelPartData cube_r6 = bb_main.addChild("cube_r6", ModelPartBuilder.create().uv(128, 92).cuboid(1.0F, -8.25F, -5.0F, 10.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(14.925F, -54.15F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r7 = bb_main.addChild("cube_r7", ModelPartBuilder.create().uv(68, 114).cuboid(-3.0F, -2.25F, -5.0F, 13.0F, 13.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.55F, -112.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r8 = bb_main.addChild("cube_r8", ModelPartBuilder.create().uv(74, 103).cuboid(-10.0F, -5.25F, -5.0F, 21.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-18.5F, -73.5F, 0.0F, 0.0F, 0.0F, 2.3562F));

        ModelPartData cube_r9 = bb_main.addChild("cube_r9", ModelPartBuilder.create().uv(74, 92).cuboid(-10.0F, -5.25F, -5.0F, 21.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(14.675F, -69.65F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r10 = bb_main.addChild("cube_r10", ModelPartBuilder.create().uv(30, 92).cuboid(-6.0F, -5.25F, -5.0F, 16.0F, 16.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.55F, -83.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r11 = bb_main.addChild("cube_r11", ModelPartBuilder.create().uv(30, 114).cuboid(-9.0F, -8.25F, -5.0F, 13.0F, 13.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.55F, -0.75F, 0.0F, 0.0F, 0.0F, 0.7854F));
        return TexturedModelData.of(modelData, 256, 256);
    }



    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        bb_main.render(matrices, vertices, light, overlay, color);

    }
}
