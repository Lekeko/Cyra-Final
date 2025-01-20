package com.keko.packet;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record AxeparticlesLordPayload(int id) implements CustomPayload {
    public static final Id<AxeparticlesLordPayload> ID = new Id<>(ModMessages.AXE_PARTICLES);
    public static final PacketCodec<RegistryByteBuf, AxeparticlesLordPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, AxeparticlesLordPayload::id,

            AxeparticlesLordPayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}