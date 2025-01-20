package com.keko.entities.bosses.jellyFishking.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.jellyFishking.JellyFishKingEntity;
import com.keko.entities.normal.jellyfish.JellyFishEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class JellyFishKingEntityModel extends GeoModel<JellyFishKingEntity> {
    @Override
    public Identifier getModelResource(JellyFishKingEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/jellyfishking.geo.json");
    }

    @Override
    public Identifier getTextureResource(JellyFishKingEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/jellyfishking.png");
    }

    @Override
    public Identifier getAnimationResource(JellyFishKingEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/jellyfish.animation.json");
    }
}
