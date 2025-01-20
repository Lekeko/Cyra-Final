package com.keko.entities.projectiles;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.FOTOL.FistOfTheOldLord;
import com.keko.entities.projectiles.compulsionAxe.CompulsionAxe;
import com.keko.entities.projectiles.compulsionSword.CompulsionSword;
import com.keko.entities.projectiles.electroCharge.Electro;
import com.keko.entities.projectiles.electroCharge.ElectroCharge;
import com.keko.entities.projectiles.electroCharge.ElectroDamager;
import com.keko.entities.projectiles.lordStar.LordStarEntity;
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
            EntityType.Builder.<PCube>create(PCube::new, SpawnGroup.MISC).dimensions(1.15f, 1.15f).build());

    public static final EntityType<CompulsionSword> COMPULSION_SWORD_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "compulsion_sword"),
            EntityType.Builder.<CompulsionSword>create(CompulsionSword::new, SpawnGroup.MISC).dimensions(0.15f, 0.15f).build());

    public static final EntityType<CompulsionAxe> COMPULSION_AXE_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "compulsion_axe"),
            EntityType.Builder.<CompulsionAxe>create(CompulsionAxe::new, SpawnGroup.MISC).dimensions(6f, 0.15f).build());

    public static final EntityType<LordStarEntity> LORD_STAR = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "lord_star"),
            EntityType.Builder.<LordStarEntity>create(LordStarEntity::new, SpawnGroup.MISC).dimensions(0.3f, 0.3f).build());

    public static final EntityType<FistOfTheOldLord> FIST_OF_THE_OLD_LORD = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "fist_of_the_old_lord"),
            EntityType.Builder.<FistOfTheOldLord>create(FistOfTheOldLord::new, SpawnGroup.MISC).dimensions(2f, 2f).build());

    public static final EntityType<ElectroCharge> ELECTRO_CHARGE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "electro_charge"),
            EntityType.Builder.<ElectroCharge>create(ElectroCharge::new, SpawnGroup.MISC).dimensions(0.3f, 0.3f).build());

    public static final EntityType<Electro> ELECTRO = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "electro"),
            EntityType.Builder.<Electro>create(Electro::new, SpawnGroup.MISC).dimensions(0.3f, 0.3f).build());

    public static final EntityType<ElectroDamager> ELECTRO_DAMAGER = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(CyraFinal.MOD_ID, "electro_damager"),
            EntityType.Builder.<ElectroDamager>create(ElectroDamager::new, SpawnGroup.MISC).dimensions(0.1f, 0.1f).build());


    public static void registerModEntityProjectiles(){

    }
}
