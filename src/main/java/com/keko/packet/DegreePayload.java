package com.keko.packet;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record DegreePayload(int degree, int cube) implements CustomPayload {
    public static final Id<DegreePayload> ID = new Id<>(ModMessages.DEGGRE_ID);
    public static final PacketCodec<RegistryByteBuf, DegreePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, DegreePayload::degree,
            PacketCodecs.INTEGER, DegreePayload::cube,
            DegreePayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}