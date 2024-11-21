package com.keko.entities.bosses.zombieLeader.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ZombieLeaderRenderer extends GeoEntityRenderer<ZombieLeaderEntity> {
    public ZombieLeaderRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ZombieLeaderModel());
    }

    @Override
    public Identifier getTextureLocation(ZombieLeaderEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/zombie_leader.png");
    }


}
