package com.keko.entities.projectiles.electroCharge;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class ElectroDamagerRenderer extends EntityRenderer<ElectroDamager> {
    public ElectroDamagerRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(ElectroDamager entity) {
        return null;
    }
}
