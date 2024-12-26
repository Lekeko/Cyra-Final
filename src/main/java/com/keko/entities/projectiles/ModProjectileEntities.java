package com.keko.entities.projectiles;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.FOTOL.FistOfTheOldLord;
import com.keko.entities.projectiles.pyriteCube.PCube;
import com.keko.entities.projectiles.seaBolt.SeaBolt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModProjectileEntities {
    public static final EntityType<SeaBolt> SEA_BOLT_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "sea_bolt"),
            EntityType.Builder.<SeaBolt>create(SeaBolt::new, SpawnGroup.MISC).dimensions(.3f, .3f).build());

    public static final EntityType<PCube> P_CUBE_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "pyrite_primordial_cube"),
            EntityType.Builder.<PCube>create(PCube::new, SpawnGroup.MISC).dimensions(1f, 1f).build());

    public static final EntityType<FistOfTheOldLord> FIST_OF_THE_OLD_LORD = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "fist_of_the_old_lord"),
            EntityType.Builder.<FistOfTheOldLord>create(FistOfTheOldLord::new, SpawnGroup.MISC).dimensions(2f, 2f).build());


    public static void registerModEntityProjectiles(){

    }
}
