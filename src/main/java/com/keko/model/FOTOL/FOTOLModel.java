package com.keko.model.FOTOL;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

@Environment(EnvType.CLIENT)
public class FOTOLModel extends Model {
    private final ModelPart fotol_projectile;

    public FOTOLModel(ModelPart layerFactory) {
        super(RenderLayer::getEntitySolid);
        this.fotol_projectile = layerFactory;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData fotol_projectile = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 35).cuboid(-5.0F, -3.0F, -5.0F, 10.0F, 3.0F, 10.0F, new Dilation(0.0F))
                .uv(40, 0).cuboid(-4.0F, -16.0F, -4.0F, 8.0F, 13.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-5.0F, -23.0F, -5.0F, 10.0F, 7.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = fotol_projectile.addChild("cube_r1", ModelPartBuilder.create().uv(40, 35).cuboid(-1.0F, -6.0F, 0.0F, 4.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -20.0F, -6.0F, 0.0F, 0.0F, 0.2182F));

        ModelPartData cube_r2 = fotol_projectile.addChild("cube_r2", ModelPartBuilder.create().uv(38, 21).cuboid(-4.713F, -3.6929F, -3.0F, 8.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(4.525F, -22.4F, 0.0F, 0.0F, 0.0F, -1.8762F));

        ModelPartData cube_r3 = fotol_projectile.addChild("cube_r3", ModelPartBuilder.create().uv(0, 19).cuboid(-4.713F, -3.6929F, -4.0F, 8.0F, 5.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(0.775F, -21.4F, 0.0F, 0.0F, 0.0F, -0.3927F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        fotol_projectile.render(matrices, vertices, light, overlay, color);
    }

}
