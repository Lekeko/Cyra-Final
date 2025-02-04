package com.keko.items.tools;

import com.keko.ComponentTypes.ModDataComponentTypes;
import com.keko.CyraFinal;
import com.keko.entities.ModEntities;
import com.keko.entities.lightEntity.LightEntity;
import com.keko.packet.LightPayload;
import com.keko.world.ModDimensions;
import foundry.veil.Veil;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class FlashLight extends Item {

    private short brightness = 0;
    public Light light;
    public boolean added = false;

    float pitch = 0;

    public FlashLight(Settings settings) {
        super(settings);
        this.getDefaultStack().set(ModDataComponentTypes.HAS_LIGHT, false);
        this.getDefaultStack().set(ModDataComponentTypes.LIGHT_ID, 0);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && world.getRegistryKey() == ModDimensions.MURIEL_KAIA_LEVEL_KEY && user.getStackInHand(hand).get(ModDataComponentTypes.HAS_LIGHT) != null){
            if (user.getStackInHand(hand).get(ModDataComponentTypes.HAS_LIGHT) == false){
                LightEntity lightEntity = new LightEntity(ModEntities.LIGHT_ENTITY_ENTITY_TYPE, world);
                lightEntity.setOwner(user);
                lightEntity.setPosition(Vec3d.of(user.getBlockPos()));
                Box box = new Box(user.getX() + 100, user.getY() + 100, user.getZ() + 100, user.getX() - 100, user.getY() - 100, user.getZ() - 100);
                for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class, box, PlayerEntity::isAlive))
                    ServerPlayNetworking.send((ServerPlayerEntity) player, new LightPayload(user.getId(), lightEntity.getId(), true));
                user.getStackInHand(hand).set(ModDataComponentTypes.LIGHT_ID, lightEntity.getId());
                user.getStackInHand(hand).set(ModDataComponentTypes.HAS_LIGHT, true);

                world.spawnEntity(lightEntity);
            }else {
                LightEntity lightEntity = (LightEntity) world.getEntityById(user.getStackInHand(hand).get(ModDataComponentTypes.LIGHT_ID));
                try {
                    lightEntity.discard();
                }catch (Exception e){
                    user.getStackInHand(hand).set(ModDataComponentTypes.HAS_LIGHT, false);
                    user.getStackInHand(hand).set(ModDataComponentTypes.LIGHT_ID, 0);
                }
                user.getStackInHand(hand).set(ModDataComponentTypes.HAS_LIGHT, false);

            }
        }
        return super.use(world, user, hand);
    }


    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        if (stack.get(ModDataComponentTypes.HAS_LIGHT) == null || world.getRegistryKey() != ModDimensions.MURIEL_KAIA_LEVEL_KEY){
            stack.set(ModDataComponentTypes.HAS_LIGHT, false);
            stack.set(ModDataComponentTypes.LIGHT_ID, 0);

        }
        if (stack.get(ModDataComponentTypes.HAS_LIGHT) != null && stack.get(ModDataComponentTypes.LIGHT_ID) != null){
            if (stack.get(ModDataComponentTypes.HAS_LIGHT) == false && stack.get(ModDataComponentTypes.LIGHT_ID) == 0){
                stack.set(ModDataComponentTypes.HAS_LIGHT, false);
                stack.set(ModDataComponentTypes.LIGHT_ID, 0);
            }
        }

        if (world.isClient && light != null)
            this.light.setTo(camera);

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
