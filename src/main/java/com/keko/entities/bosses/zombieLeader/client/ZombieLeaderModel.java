package com.keko.entities.bosses.zombieLeader.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ZombieLeaderModel extends GeoModel<ZombieLeaderEntity> {
    @Override
    public Identifier getModelResource(ZombieLeaderEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/zombie_leader.geo.json");
    }

    @Override
    public Identifier getTextureResource(ZombieLeaderEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/zombie_leader.png");
    }

    @Override
    public Identifier getAnimationResource(ZombieLeaderEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/zombie_leader.animation.json");
    }


    @Override
    public void setCustomAnimations(ZombieLeaderEntity animatable, long instanceId, AnimationState<ZombieLeaderEntity> animationState) {

    }
}
