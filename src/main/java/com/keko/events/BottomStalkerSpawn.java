package com.keko.events;

import com.keko.entities.ModEntities;
import com.keko.entities.normal.bottom_stalker.BottomStalkerEntity;
import com.keko.world.biome.ModBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class BottomStalkerSpawn {
    public static void tryToSpawnAStalker(MinecraftServer server) {
        for (PlayerEntity player : server.getPlayerManager().getPlayerList()){
            if (player.getWorld().getBiome(player.getBlockPos()).matchesKey(ModBiomes.VOID_WATERS) && player.getWorld().random.nextBetween(1, 100) < 4){
                spawnBottomStalker(player, server);
            }
        }
    }

    private static void spawnBottomStalker(PlayerEntity player, MinecraftServer server) {
        int tries = 5;
        while (tries > 0){
            double x = player.getX() + player.getWorld().random.nextBetween( -10, 10);
            double y = player.getY() + player.getWorld().random.nextBetween( -10, 10);
            double z = player.getZ() + player.getWorld().random.nextBetween( -10, 10);
            if (canSpawnAt(x,y,z,player)){
                BottomStalkerEntity bottomStalker = new BottomStalkerEntity(ModEntities.BOTTOM_STALKER, player.getWorld());
                bottomStalker.setPos(x,y,z);
                player.getWorld().spawnEntity(bottomStalker);
                return;
            }
            tries--;
        }
    }

    private static boolean canSpawnAt(double x, double y, double z, PlayerEntity player) {
        int area = 10;
        for (int i = - area; i < area; i++)
            for (int j = - area; j < area; j++)
                for (int k = - area; k < area; k++){
                    if (!(player.getWorld().getBlockState(new BlockPos((int) (x + i), (int) (y + j), (int) (z + k))).isOf(Blocks.WATER))){
                        return false;
                    }
                }

        return true;
    }
}
