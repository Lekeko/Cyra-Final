package com.keko.packet.networking;

import com.keko.CyraFinal;
import com.keko.packet.HealSyncDataS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier HEALING_SYNC_ID = Identifier.of(CyraFinal.MOD_ID, "healing");

    public static void registerC2SPacket() {

    }

    public static void registerS2CPacket() {
        //ClientPlayNetworking.registerGlobalReceiver(HEALING_SYNC_ID, HealSyncDataS2CPacket::receive);
    }

}
