package com.keko.entities.normal.deep_salmon.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import com.keko.entities.normal.deep_salmon.DeepSalmonEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class DeepSalmonModel extends GeoModel<DeepSalmonEntity> {
    @Override
    public Identifier getModelResource(DeepSalmonEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/deep_salmon.geo.json");
    }

    @Override
    public Identifier getTextureResource(DeepSalmonEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/deep_salmon.png");
    }

    @Override
    public Identifier getAnimationResource(DeepSalmonEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/deep_salmon.animation.json");
    }


}
