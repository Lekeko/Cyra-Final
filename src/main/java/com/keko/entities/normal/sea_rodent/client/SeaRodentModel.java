package com.keko.entities.normal.sea_rodent.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SeaRodentModel extends GeoModel<SeaRodentEntity> {
    @Override
    public Identifier getModelResource(SeaRodentEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/sea_rodent.geo.json");
    }

    @Override
    public Identifier getTextureResource(SeaRodentEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/sea_rodent.png");
    }

    @Override
    public Identifier getAnimationResource(SeaRodentEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/sea_rodent.animation.json");
    }
}
