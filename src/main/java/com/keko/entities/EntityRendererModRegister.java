package com.keko.entities;

import com.keko.CyraFinal;
import com.keko.entities.bosses.jellyFishking.client.JellyFishKingEntityRenderer;
import com.keko.entities.bosses.oldLord.client.OldLordRenderer;
import com.keko.entities.bosses.skeletonLeader.client.SkeletonLeaderRenderer;
import com.keko.entities.bosses.zombieLeader.client.ZombieLeaderRenderer;
import com.keko.entities.lightEntity.LightEntityRenderer;
import com.keko.entities.normal.bottom_stalker.client.BottomStalkerRender;
import com.keko.entities.normal.deep_salmon.DeepSalmonEntity;
import com.keko.entities.normal.deep_salmon.client.DeepSalmonRenderer;
import com.keko.entities.normal.groxion.client.GroxionRenderer;
import com.keko.entities.normal.jellyfish.client.JellyFishEntityRenderer;
import com.keko.entities.normal.sea_rodent.client.SeaRodentRenderer;
import com.keko.entities.normal.stridely.client.StridelyRenderer;
import com.keko.entities.normal.void_slug.client.VoidSlugRenderer;
import com.keko.entities.projectiles.FOTOL.FistOfTheOldLordRendering;
import com.keko.entities.projectiles.ModProjectileEntities;
import com.keko.entities.projectiles.compulsionAxe.CompulsionAxeRendering;
import com.keko.entities.projectiles.compulsionScythe.CompulsionScythe;
import com.keko.entities.projectiles.compulsionScythe.CompulsionScytheRendering;
import com.keko.entities.projectiles.compulsionSword.CompulsionSwordRendering;
import com.keko.entities.projectiles.electroCharge.ElectroChargeRenderer;
import com.keko.entities.projectiles.electroCharge.ElectroDamagerRenderer;
import com.keko.entities.projectiles.electroCharge.ElectroRenderer;
import com.keko.entities.projectiles.lordStar.LordStarEntityRenderer;
import com.keko.entities.projectiles.oldLordsSpear.OldLordsSpearEntity;
import com.keko.entities.projectiles.oldLordsSpear.OldLordsSpearEntityRenderer;
import com.keko.entities.projectiles.pyriteCube.PCubeRenderer;
import com.keko.entities.projectiles.seaBolt.BoltRenderer;
import com.keko.entities.testVeilRenderEntity.ModTestEntity;
import com.keko.entities.testVeilRenderEntity.cubeEntity.CubeEntityRenderer;
import com.keko.model.ModModelLayer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;

public class EntityRendererModRegister {
    public static void register(){
        EntityRendererRegistry.register(ModProjectileEntities.SEA_BOLT_ENTITY_TYPE, ctx -> new BoltRenderer(ctx, Identifier.of(CyraFinal.MOD_ID, "textures/entity/sea_bolt.png"), ModModelLayer.SEA_BOLT));
        EntityRendererRegistry.register(ModTestEntity.CUBE_ENTITY_TYPE, CubeEntityRenderer::new);
        EntityRendererRegistry.register(ModProjectileEntities.FIST_OF_THE_OLD_LORD, FistOfTheOldLordRendering::new);
        EntityRendererRegistry.register(ModProjectileEntities.ELECTRO_CHARGE, ElectroChargeRenderer::new);
        EntityRendererRegistry.register(ModProjectileEntities.COMPULSION_SWORD_ENTITY_TYPE, CompulsionSwordRendering::new);
        EntityRendererRegistry.register(ModProjectileEntities.OLD_LORDS_SPEAR_ENTITY_ENTITY_TYPE, OldLordsSpearEntityRenderer::new);
        EntityRendererRegistry.register(ModProjectileEntities.COMPULSION_AXE_ENTITY_TYPE, CompulsionAxeRendering::new);
        EntityRendererRegistry.register(ModProjectileEntities.COMPULSION_SCYTHE_ENTITY_TYPE, CompulsionScytheRendering::new);
        EntityRendererRegistry.register(ModProjectileEntities.ELECTRO, ElectroRenderer::new);
        EntityRendererRegistry.register(ModProjectileEntities.LORD_STAR, ctx -> new LordStarEntityRenderer(ctx, Identifier.of(CyraFinal.MOD_ID, "textures/entity/lord_star.png"), ModModelLayer.PCUBE));
        EntityRendererRegistry.register(ModProjectileEntities.ELECTRO_DAMAGER, ElectroDamagerRenderer::new);
        EntityRendererRegistry.register(ModProjectileEntities.P_CUBE_ENTITY_TYPE, ctx -> new PCubeRenderer(ctx, Identifier.of(CyraFinal.MOD_ID, "textures/entity/pyrite_primordial_cube.png"), ModModelLayer.PCUBE));
        EntityRendererRegistry.register(ModEntities.ZOMBIE_LEADER, ZombieLeaderRenderer::new);
        EntityRendererRegistry.register(ModEntities.SKELETON_LEADER, SkeletonLeaderRenderer::new);
        EntityRendererRegistry.register(ModEntities.OLD_LORD_ENTITY_ENTITY_TYPE, OldLordRenderer::new);
        EntityRendererRegistry.register(ModEntities.JELLYFISH, JellyFishEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.JELLYFISH_KING, JellyFishKingEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.DEEP_SALMON, DeepSalmonRenderer::new);
        EntityRendererRegistry.register(ModEntities.GROXION, GroxionRenderer::new);
        EntityRendererRegistry.register(ModEntities.SEA_RODENT, SeaRodentRenderer::new);
        EntityRendererRegistry.register(ModEntities.STRIDELY, StridelyRenderer::new);
        EntityRendererRegistry.register(ModEntities.BOTTOM_STALKER, BottomStalkerRender::new);
        EntityRendererRegistry.register(ModEntities.LIGHT_ENTITY_ENTITY_TYPE, LightEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.VOID_SLUG_ENTITY_ENTITY_TYPE, VoidSlugRenderer::new);
    }
}
