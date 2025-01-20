package com.keko.entities.bosses.oldLord.client;

import com.keko.CyraFinal;
import com.keko.entities.bosses.oldLord.OldLordEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OldLordRenderer  extends GeoEntityRenderer<OldLordEntity> {
    public OldLordRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new OldLordModel());
    }

    @Override
    public Identifier getTextureLocation(OldLordEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/old_lord.png");
    }

}
