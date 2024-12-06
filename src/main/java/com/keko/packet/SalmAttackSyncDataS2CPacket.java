package com.keko.packet;

import com.keko.util.EntityDataServer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;

public class SalmAttackSyncDataS2CPacket {
    public static void receive(MinecraftServer server, ClientPlayNetworkHandler handler,
                               PacketByteBuf byteBuf, PacketSender packetSender){
        ((EntityDataServer) server).getPersistenData().putBoolean("slam", byteBuf.readBoolean());
    }
}
