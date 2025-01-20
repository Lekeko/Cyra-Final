package com.keko.entities.normal.sea_rodent.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.groxion.GroxionEntity;
import com.keko.entities.normal.groxion.client.GroxionModel;
import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeaRodentRenderer extends GeoEntityRenderer<SeaRodentEntity> {
    public SeaRodentRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SeaRodentModel());
    }

    @Override
    public Identifier getTextureLocation(SeaRodentEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/sea_rodent.png");
    }
}
