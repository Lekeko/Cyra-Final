package com.keko.entities.normal.stridely.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.stridely.StridelyEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StridelyRenderer extends GeoEntityRenderer<StridelyEntity> {
    public StridelyRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new StridelyModel());
    }

    @Override
    public Identifier getTextureLocation(StridelyEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/stridely.png");
    }
}
