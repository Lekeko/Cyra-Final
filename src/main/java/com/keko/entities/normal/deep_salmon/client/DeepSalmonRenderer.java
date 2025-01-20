package com.keko.entities.normal.deep_salmon.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.deep_salmon.DeepSalmonEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DeepSalmonRenderer extends GeoEntityRenderer<DeepSalmonEntity> {
    public DeepSalmonRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new DeepSalmonModel());
    }

    @Override
    public Identifier getTextureLocation(DeepSalmonEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/deep_salmon.png");
    }

}
