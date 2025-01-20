package com.keko.entities.normal.groxion.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.groxion.GroxionEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GroxionRenderer extends GeoEntityRenderer<GroxionEntity> {
    public GroxionRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GroxionModel());
    }

    @Override
    public Identifier getTextureLocation(GroxionEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/groxion.png");
    }
}
