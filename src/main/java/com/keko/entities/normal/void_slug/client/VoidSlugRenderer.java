package com.keko.entities.normal.void_slug.client;

import com.keko.CyraFinal;
import com.keko.entities.normal.bottom_stalker.BottomStalkerEntity;
import com.keko.entities.normal.bottom_stalker.client.BottomStalkerModel;
import com.keko.entities.normal.void_slug.VoidSlugEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VoidSlugRenderer  extends GeoEntityRenderer<VoidSlugEntity> {
    public VoidSlugRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new VoidSlugModel());
    }

    @Override
    public Identifier getTextureLocation(VoidSlugEntity animatable) {
        return Identifier.of(CyraFinal.MOD_ID, "textures/entity/void_slug.png");
    }

}