package com.keko.entities.bosses.oldLord.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.oldLord.OldLordEntity;
import com.keko.entities.bosses.skeletonLeader.SkeletonLeaderEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class OldLordModel extends GeoModel<OldLordEntity> {

    @Override
    public Identifier getModelResource(OldLordEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "geo/old_lord.geo.json");
    }

    @Override
    public Identifier getTextureResource(OldLordEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/old_lord.png");
    }

    @Override
    public Identifier getAnimationResource(OldLordEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "animations/old_lord.animation.json");
    }


}
