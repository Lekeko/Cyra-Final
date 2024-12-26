package com.keko.model;

import com.keko.CyraFinal;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayer {
        public static final EntityModelLayer SEA_BOLT = new EntityModelLayer(Identifier.of(CyraFinal.MOD_ID, "entity/sea_bolt"), "main");
        public static final EntityModelLayer PCUBE = new EntityModelLayer(Identifier.of(CyraFinal.MOD_ID, "entity/pyrite_primordial_cube"), "main");
        public static final EntityModelLayer FOTOL = new EntityModelLayer(Identifier.of(CyraFinal.MOD_ID, "entity/fist_of_the_old_lord"), "main");

}
