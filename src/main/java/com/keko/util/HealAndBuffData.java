package com.keko.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class HealAndBuffData {
    public static int startHealCooldown(EntityDataServer player, int amount){
        NbtCompound nbt = player.getPersistenData();
        int healCooldown = nbt.getInt("healCooldown");
        if (healCooldown + amount >= 10){
            healCooldown = 10;
        }else {
            healCooldown += amount;
        }

        nbt.putInt("healCooldown", healCooldown);
        return healCooldown;
    }
    public static int resetHealCooldown(EntityDataServer player, int amount){
        NbtCompound nbt = player.getPersistenData();
        int healCooldown = nbt.getInt("healCooldown");
        if (healCooldown + amount >= 10){
            healCooldown = 0;
        }else {
            healCooldown -= amount;
        }

        nbt.putInt("healCooldown", healCooldown);
        return healCooldown;

    }
    public static int startBuffCooldown(){
        return 0;
    }
    public static int resetBuffCooldown(){
        return 0;
    }

    public static void  syncCooldown(int healCooldown, ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(healCooldown);
        //ServerPlayNetworking.send(player, ModMess);
    }

}
