package com.keko.entities;

import com.keko.CyraFinal;
import com.keko.entities.bosses.skeletonLeader.SkeletonLeaderEntity;
import com.keko.entities.bosses.zombieLeader.ZombieLeaderEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModEntities {

    public static final EntityType<ZombieLeaderEntity> ZOMBIE_LEADER = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "zombie_leader"),
            EntityType.Builder.create(ZombieLeaderEntity::new, SpawnGroup.MONSTER).dimensions(1f, 3f).build());

    public static final EntityType<SkeletonLeaderEntity> SKELETON_LEADER = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(CyraFinal.MOD_ID, "skeleton_entity"),
            EntityType.Builder.create(SkeletonLeaderEntity::new, SpawnGroup.MONSTER).dimensions(1f, 3f).build());

}
