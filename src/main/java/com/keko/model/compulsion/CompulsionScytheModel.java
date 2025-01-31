package com.keko.model.compulsion;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class CompulsionScytheModel extends Model {
    private final ModelPart bone;
    public CompulsionScytheModel(ModelPart layerFactory) {
        super(RenderLayer::getEntitySolid);
        this.bone = layerFactory;
    }

    public static TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(10, 14).cuboid(-7.0F, -1.0F, -8.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                    .uv(16, 0).cuboid(-5.0F, -1.0F, -7.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                    .uv(0, 17).cuboid(-4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                    .uv(6, 18).cuboid(-2.0F, -1.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                    .uv(12, 18).cuboid(-3.0F, -1.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                    .uv(18, 14).cuboid(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                    .uv(22, 8).cuboid(0.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

            ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(14, 5).cuboid(-1.0F, -1.0F, -2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 0.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 14).cuboid(-1.0F, -1.0F, -2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, 4.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(10, 11).cuboid(-1.0F, -1.0F, -2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, 5.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 5).cuboid(-1.0F, -1.0F, -5.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 5.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r5 = bb_main.addChild("cube_r5", ModelPartBuilder.create().uv(16, 22).cuboid(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 5.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r6 = bb_main.addChild("cube_r6", ModelPartBuilder.create().uv(18, 18).cuboid(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 4.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r7 = bb_main.addChild("cube_r7", ModelPartBuilder.create().uv(14, 8).cuboid(-3.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 0.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r8 = bb_main.addChild("cube_r8", ModelPartBuilder.create().uv(0, 11).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r9 = bb_main.addChild("cube_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -1.0F, -1.0F, 5.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r10 = bb_main.addChild("cube_r10", ModelPartBuilder.create().uv(12, 22).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r11 = bb_main.addChild("cube_r11", ModelPartBuilder.create().uv(8, 22).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r12 = bb_main.addChild("cube_r12", ModelPartBuilder.create().uv(4, 22).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, -3.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r13 = bb_main.addChild("cube_r13", ModelPartBuilder.create().uv(22, 0).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r14 = bb_main.addChild("cube_r14", ModelPartBuilder.create().uv(0, 21).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, -4.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData cube_r15 = bb_main.addChild("cube_r15", ModelPartBuilder.create().uv(20, 11).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 0.0F, -5.0F, 0.0F, 1.5708F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
    }



    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        bone.render(matrices, vertices, light, overlay, color);

    }
}
