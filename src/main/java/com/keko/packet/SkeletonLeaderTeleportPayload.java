package com.keko.packet;

import com.keko.packet.networking.ModMessages;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SkeletonLeaderTeleportPayload (int x, int y, int z) implements CustomPayload {
    public static final CustomPayload.Id<SkeletonLeaderTeleportPayload> ID = new CustomPayload.Id<>(ModMessages.SKEL_LEADER_TP_ID);
    public static final PacketCodec<RegistryByteBuf, SkeletonLeaderTeleportPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, SkeletonLeaderTeleportPayload::x,
            PacketCodecs.INTEGER, SkeletonLeaderTeleportPayload::y,
            PacketCodecs.INTEGER, SkeletonLeaderTeleportPayload::z,

            SkeletonLeaderTeleportPayload::new);

    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
