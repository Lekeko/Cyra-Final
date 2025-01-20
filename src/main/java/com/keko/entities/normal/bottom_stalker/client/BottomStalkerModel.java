package com.keko.entities.normal.bottom_stalker.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.bottom_stalker.BottomStalkerEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BottomStalkerModel extends GeoModel<BottomStalkerEntity> {
    @Override
    public Identifier getModelResource(BottomStalkerEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/bottom_stalker.geo.json");
    }

    @Override
    public Identifier getTextureResource(BottomStalkerEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/bottom_stalker.png");
    }

    @Override
    public Identifier getAnimationResource(BottomStalkerEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/bottom_stalker.animation.json");
    }


}