package com.keko.game;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record DashPayload (int dash) implements CustomPayload {
    public static final CustomPayload.Id<DashPayload> ID = new CustomPayload.Id<>(ModMessages.DASH_SYNC_ID);
    public static final PacketCodec<RegistryByteBuf, DashPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, DashPayload::dash, DashPayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}