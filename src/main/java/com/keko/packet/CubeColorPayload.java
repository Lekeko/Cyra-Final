package com.keko.packet;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record CubeColorPayload(int cube, int red, int green, int blue) implements CustomPayload {
    public static final Id<CubeColorPayload> ID = new Id<>(ModMessages.CUBE_COLOR_ID);
    public static final PacketCodec<RegistryByteBuf, CubeColorPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, CubeColorPayload::cube,
            PacketCodecs.INTEGER, CubeColorPayload::red,
            PacketCodecs.INTEGER, CubeColorPayload::green,
            PacketCodecs.INTEGER, CubeColorPayload::blue,

            CubeColorPayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}