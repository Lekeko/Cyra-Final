package com.keko.packet;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record StarParticlesScythePayload(double x, double y, double z) implements CustomPayload {
    public static final Id<StarParticlesScythePayload> ID = new Id<>(ModMessages.STARS_SCYTHE);
    public static final PacketCodec<RegistryByteBuf, StarParticlesScythePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.DOUBLE, StarParticlesScythePayload::x,
            PacketCodecs.DOUBLE, StarParticlesScythePayload::y,
            PacketCodecs.DOUBLE, StarParticlesScythePayload::z,

            StarParticlesScythePayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}