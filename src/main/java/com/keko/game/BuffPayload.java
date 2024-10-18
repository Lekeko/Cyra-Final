package com.keko.game;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record BuffPayload (int buff) implements CustomPayload {
    public static final CustomPayload.Id<BuffPayload> ID = new CustomPayload.Id<>(ModMessages.BUFF_SYNC_ID);
    public static final PacketCodec<RegistryByteBuf, BuffPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, BuffPayload::buff, BuffPayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
