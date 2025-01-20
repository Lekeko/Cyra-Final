package com.keko.entities;

import com.keko.CyraFinal;
import com.keko.entities.bosses.jellyFishking.JellyFishKingEntity;
import com.keko.entities.bosses.oldLord.OldLordEntity;
import com.keko.entities.bosses.skeletonLeader.SkeletonLeaderEntity;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import com.keko.entities.lightEntity.LightEntity;
import com.keko.entities.normal.bottom_stalker.BottomStalkerEntity;
import com.keko.entities.normal.deep_salmon.DeepSalmonEntity;
import com.keko.entities.normal.groxion.GroxionEntity;
import com.keko.entities.normal.jellyfish.JellyFishEntity;
import com.keko.entities.normal.sea_rodent.SeaRodentEntity;
import com.keko.entities.normal.stridely.StridelyEntity;
import com.keko.entities.normal.void_slug.VoidSlugEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModEntities {

    public static final EntityType<LightEntity> LIGHT_ENTITY_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "light_entity"),
            EntityType.Builder.create(LightEntity::new, SpawnGroup.MISC).dimensions(0.1f, 0.1f).build());

    public static final EntityType<JellyFishEntity> JELLYFISH = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "jellyfish"),
            EntityType.Builder.create(JellyFishEntity::new, SpawnGroup.MONSTER).dimensions(1.1f, 1.9f).build());

    public static final EntityType<JellyFishKingEntity> JELLYFISH_KING = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "jellyfish_king"),
            EntityType.Builder.create(JellyFishKingEntity::new, SpawnGroup.MONSTER).dimensions(5f, 5f).build());

    public static final EntityType<BottomStalkerEntity> BOTTOM_STALKER = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "bottom_stalker"),
            EntityType.Builder.create(BottomStalkerEntity::new, SpawnGroup.MONSTER).dimensions(1.1f, 1.1f).build());
    public static final EntityType<VoidSlugEntity> VOID_SLUG_ENTITY_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "void_slug"),
            EntityType.Builder.create(VoidSlugEntity::new, SpawnGroup.MONSTER).dimensions(0.8f, 0.8f).build());

    public static final EntityType<ZombieLeaderEntity> ZOMBIE_LEADER = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "zombie_leader"),
            EntityType.Builder.create(ZombieLeaderEntity::new, SpawnGroup.MONSTER).dimensions(1f, 3f).build());

    public static final EntityType<SkeletonLeaderEntity> SKELETON_LEADER = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "skeleton_leader"),
            EntityType.Builder.create(SkeletonLeaderEntity::new, SpawnGroup.MONSTER).dimensions(1f, 3f).build());

    public static final EntityType<OldLordEntity> OLD_LORD_ENTITY_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "old_lord"),
            EntityType.Builder.create(OldLordEntity::new, SpawnGroup.MONSTER).dimensions(1f, 3f).build());

    public static final EntityType<DeepSalmonEntity> DEEP_SALMON = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "deep_salmon"),
            EntityType.Builder.create(DeepSalmonEntity::new, SpawnGroup.WATER_AMBIENT).dimensions(0.4f, 0.4f).build());

    public static final EntityType<GroxionEntity> GROXION = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "groxion"),
            EntityType.Builder.create(GroxionEntity::new, SpawnGroup.WATER_AMBIENT).dimensions(0.4f, 0.4f).build());

    public static final EntityType<SeaRodentEntity> SEA_RODENT = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "sea_rodent"),
            EntityType.Builder.create(SeaRodentEntity::new, SpawnGroup.WATER_AMBIENT).dimensions(0.4f, 0.4f).build());

    public static final EntityType<StridelyEntity> STRIDELY = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "stridely"),
            EntityType.Builder.create(StridelyEntity::new, SpawnGroup.WATER_AMBIENT).dimensions(0.4f, 0.4f).build());




}
