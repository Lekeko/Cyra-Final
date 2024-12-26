package com.keko.packet.networking;

import com.keko.CyraFinal;
import com.keko.entities.projectiles.pyriteCube.PCube;
import com.keko.packet.CubeColorPayload;
import com.keko.packet.HealSyncDataS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.awt.*;

public class ModMessages {
    public static final Identifier HEALING_SYNC_ID = Identifier.of(CyraFinal.MOD_ID, "healing");
    public static final Identifier BUFF_SYNC_ID = Identifier.of(CyraFinal.MOD_ID, "buff");
    public static final Identifier DASH_SYNC_ID = Identifier.of(CyraFinal.MOD_ID, "dash");
    public static final Identifier CUBE_COLOR_ID = Identifier.of(CyraFinal.MOD_ID, "cube_color");

    public static void registerC2SPacket() {

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
    }

}
