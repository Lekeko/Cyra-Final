package com.keko.entities.bosses.skeletonLeader.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.skeletonLeader.SkeletonLeaderEntity;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import com.keko.entities.bosses.zombieLeader.client.ZombieLeaderModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonLeaderRenderer extends GeoEntityRenderer<SkeletonLeaderEntity> {
    public SkeletonLeaderRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SkeletonLeaderModel());
    }

    @Override
    public Identifier getTextureLocation(SkeletonLeaderEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/skeleton_leader.png");
    }

}
