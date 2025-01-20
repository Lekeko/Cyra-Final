package com.keko.entities.normal.jellyfish.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.jellyfish.JellyFishEntity;
import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class JellyFishEntityModel extends GeoModel<JellyFishEntity> {
    @Override
    public Identifier getModelResource(JellyFishEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/jellyfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(JellyFishEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/jellyfish.png");
    }

    @Override
    public Identifier getAnimationResource(JellyFishEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/jellyfish.animation.json");
    }
}
