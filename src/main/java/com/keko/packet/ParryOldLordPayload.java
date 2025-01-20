package com.keko.packet;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record ParryOldLordPayload(int id) implements CustomPayload {
    public static final Id<ParryOldLordPayload> ID = new Id<>(ModMessages.PARRY_OLD_LORD);
    public static final PacketCodec<RegistryByteBuf, ParryOldLordPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, ParryOldLordPayload::id,

            ParryOldLordPayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}