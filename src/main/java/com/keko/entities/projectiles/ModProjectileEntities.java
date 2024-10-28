package com.keko.entities.projectiles;

import com.keko.CyraFinal;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModProjectileEntities {
    public static final EntityType<SeaBolt> SEA_BOLT_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "sea_bolt"),
            EntityType.Builder.<SeaBolt>create(SeaBolt::new, SpawnGroup.MISC).dimensions(1.0f, 1.0f).build());


    public static void registerModEntityProjectiles(){

    }
}
