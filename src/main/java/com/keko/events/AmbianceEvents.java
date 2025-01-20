package com.keko.events;

import com.keko.sounds.ModSounds;
import com.keko.world.biome.ModBiomes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundCategory;

import java.util.Random;

public class AmbianceEvents {
    public static void scarePlayersInVoid(MinecraftServer server) {
        Random random = new Random();
        if (random.nextInt(100) < 10){
            for (PlayerEntity players : server.getPlayerManager().getPlayerList())
                if (players.isSubmergedInWater() && players.getWorld().getBiome(players.getBlockPos()).matchesKey(ModBiomes.VOID_WATERS))
                    players.getWorld().playSound(null, players.getBlockPos(), random.nextBoolean() ? ModSounds.VOID_AMBIANCE : ModSounds.VOID_AMBIANCE_MONSTER, SoundCategory.AMBIENT, 2, 1f);
        }
    }

    public static void playMusic(MinecraftClient client) {
        Random random = new Random();
        if (client.player != null){
            if (true && (client.player.getWorld().getBiome(client.player.getBlockPos()).matchesKey(ModBiomes.DAZED_WATERS) || client.player.getWorld().getBiome(client.player.getBlockPos()).matchesKey(ModBiomes.MURIEL_WATERS))) {
                client.getMusicTracker().play(new MusicSound(RegistryEntry.of(ModSounds.MURIEL_MUSIC_1), 0, 0, true));
            }
        }
    }
}
