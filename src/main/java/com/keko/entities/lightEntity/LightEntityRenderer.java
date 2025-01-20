package com.keko.entities.lightEntity;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class LightEntityRenderer extends EntityRenderer<LightEntity> {
    public LightEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(LightEntity entity) {
        return null;
    }

}
