package com.keko.entities.normal.groxion.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.deep_salmon.DeepSalmonEntity;
import com.keko.entities.normal.groxion.GroxionEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GroxionModel extends GeoModel<GroxionEntity> {
    @Override
    public Identifier getModelResource(GroxionEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/groxion.geo.json");
    }

    @Override
    public Identifier getTextureResource(GroxionEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/groxion.png");
    }

    @Override
    public Identifier getAnimationResource(GroxionEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/groxion.animation.json");
    }

}
