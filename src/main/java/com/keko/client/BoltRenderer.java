package com.keko.client;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.SeaBolt;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class BoltRenderer extends EntityRenderer<SeaBolt> {
    public BoltRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(SeaBolt entity) {
        return Identifier.of(CyraFinal.MOD_ID, "texture/item/sea_bolt.png");
    }
}
