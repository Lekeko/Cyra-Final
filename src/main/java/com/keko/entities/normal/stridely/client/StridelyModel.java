package com.keko.entities.normal.stridely.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import com.keko.entities.normal.stridely.StridelyEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class StridelyModel extends GeoModel<StridelyEntity> {
    @Override
    public Identifier getModelResource(StridelyEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/stridely.geo.json");
    }

    @Override
    public Identifier getTextureResource(StridelyEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/stridely.png");
    }

    @Override
    public Identifier getAnimationResource(StridelyEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/stridely.animation.json");
    }
}