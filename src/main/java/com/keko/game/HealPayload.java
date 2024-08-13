package com.keko.game;

import com.keko.packet.HealSyncDataS2CPacket;
import com.keko.packet.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;

public record HealPayload(int heal) implements CustomPayload {
    public static final CustomPayload.Id<HealPayload> ID = new CustomPayload.Id<>(ModMessages.HEALING_SYNC_ID);
    public static final PacketCodec<RegistryByteBuf, HealPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, HealPayload::heal, HealPayload::new);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
