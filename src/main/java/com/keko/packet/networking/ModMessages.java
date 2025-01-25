package com.keko.packet.networking;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.CyraFinal;
import com.keko.CyraModClient;
import com.keko.entities.lightEntity.LightEntity;
import com.keko.entities.projectiles.pyriteCube.PCube;
import com.keko.game.BuffPayload;
import com.keko.game.HealPayload;
import com.keko.game.KeyBinds;
import com.keko.helpers.InvSearch;
import com.keko.items.ModItems;
import com.keko.items.tools.BuffFlask;
import com.keko.items.tools.FlashLight;
import com.keko.packet.*;
import com.keko.particle.ModParticles;
import foundry.veil.Veil;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import foundry.veil.api.quasar.data.ParticleSettings;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class ModMessages {
    public static final Identifier HEALING_SYNC_ID = Identifier.of(CyraFinal.MOD_ID, "healing");
    public static final Identifier BUFF_SYNC_ID = Identifier.of(CyraFinal.MOD_ID, "buff");
    public static final Identifier DASH_SYNC_ID = Identifier.of(CyraFinal.MOD_ID, "dash");
    public static final Identifier CUBE_COLOR_ID = Identifier.of(CyraFinal.MOD_ID, "cube_color");
    public static final Identifier SKEL_LEADER_TP_ID = Identifier.of(CyraFinal.MOD_ID, "skel_leader_tp");
    public static final Identifier LIGHT_ID = Identifier.of(CyraFinal.MOD_ID, "light_tp");
    public static final Identifier DEGGRE_ID = Identifier.of(CyraFinal.MOD_ID, "degree_tp");
    public static final Identifier PARRY_OLD_LORD = Identifier.of(CyraFinal.MOD_ID, "parry_old_lord_tp");
    public static final Identifier AXE_PARTICLES = Identifier.of(CyraFinal.MOD_ID, "axe_particles_tp");
    public static final Identifier STARS = Identifier.of(CyraFinal.MOD_ID, "stars_tp");

    public static void registerC2SPacket() {
        PayloadTypeRegistry.playC2S().register(HealPayload.ID, HealPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(HealPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                context.player().heal(2 * KeyBinds.healing_amount);
                ItemStack itemStack = InvSearch.getItemInInv(context.player(), ModItems.HEALING_FLASK);
                assert itemStack != null;
                context.player().getItemCooldownManager().set(itemStack.getItem(), KeyBinds.cooldown * 20);
            });
        });



        PayloadTypeRegistry.playC2S().register(BuffPayload.ID, BuffPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(BuffPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                ItemStack itemStack = InvSearch.getItemStackInInv(context.player(), ModItems.BUFF_FLASK);
                PlayerEntity user = context.player();

                HashMap<Integer, StatusEffectInstance> effect = BuffFlask.effect;
                if (!context.player().getItemCooldownManager().isCoolingDown(itemStack.getItem())){
                    if (itemStack.get(ModDataComponentTypes.EFFECT_BUFF_1) != null && itemStack.get(ModDataComponentTypes.EFFECT_BUFF_2) != null){
                        user.setStatusEffect(effect.get(itemStack.get(ModDataComponentTypes.EFFECT_BUFF_1)), user);
                        user.setStatusEffect(effect.get(itemStack.get(ModDataComponentTypes.EFFECT_BUFF_2)), user);

                        effect.clear();

                        effect.put(1, new StatusEffectInstance(StatusEffects.HASTE, 20 * 60 * 2, 2));
                        effect.put(2, new StatusEffectInstance(StatusEffects.SPEED, 20 * 60 * 2, 2));
                        effect.put(3, new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 60 * 2, 2));
                        effect.put(4, new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 60, 1));
                        effect.put(5, new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 60 * 2, 2));
                        user.getItemCooldownManager().set(itemStack.getItem(), (int)(20 * 60 * 2.6));

                    }else {
                        Objects.requireNonNull(user).sendMessage(Text.literal("The Buff Potion does not have any effects!").withColor(new Color(245, 165, 255, 255).getRGB()));

                    }
                }
            });
        });

    }

    public static void registerS2CPacket() {
        PayloadTypeRegistry.playS2C().register(CubeColorPayload.ID, CubeColorPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(CubeColorPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                PCube cube = (PCube) context.player().getWorld().getEntityById(payload.cube());
                assert cube != null;
                try{
                    cube.color = new Color(payload.red(), payload.green(), payload.blue(), 255);
                }catch (Exception ignored){}
            });
        });

        PayloadTypeRegistry.playS2C().register(DegreePayload.ID, DegreePayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(DegreePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                PCube cube = (PCube) context.player().getWorld().getEntityById(payload.cube());
                assert cube != null;
                try{
                    cube.degree = payload.degree();
                }catch (Exception ignored){}
            });
        });
        PayloadTypeRegistry.playS2C().register(AxeparticlesLordPayload.ID, AxeparticlesLordPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(AxeparticlesLordPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientPlayerEntity player = context.player();
                for (int i = 0; i < 60; i++)
                    player.getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, player.getX(), player.getY(), player.getZ(),
                            (player.getWorld().random.nextFloat() - .5f ) * 2,
                            player.getWorld().random.nextFloat() / 4 ,
                            (player.getWorld().random.nextFloat() - .5f ) * 2);
            });
        });

        PayloadTypeRegistry.playS2C().register(StarParticlesGeneralPayload.ID, StarParticlesGeneralPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(StarParticlesGeneralPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientPlayerEntity player = context.player();
                double x = payload.x();
                double y = payload.y();
                double z = payload.z();
                for (int i = 0; i < 1000; i++)
                    player.getWorld().addParticle(ParticleTypes.END_ROD, x,y ,z,
                            (player.getWorld().random.nextFloat() - .5f ) * 10,
                            (player.getWorld().random.nextFloat() - .5f ) * 10,
                            (player.getWorld().random.nextFloat() - .5f ) * 10);
                spawnParticle(player, Identifier.of(CyraFinal.MOD_ID, "star_burst"), x,y,z);

            });
        });

        PayloadTypeRegistry.playS2C().register(SkeletonLeaderTeleportPayload.ID, SkeletonLeaderTeleportPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(SkeletonLeaderTeleportPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                for (int i = 0; i < 20; i++){
                    Random random = new Random();
                    context.player().getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, true, payload.x()+0.5f, payload.y()+1.5f, payload.z()+0.5f,
                            -.5 + random.nextFloat(),  -.5 + random.nextFloat(),  -.5 + random.nextFloat() );
                }
            });
        });

        PayloadTypeRegistry.playS2C().register(LightPayload.ID, LightPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(LightPayload.ID, (payload, context) -> {
            context.client().execute(() -> {

                ClientWorld world = (ClientWorld) context.client().world;

                PlayerEntity targetEntity = (PlayerEntity) world.getEntityById(payload.player());

                LightEntity light = (LightEntity) context.client().world.getEntityById(payload.light());

                assert light != null;
                light.setOwner(targetEntity);
            });
        });

        PayloadTypeRegistry.playS2C().register(ParryOldLordPayload.ID, ParryOldLordPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(ParryOldLordPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                CyraModClient.initializeFlash();
            });
        });

    }


    public static void spawnParticle(Entity entity, Identifier id, double x, double y, double z){
        try {
            ParticleSystemManager manager = VeilRenderSystem.renderer().getParticleManager();
            ParticleEmitter emitter = manager.createEmitter(id);
            emitter.setPosition(x,y,z);
            emitter.setParticleSettings(new ParticleSettings(5f, 0.0f, 0.05f,
                    100, 0f,
                    new Vector3f(
                            entity.getWorld().random.nextFloat() - .5f * 5f,
                            entity.getWorld().random.nextFloat() - .5f * 5f,
                            entity.getWorld().random.nextFloat() - .5f * 5f),
                    true,false,false,true,false));
            manager.addParticleSystem(emitter);
        } catch (Exception ignored) {
        }
    }
}
