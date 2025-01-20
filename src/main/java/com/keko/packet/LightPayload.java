package com.keko.packet;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record LightPayload(int player, int light, boolean addOrRemove) implements CustomPayload {
    public static final Id<LightPayload> ID = new Id<>(ModMessages.LIGHT_ID);
    public static final PacketCodec<RegistryByteBuf, LightPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, LightPayload::player,
            PacketCodecs.INTEGER, LightPayload::light,
            PacketCodecs.BOOL, LightPayload::addOrRemove,

            LightPayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}