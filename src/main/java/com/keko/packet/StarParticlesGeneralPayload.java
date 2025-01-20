package com.keko.packet;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record StarParticlesGeneralPayload(double x, double y, double z) implements CustomPayload {
    public static final Id<StarParticlesGeneralPayload> ID = new Id<>(ModMessages.STARS);
    public static final PacketCodec<RegistryByteBuf, StarParticlesGeneralPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.DOUBLE, StarParticlesGeneralPayload::x,
            PacketCodecs.DOUBLE, StarParticlesGeneralPayload::y,
            PacketCodecs.DOUBLE, StarParticlesGeneralPayload::z,

            StarParticlesGeneralPayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}