package com.keko.entities.normal.void_slug.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import com.keko.entities.normal.void_slug.VoidSlugEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class VoidSlugModel extends GeoModel<VoidSlugEntity> {
    @Override
    public Identifier getModelResource(VoidSlugEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/void_slug.geo.json");
    }

    @Override
    public Identifier getTextureResource(VoidSlugEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/void_slug.png");
    }

    @Override
    public Identifier getAnimationResource(VoidSlugEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/void_slug.animation.json");
    }


}
