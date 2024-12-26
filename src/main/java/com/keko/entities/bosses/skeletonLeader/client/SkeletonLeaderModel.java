package com.keko.entities.bosses.skeletonLeader.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.skeletonLeader.SkeletonLeaderEntity;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonLeaderModel extends GeoModel<SkeletonLeaderEntity> {

    @Override
    public Identifier getModelResource(SkeletonLeaderEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/skeleton_leader.geo.json");
    }

    @Override
    public Identifier getTextureResource(SkeletonLeaderEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/skeleton_leader.png");
    }

    @Override
    public Identifier getAnimationResource(SkeletonLeaderEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/skeleton_leader.animation.json");
    }


}
