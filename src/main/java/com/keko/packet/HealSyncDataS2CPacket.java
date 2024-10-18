package com.keko.packet;

import com.keko.util.EntityDataServer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class  HealSyncDataS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf byteBuf, PacketSender packetSender){
        assert client.player != null;
        ((EntityDataServer) client.player).getPersistenData().putInt("healing", byteBuf.readInt());
    }
}
