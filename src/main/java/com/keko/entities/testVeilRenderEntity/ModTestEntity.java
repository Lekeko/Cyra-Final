package com.keko.entities.testVeilRenderEntity;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.pyriteCube.PCube;
import com.keko.entities.testVeilRenderEntity.cubeEntity.CubeEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModTestEntity {
    public static final EntityType<CubeEntity> CUBE_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "cube_entity"),
            EntityType.Builder.<CubeEntity>create(CubeEntity::new, SpawnGroup.MISC).dimensions(1f, 1f).build());


    public static void registerModTestEntityProjectiles() {

    }
}
