package com.keko.game;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SlamAttackpayload (boolean slam) implements CustomPayload {
    public static final CustomPayload.Id<SlamAttackpayload> ID = new CustomPayload.Id<>(ModMessages.SLAM_ATTACK_SYNC_ID);
    public static final PacketCodec<RegistryByteBuf, SlamAttackpayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, SlamAttackpayload::slam, SlamAttackpayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}